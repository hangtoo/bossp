package com.hangtoo.bossp.codec;

import com.hangtoo.bossp.util.Constants;





public class HandleRspMessage extends AbstractMessage {
	private static final long serialVersionUID = 6465815883272847971L;

	public HandleRspMessage() {
		super();
		header.setType(Constants.COMMAND_ID_HAND_RSP);
		header.setLength(Constants.LENGTH_HEADER);
	}
	
	@Override
	public String toString(){
		StringBuilder ret=new StringBuilder();
		ret.append("HandleRspMessage:{head:"+header.toString()+"}");
		
		return ret.toString();
	}
	

}
