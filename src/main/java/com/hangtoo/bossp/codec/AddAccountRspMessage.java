package com.hangtoo.bossp.codec;

import com.hangtoo.bossp.util.Constants;


public class AddAccountRspMessage extends AbstractMessage {
	private static final long serialVersionUID = -4243750607274670626L;
	byte result;
    public AddAccountRspMessage() {
        super();
        header.setType(Constants.COMMAND_ID_ADDACCOUNT_RSP);
        header.setLength(Constants.LENGTH_HEADER);
    }
	public byte getResult() {
		return result;
	}
	public void setResult(byte result) {
		this.result = result;
	}
}
