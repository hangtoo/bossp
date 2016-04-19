package com.hangtoo.bossp.util;

import java.util.Hashtable;
import java.util.Map;

import com.hangtoo.bossp.codec.AbstractMessage;

/**
 * <p>
 * Title: 
 * </p>
 * <p>
 * Copyright: Copyright (c) 2009
 * </p>
 * <p>
 * Company: eastcom
 * </p>
 */

public class GlobeData {
	
	public static Map<String,SessionHelp> map=new Hashtable<String,SessionHelp>();
	
	public static void putSendMsg(AbstractMessage msg){
		String serviceaddr=msg.getHeader().getServiceaddr();
		SessionHelp sessionhelp=map.get(serviceaddr);
		
		if(sessionhelp==null){
			sessionhelp=new SessionHelp(serviceaddr);
			if (!sessionhelp.sendthread.isAlive())
				sessionhelp.sendthread.start();
			map.put(serviceaddr, sessionhelp);
		}
		
		sessionhelp.putSendMsg(msg);
	}
	
	public static Object getSendMsg(String serviceaddr){
		SessionHelp sessionhelp=map.get(serviceaddr);
		
		if(sessionhelp==null){
			sessionhelp=new SessionHelp(serviceaddr);
			map.put(serviceaddr, sessionhelp);
		}
		
		return sessionhelp.getSendMsg();
	}
	
	public static AbstractMessage getMsg(String serviceaddr,int seq) {//该方法需要等待返回
		
		SessionHelp sessionhelp=map.get(serviceaddr);
		
		if(sessionhelp==null){
			sessionhelp=new SessionHelp(serviceaddr);
			map.put(serviceaddr, sessionhelp);
		}
		
		return sessionhelp.getMsg(seq);
	}
	
	public static AbstractMessage popMsg(String serviceaddr) {//该方法需要等待返回
		
		SessionHelp sessionhelp=map.get(serviceaddr);
		
		if(sessionhelp==null){
			sessionhelp=new SessionHelp(serviceaddr);
			map.put(serviceaddr, sessionhelp);
		}
		
		return sessionhelp.popMsg();
	}
	
	public static void putMsg(AbstractMessage msg){//该方法会马上返回
		
		String serviceaddr=msg.getHeader().getServiceaddr();
		SessionHelp sessionhelp=map.get(serviceaddr);
		
		if(sessionhelp==null){
			sessionhelp=new SessionHelp(serviceaddr);
			if (!sessionhelp.sendthread.isAlive())
				sessionhelp.sendthread.start();
			map.put(serviceaddr, sessionhelp);
		}
		
		sessionhelp.putMsg(msg);
	}
}
