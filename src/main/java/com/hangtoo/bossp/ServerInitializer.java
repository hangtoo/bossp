/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.hangtoo.bossp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import com.hangtoo.bossp.codec.AddAccountReqDecoder;
import com.hangtoo.bossp.codec.AddAccountReqEncoder;
import com.hangtoo.bossp.codec.AddAccountRspDecoder;
import com.hangtoo.bossp.codec.AddAccountRspEncoder;
import com.hangtoo.bossp.codec.HandleReqDecoder;
import com.hangtoo.bossp.codec.HandleReqEncoder;
import com.hangtoo.bossp.codec.HandleRspDecoder;
import com.hangtoo.bossp.codec.HandleRspEncoder;

/**
 * Creates a newly configured {@link ChannelPipeline} for a server-side channel.
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {

    public ServerInitializer() {
    }

    @Override
    public void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        
        pipeline.addLast(new HandleReqDecoder());
        pipeline.addLast(new HandleRspEncoder());
        
        pipeline.addLast(new HandleReqEncoder());
        pipeline.addLast(new HandleRspDecoder());
        
        pipeline.addLast(new AddAccountReqDecoder());
        pipeline.addLast(new AddAccountRspEncoder());
        
        pipeline.addLast(new AddAccountReqEncoder());
        pipeline.addLast(new AddAccountRspDecoder());
        
        pipeline.addLast(new IdleStateHandler(10, 5, 0));
        
        pipeline.addLast(new ServerHandler());
        
    }
}
