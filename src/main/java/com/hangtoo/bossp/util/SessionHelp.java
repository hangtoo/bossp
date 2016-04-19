package com.hangtoo.bossp.util;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;

import com.hangtoo.bossp.codec.AbstractMessage;

public class SessionHelp {
	
	static Logger log = Logger.getLogger(SessionHelp.class);
	
	//消息处理线程
    public SendThread sendthread=null;
    
    public SessionHelp(String serveraddr){
    	try {
			sendthread=new SendThread(serveraddr);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    

    //发送消息缓存，其中消息处理线程会逐条获取其中的消息
    
    private BlockingQueue sendmessges=new ArrayBlockingQueue(Constants.STACKLSIZE);//服务端缓存
    
    //接收到的反馈消息缓存
    public Map<Integer, AbstractMessage> msgbuffer=new Hashtable<Integer, AbstractMessage>();//普通消息服务端缓存
    
	public void putSendMsg(Object msg){
		synchronized(sendmessges){
			try {
				sendmessges.put(msg);
				//sendmessges.push(msg);
			} catch (InterruptedException e) {
				log.error(e);
			}
			sendmessges.notifyAll();
		}
	}
	
	public Object getSendMsg(){
		long tstart=System.currentTimeMillis();
		
		Object obj = null;
		synchronized(sendmessges){
			try {
				if (sendmessges.isEmpty()){
					sendmessges.wait();
				}

				//obj=sendmessges.pop();
				obj=sendmessges.take();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				log.error(e);
			}
		}
		
		log.info("getMsg cost time："+(System.currentTimeMillis()-tstart));
		return obj;
	}
	
	/**
	 * 遍历并返回数据
	 * @return
	 */
	public AbstractMessage popMsg(){
		synchronized (msgbuffer) {
			Iterator<Map.Entry<Integer,AbstractMessage>> iter=msgbuffer.entrySet().iterator();
			if(!iter.hasNext()){
				try {
					msgbuffer.wait(Constants.POPTIME);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Map.Entry<Integer,AbstractMessage> ele=iter.next();
			
			if(ele==null)
				return null;
			
			Integer key=ele.getKey();
			AbstractMessage value=ele.getValue();
			
			msgbuffer.remove(key);
			return value;
		}
	}
	
	public AbstractMessage getMsg(int seq) {//该方法需要等待返回
		Object obj=null;
		synchronized (msgbuffer) {
			obj = msgbuffer.remove(seq);
		}
		long tstart;
		tstart = System.currentTimeMillis();
		while (obj == null) {
			if (System.currentTimeMillis() - tstart > Constants.TIMEOUTTIME) {// 超时设置
				log.warn("等待普通命令返回数据获取超时！");
				break;
			}
			try {
				Thread.sleep(Constants.DATEREADWAITTIME);// 如果有数据了，或者经历了一定的等待时间还没获取数据，认为返回数据为空
			} catch (InterruptedException e) {
				log.warn("等待普通命令返回数据失败!");
			}
			synchronized (msgbuffer) {
				obj = msgbuffer.remove(seq);
			}

		}
		log.info(
				"getMsg cost time：" + (System.currentTimeMillis() - tstart));
		return (AbstractMessage) obj;
	}
	
	public void putMsg(AbstractMessage msg){//该方法会马上返回
		synchronized (msgbuffer) {
			int seq=msg.getHeader().getSeq();
			if(msgbuffer.size()>=Constants.STACKLSIZE){
				Set keyset=msgbuffer.keySet();
				Iterator keyit=keyset.iterator();
				int key;
				Object obj;
				while(keyit.hasNext()){
					obj=keyit.next();
					if(obj instanceof Integer){
						key=(Integer)obj;
						if(key<seq-Constants.STACKLSIZE||
								(seq-Constants.STACKLSIZE<=Constants.MINMSGID&&key<seq+Constants.MAXMSGID-Constants.MINMSGID-Constants.STACKLSIZE)){//
							//msgbuffer.remove(key);
							keyit.remove();
						}
					}
				}
			}
			
			msgbuffer.put(seq,msg);
			msgbuffer.notifyAll();
		}
	}
}
