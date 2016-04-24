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
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.net.InetSocketAddress;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.hangtoo.bossp.codec.AbstractMessage;
import com.hangtoo.bossp.codec.HandleReqDecoder;
import com.hangtoo.bossp.codec.HandleReqEncoder;
import com.hangtoo.bossp.codec.HandleRspDecoder;
import com.hangtoo.bossp.codec.HandleRspEncoder;
import com.hangtoo.bossp.util.ClusterChannelHelp;
import com.hangtoo.bossp.util.Function;

public class Client {

	private static final Logger log = Logger.getLogger(Client.class);

	private String remoteHost;

	private int remotePort;

	private volatile EventLoopGroup workerGroup;
	private volatile Bootstrap bootstrap;
	private volatile boolean closed = false;

	private ChannelFuture future;

	private static Map<String,Client> clients = new Hashtable<String,Client>();

	public static Client getClient(String serveraddr){
		Client theclient=clients.get(serveraddr);
		
		if(theclient==null){
			try {
				theclient=new Client(Function.getHost(serveraddr),Function.getPort(serveraddr));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			clients.put(serveraddr, theclient);
		}
		return theclient;
	}
	
	private Client(String hostname, int port) throws InterruptedException {
		remoteHost = hostname;
		remotePort = port;
		this.init();
	}

	public void close() {
		closed = true;
		workerGroup.shutdownGracefully();
		log.info("Stopped Tcp Client: " + getServerInfo());
	}

	public void init() {
		closed = false;

		workerGroup = new NioEventLoopGroup();
		bootstrap = new Bootstrap();
		bootstrap.group(workerGroup)
			.channel(NioSocketChannel.class)
			.handler(new ClientInitializer());
		doConnect();
	}

	public void doConnect() {
		if (closed) {
			return;
		}
		this.future = bootstrap.connect(new InetSocketAddress(
				remoteHost, remotePort));

		this.future.addListener(new ChannelFutureListener() {
			public void operationComplete(ChannelFuture f) throws Exception {
				if (f.isSuccess()) {
					log.info("Started Tcp Client: " + getServerInfo());
				} else {
					log.info("Started Tcp Client Failed: " + getServerInfo());

					f.channel().eventLoop().schedule(new Runnable() {
						public void run() {
							doConnect();
						}
					}, 1, TimeUnit.SECONDS);
				}
			}
		});

	}

	public void write(AbstractMessage msg) {
		log.info(msg.getHeader().getSeq());
		future.channel().writeAndFlush(msg);
	}

	public Channel getChannel() {
		if(future!=null)
			return future.channel();
		return null;
	}

	private String getServerInfo() {
		return String.format("RemoteHost=%s RemotePort=%d", remoteHost,
				remotePort);
	}
}
