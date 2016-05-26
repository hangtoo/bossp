package com.hangtoo.bossp.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import com.hangtoo.bossp.util.Constants;

public class HandleReqDecoder extends AbstractMessageDecoder {
	public HandleReqDecoder() {
		//super(Constants.COMMAND_ID_HAND_REQ);
		super(HandleReqMessage.class);
	}

	@Override
	protected AbstractMessage decodeBody(ChannelHandlerContext ctx, ByteBuf in,Header header) {
		
		if(header.getType()!=Constants.COMMAND_ID_HAND_REQ){
			return null;
		}
		
		HandleReqMessage _message = new HandleReqMessage();
		_message.setHeader(header);
		
		return _message;
	}

	public void dispose() throws Exception {
	}

}