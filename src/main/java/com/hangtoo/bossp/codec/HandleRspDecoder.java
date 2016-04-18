package com.hangtoo.bossp.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class HandleRspDecoder extends AbstractMessageDecoder {
	public HandleRspDecoder() {
		//super(Constants.COMMAND_ID_HAND_RSP);
		super(HandleRspMessage.class);
	}

	@Override
	protected AbstractMessage decodeBody(ChannelHandlerContext ctx, ByteBuf in,Header header) {

		HandleRspMessage _message = new HandleRspMessage();
		_message.setHeader(header);
		return _message;
	}

	public void dispose() throws Exception {
	}

}