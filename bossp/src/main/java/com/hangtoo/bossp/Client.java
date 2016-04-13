/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *  
 *    http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License. 
 *  
 */
package com.hangtoo.bossp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import org.apache.log4j.Logger;

import com.hangtoo.bossp.codec.AbstractMessage;

public class Client {
	
	private static final Logger log = Logger.getLogger(Client.class);
	
	private int CONNECT_TIMEOUT;
	
	private String HOSTNAME;
	
	private int PORT;
	
	EventLoopGroup group = new NioEventLoopGroup();
	Bootstrap b = new Bootstrap();
	ChannelFuture f;
	
	Channel channel;
	
	public Client(String hostname,int port,int connecttimeout) throws InterruptedException{

		HOSTNAME=hostname;
		
		PORT=port;
		
		CONNECT_TIMEOUT=connecttimeout;
        
        b.group(group)
        .channel(NioSocketChannel.class)
        //.handler(new LoggingHandler(LogLevel.INFO))
        .handler(new ClientInitializer());

	}
	
	public void connect() throws InterruptedException{
		log.info(HOSTNAME+":"+PORT);
        for (;;) {
            try {
                // Make a new connection.
                f = b.connect(HOSTNAME, PORT).sync();
                channel=f.channel();
                
                // Wait until the connection is closed.
                channel.closeFuture().sync();
                break;
            } finally {
            	group.shutdownGracefully();
            }
        }
	}
	
	public static void main(String[] args) throws Exception {
		final Client client =new Client("127.0.0.1",9000,1000);
		
        new Thread(){
        	public void run() {
        		try {
        			client.connect();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        	};
        }.start();
		
	}
	
	
	public void disconnect(boolean keepconnect) {
		try {
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			group.shutdownGracefully();
		}
	}
	
	public void write(AbstractMessage msg){
		log.info(msg.getHeader().getSeq());
		f.channel().writeAndFlush(msg);
	}

	public Channel getChannel() {
		return channel;
	}
}
