package com.hangtoo.bossp.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import com.hangtoo.bossp.util.Constants;


public class AddAccountRspDecoder extends AbstractMessageDecoder {
	public AddAccountRspDecoder() {
		//super(Constants.COMMAND_ID_ADDACCOUNT_RSP);
		super(AddAccountRspMessage.class);
	}

	@Override
	protected AbstractMessage decodeBody(ChannelHandlerContext ctx, ByteBuf in,Header header) {
		if(header.getType()!=Constants.COMMAND_ID_ADDACCOUNT_RSP){
			return null;
		}
        if (in.readableBytes() < length-Constants.LENGTH_HEADER) {
            return null;
        }
		
        byte result=in.readByte();
		
		AddAccountRspMessage _message = new AddAccountRspMessage();

		_message.setResult(result);

		return _message;
	}

	public void dispose() throws Exception {
	}

}