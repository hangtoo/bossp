package com.hangtoo.bossp.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;


public class HandleRspEncoder extends AbstractMessageEncoder<HandleRspMessage> {

	public HandleRspEncoder() {
		super();
	}

	@Override
	protected void encodeBody(ChannelHandlerContext ctx,HandleRspMessage msg, ByteBuf buf) {
		//System.out.println("HandleRspEncoder:"+msg);
	}

}