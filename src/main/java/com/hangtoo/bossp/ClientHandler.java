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

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.hangtoo.bossp.codec.AbstractMessage;
import com.hangtoo.bossp.util.ClusterChannelHelp;


public class ClientHandler extends ChannelInboundHandlerAdapter {
	private static final Logger log = Logger.getLogger(ClientHandler.class);
	
	@Override
	public void channelInactive(
			ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);
		final String remoteAddress=ctx.channel().remoteAddress().toString();
		ctx.channel().eventLoop()
				.schedule(new Runnable() {
					public void run() {
						Client.getClient(remoteAddress).doConnect();
					}
				}, 1, TimeUnit.SECONDS);
	}
	
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        
        log.debug("channelRegistered");
    }
	
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	log.debug("channelActive");
    }
    
    /**
     * Calls {@link ChannelHandlerContext#fireChannelRead(Object)} to forward
     * to the next {@link ChannelInboundHandler} in the {@link ChannelPipeline}.
     *
     * Sub-classes may override this method to change behavior.
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.debug("channelRead:"+msg);
        
        if(msg instanceof AbstractMessage){
        	ClusterChannelHelp.putMsg((AbstractMessage)msg);
        }
    }

    /**
     * Calls {@link ChannelHandlerContext#fireChannelReadComplete()} to forward
     * to the next {@link ChannelInboundHandler} in the {@link ChannelPipeline}.
     *
     * Sub-classes may override this method to change behavior.
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.debug("channelReadComplete");
        ctx.flush();
    }
    
    @Override
	public void exceptionCaught(
			ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		//super.exceptionCaught(ctx,cause);
		final String remoteAddress=ctx.channel().remoteAddress().toString();
		ctx.channel().eventLoop()
				.schedule(new Runnable() {
					public void run() {
						Client.getClient(remoteAddress).doConnect();
					}
				}, 1, TimeUnit.SECONDS);
		
	}
    
    @Override
    public void userEventTriggered(
            ChannelHandlerContext ctx, Object evt)
            throws Exception {
        if(IdleStateEvent.class.isAssignableFrom(evt.getClass())){
            IdleStateEvent event = (IdleStateEvent) evt;
            if(event.state() == IdleState.READER_IDLE)
            	log.info("read idle");
            else if(event.state() == IdleState.WRITER_IDLE){
/*                log.info("write idle");
            
	        	HandleReqMessage msg=new HandleReqMessage();
	        	msg.getHeader().setSeq(Constants.getMsgId());
	        	//log.debug("ClientHandler:"+msg);
	        	ctx.writeAndFlush(msg);
*/            
            }else if(event.state() == IdleState.ALL_IDLE){
            	log.info("all idle");
            }
                
        }
    }
}