package com.hangtoo.bossp.service;

import com.hangtoo.bossp.codec.AbstractMessage;
import com.hangtoo.bossp.util.ClusterChannelHelp;
import com.hangtoo.util.ResourceBundleTools;



public final class Sender {
	
	private static int icluster=0;
	
	private static String[] serviceaddrs=ResourceBundleTools.rb.getString("SCMS.SERVERADDR").split(",");
	
	private static AbstractMessage sendMsg(AbstractMessage msg){
		int seq=msg.getHeader().getSeq();
		ClusterChannelHelp.putSendMsg(msg);
		return ClusterChannelHelp.getMsg(""+msg.getHeader().getServiceaddr(),seq);
	}
	
	/**
	 * 该实现方式为发送消息并等待返回,适用与向scms发送消息,并等待返回的实现方式
	 * 否则考虑直接ClusterChannelHelp.putSendMsg(msg),然后通过线程从ClusterChannelHelp.getMsg获取数据包进行返回或超时返回
	 * 
	 * 该方法具有集群选路功能，默认为配置的第一项；如果未返回，则自动遍历一遍进行选路。如果遍历后，依然未获取数据，则选路失败返回null；否则选路成功并正常返回。
	 * 
	 * @param msg
	 * @return
	 */	
	public static AbstractMessage sendMsgCluster(AbstractMessage msg){
		System.out.println("i:"+icluster);
		if(icluster>serviceaddrs.length)
			icluster=0;
		
		msg.getHeader().setServiceaddr(serviceaddrs[icluster]);//初始选用
		
		AbstractMessage ret=Sender.sendMsg(msg);
		
		if(ret!=null){
			return ret;
		}
		
		for(int i=0;i<serviceaddrs.length;i++){//遍历所有
			
			if(i==icluster)
				continue;
			
			msg.getHeader().setServiceaddr(serviceaddrs[i]);
			
			ret=Sender.sendMsg(msg);
			
			if(ret!=null){
				icluster=i;
				return ret;
			}else{
				continue;
			}
		}
		
		return null;
	}
	
}
