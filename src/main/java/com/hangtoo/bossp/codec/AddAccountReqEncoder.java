package com.hangtoo.bossp.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;



public class AddAccountReqEncoder extends AbstractMessageEncoder<AddAccountReqMessage> {

	public AddAccountReqEncoder() {
		super();
	}

	@Override
	protected void encodeBody(ChannelHandlerContext ctx,AddAccountReqMessage message, ByteBuf out) {
		out.writeInt(message.getPhonelength());
		if(message.getPhonelength()!=0)
			out.writeBytes(message.getPhone());
		
		out.writeInt(message.getImsilength());
		if(message.getImsilength()!=0)
			out.writeBytes(message.getImsi());
		
		out.writeByte(message.getTemplet());
		
		out.writeInt(message.getNotelength());
		if(message.getNotelength()!=0)
			out.writeBytes(message.getNote());
		
	}


}