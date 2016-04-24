package com.hangtoo.bossp.util;

import io.netty.channel.Channel;

import java.util.Hashtable;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hangtoo.bossp.Client;
import com.hangtoo.bossp.codec.AbstractMessage;

/**
 * 
 * @author huanglf
 * 
 * 发送线程
 */
public class SendThread extends Thread {
	private boolean isStop = false;
	private Logger log = Logger.getLogger(getClass());
	private Client client = null;
	
	public SendThread(String hostname,int port,int connecttimeout) throws InterruptedException {
		String serveraddr=Function.getServeraddr(hostname, port);
		Client theclient=Client.getClient(serveraddr);
		
		this.client=theclient;
	}
	
	public SendThread(String serveraddr) throws InterruptedException {
		Client theclient=Client.getClient(serveraddr);
		
		this.client=theclient;
	}
	
	public void stopReceiveThread() {
		isStop = true;
	}

	@Override
	public void run() {
		while (!isStop) {
			try {
				Channel channel=client.getChannel();
				
				if(channel==null||!channel.isWritable()){
					this.sleep(Constants.SLEEPTIME);
					continue;
				}
				
				Object obj = ClusterChannelHelp.getSendMsg(channel.remoteAddress().toString());
				
				if(obj instanceof AbstractMessage){
					client.write((AbstractMessage)obj);
				}
			} catch (Exception e) {
				log.error(e);
			}
		}
	}
}
