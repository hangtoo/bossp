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

import org.apache.log4j.Logger;

import com.hangtoo.bossp.codec.HandleReqMessage;
import com.hangtoo.bossp.codec.HandleRspMessage;

/**
 * {@link IoHandler} for SumUp server.
 * 
 * @author The Apache Directory Project (mina-dev@directory.apache.org)
 * @version $Rev: 555855 $, $Date: 2009/05/25 06:48:50 $
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
	private static final Logger log = Logger.getLogger(ClientHandler.class);
	
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
    	
    	if(msg instanceof HandleReqMessage){
    		HandleReqMessage message=(HandleReqMessage)msg;
    		
    		HandleRspMessage retmsg=new HandleRspMessage();
        	retmsg.getHeader().setSeq(message.getHeader().getSeq());
        	ctx.writeAndFlush(retmsg);
    	}
    	
    	//log.debug("ServerHandler channelRead and write:"+retmsg);
        log.debug("ServerHandler channelRead:"+msg);
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
    
}