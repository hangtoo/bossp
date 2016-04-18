package com.hangtoo.bossp.codec;

import com.hangtoo.bossp.Constants;


public class HandleReqMessage extends AbstractMessage {
	public HandleReqMessage() {
		super();
		header.setType(Constants.COMMAND_ID_HAND_REQ);
		header.setLength(Constants.LENGTH_HEADER);
	}
	
	@Override
	public String toString(){
		StringBuilder ret=new StringBuilder();
		ret.append("HandleReqMessage:{head:"+header.toString()+"}");
		
		return ret.toString();
	}
	
	
}
