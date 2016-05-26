package com.hangtoo.bossp.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;


public class AddAccountRspEncoder extends AbstractMessageEncoder<AddAccountRspMessage> {

	public AddAccountRspEncoder() {
		super();
	}

	@Override
	protected void encodeBody(ChannelHandlerContext ctx,AddAccountRspMessage message, ByteBuf out) {
		out.writeByte(message.getResult());
	}

}