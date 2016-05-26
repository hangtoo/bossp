package com.hangtoo.bossp.codec;

import com.hangtoo.bossp.util.Constants;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class HandleRspDecoder extends AbstractMessageDecoder {
	public HandleRspDecoder() {
		//super(Constants.COMMAND_ID_HAND_RSP);
		super(HandleRspMessage.class);
	}

	@Override
	protected AbstractMessage decodeBody(ChannelHandlerContext ctx, ByteBuf in,Header header) {
		if(header.getType()!=Constants.COMMAND_ID_HAND_RSP){
			return null;
		}
		HandleRspMessage _message = new HandleRspMessage();
		_message.setHeader(header);
		return _message;
	}

	public void dispose() throws Exception {
	}

}