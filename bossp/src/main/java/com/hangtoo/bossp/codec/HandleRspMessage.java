package com.hangtoo.bossp.codec;

import com.hangtoo.bossp.Constants;





public class HandleRspMessage extends AbstractMessage {
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
