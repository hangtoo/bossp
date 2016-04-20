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
	
	private static Map<String,Client> clients = new Hashtable<String,Client>();

	public SendThread(String hostname,int port,int connecttimeout) throws InterruptedException {
		String serveraddr=Function.getServeraddr(hostname, port);
		Client theclient=clients.get(serveraddr);
		
		if(theclient==null){
			theclient=new Client(hostname,port,connecttimeout);
			clients.put(serveraddr, theclient);
		}
		this.client=theclient;
	}
	
	public SendThread(String serveraddr) throws InterruptedException {
		Client theclient=clients.get(serveraddr);
		
		if(theclient==null){
			theclient=new Client(Function.getHost(serveraddr),Function.getPort(serveraddr),1000);
			clients.put(serveraddr, theclient);
		}
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
				
				Object obj = ClusterChannelHelp.getSendMsg(channel.localAddress().toString());
				
				if(obj instanceof AbstractMessage){
					client.write((AbstractMessage)obj);
				}
			} catch (Exception e) {
				log.error(e);
			}
		}
	}
}
