package com.hangtoo.bossp.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.internal.TypeParameterMatcher;

import java.util.List;

import org.apache.log4j.Logger;

public abstract class AbstractMessageDecoder extends ByteToMessageDecoder  {
/*	private final byte id;*/

	protected short length;

	private boolean readHeader;

	private Header header = new Header();

	private Logger log = Logger.getLogger(getClass());
	
	private final TypeParameterMatcher outboundMsgMatcher;

/*	protected AbstractMessageDecoder(byte id) {
		this.id = id;	
	}*/
	
	protected AbstractMessageDecoder(Class<? extends AbstractMessage> outboundMessageType) {
		this.outboundMsgMatcher=TypeParameterMatcher.get(outboundMessageType);
	}
	

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {
		
		// TODO need to add headlength check
        if (in.readableBytes() < 2) {
            return;
        }
		
		if (!readHeader) {
			length=in.readShort();//设置长度
			byte type=in.readByte();
			int seq=in.readInt();
			
			header.setLength(length);
			header.setType(type);
			header.setSeq(seq);
			
			header.setServiceaddr(ctx.channel().remoteAddress().toString());
			
			readHeader = true;
		}
		// Try to decode body
		AbstractMessage m = decodeBody(ctx, in,header);
		// Return NEED_DATA if the body is not fully read.
		if (m == null) {
			return;
		} else {
			readHeader = false; // reset readHeader for the next decode
		}
		
		out.add(m);
		
		log.info(header.getLength()+":"+header.getType()+":"+header.getSeq());
	}

	/**
	 * @return <tt>null</tt> if the whole body is not read yet
	 */
	protected abstract AbstractMessage decodeBody(ChannelHandlerContext ctx, ByteBuf in,Header header);
	

}
