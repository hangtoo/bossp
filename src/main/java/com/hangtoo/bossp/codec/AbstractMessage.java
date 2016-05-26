package com.hangtoo.bossp.codec;

import java.io.Serializable;


public abstract class AbstractMessage implements Serializable {
	private static final long serialVersionUID = 544956311354994271L;
	protected Header header ;

	protected AbstractMessage() {
		header = new Header();
		//TODO 从过来的消息中获取
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

}
