package com.hangtoo.bossp.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;



public class HandleReqEncoder extends AbstractMessageEncoder<HandleReqMessage> {

	public HandleReqEncoder() {
		super();
	}

	@Override
	protected void encodeBody(ChannelHandlerContext ctx,HandleReqMessage msg, ByteBuf buf) {
		//System.out.println("HandleReqEncoder:"+msg);
	}



}