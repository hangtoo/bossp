package com.hangtoo.bossp.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import com.hangtoo.bossp.util.Constants;

public class AddAccountReqDecoder extends AbstractMessageDecoder {
	public AddAccountReqDecoder() {
		//super(Constants.COMMAND_ID_ADDACCOUNT_REQ);
		super(AddAccountReqMessage.class);
	}

	@Override
	protected AbstractMessage decodeBody(ChannelHandlerContext ctx, ByteBuf in,Header header) {
		if(header.getType()!=Constants.COMMAND_ID_ADDACCOUNT_REQ){
			return null;
		}
		
		if (in.readableBytes() < length-Constants.LENGTH_HEADER) {
			return null;
		}
		
		int phonelength=in.readInt();
		
		byte[] phone=new byte[phonelength];
		in.readBytes(phone);
		
		int imsilength=in.readInt();
		
		byte[] imsi=new byte[imsilength];
		in.readBytes(imsi);
		
		byte templet=in.readByte();
		
		int notelength=in.readInt();
		
		byte[] note=new byte[notelength];
		in.readBytes(note);
		
		AddAccountReqMessage _message = new AddAccountReqMessage();

		_message.setPhonelength(phonelength);
		_message.setPhone(phone);
		_message.setImsilength(imsilength);
		_message.setImsi(imsi);
		
		_message.setTemplet(templet);
		_message.setNotelength(notelength);
		_message.setNote(note);
		return _message;

	}

	public void dispose() throws Exception {
	}

}