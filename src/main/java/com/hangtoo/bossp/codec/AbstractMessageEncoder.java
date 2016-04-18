package com.hangtoo.bossp.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import org.apache.log4j.Logger;

public abstract class AbstractMessageEncoder<T> extends MessageToByteEncoder<T> {
	private Logger log = Logger.getLogger(getClass());

	protected AbstractMessageEncoder() {
	}

	protected abstract void encodeBody(ChannelHandlerContext ctx,T msg, ByteBuf buf);
	
	@Override
	protected void encode(ChannelHandlerContext ctx, T msg,
			ByteBuf out) throws Exception {
		
		Header header = ((AbstractMessage)msg).getHeader();
		
	    // Encode a header
		out.writeShort(header.getLength());

		out.writeByte(header.getType());
	    
		out.writeInt(header.getSeq());
	    
		log.info(header.getLength()+":"+header.getType()+":"+header.getSeq());
	    
	    // Encode a body
	    encodeBody(ctx,msg, out);
	    
	}
	
}