package com.hangtoo.bossp.util;

import java.util.Hashtable;
import java.util.Map;

import com.hangtoo.bossp.codec.AbstractMessage;

/**
 * 
 * @author hlf
 *
 */

public class ClusterChannelHelp {
	
	public static Map<String,ChannelHelp> map=new Hashtable<String,ChannelHelp>();
	
	/**
	 * 根据消息的serviceaddr，获取channelhelp,放入发送缓存
	 * @param msg
	 */
	public static void putSendMsg(AbstractMessage msg){
		String serviceaddr=msg.getHeader().getServiceaddr();
		ChannelHelp channelhelp=map.get(serviceaddr);
		
		if(channelhelp==null){
			channelhelp=new ChannelHelp(serviceaddr);
			if (!channelhelp.sendthread.isAlive())
				channelhelp.sendthread.start();
			map.put(serviceaddr, channelhelp);
		}
		
		channelhelp.putSendMsg(msg);
	}

	
	
	public static Object getSendMsg(String serviceaddr){
		ChannelHelp channelhelp=map.get(serviceaddr);
		
		if(channelhelp==null){
			channelhelp=new ChannelHelp(serviceaddr);
			map.put(serviceaddr, channelhelp);
		}
		
		return channelhelp.getSendMsg();
	}
	
	/**
	 * 获取指定seq的返回数据，该方法需要等待返回，直到超时
	 * @param serviceaddr
	 * @param seq
	 * @return
	 */
	public static AbstractMessage getMsg(String serviceaddr,int seq) {
		
		ChannelHelp channelhelp=map.get(serviceaddr);
		
		if(channelhelp==null){
			channelhelp=new ChannelHelp(serviceaddr);
			map.put(serviceaddr, channelhelp);
		}
		
		return channelhelp.getMsg(seq);
	}
	
	/**
	 * 返回一条数据，如果没有会等待一段时间，但不会阻塞
	 * @param serviceaddr
	 * @return
	 */
	@Deprecated
	public static AbstractMessage popMsg(String serviceaddr) {
		
		ChannelHelp channelhelp=map.get(serviceaddr);
		
		if(channelhelp==null){
			channelhelp=new ChannelHelp(serviceaddr);
			map.put(serviceaddr, channelhelp);
		}
		
		return channelhelp.popMsg();
	}
	
	public static void putMsg(AbstractMessage msg){//该方法会马上返回
		
		String serviceaddr=msg.getHeader().getServiceaddr();
		ChannelHelp channelhelp=map.get(serviceaddr);
		
		if(channelhelp==null){
			channelhelp=new ChannelHelp(serviceaddr);
			if (!channelhelp.sendthread.isAlive())
				channelhelp.sendthread.start();
			map.put(serviceaddr, channelhelp);
		}
		
		channelhelp.putMsg(msg);
	}
}
