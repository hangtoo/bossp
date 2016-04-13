package com.hangtoo.bossp.codec;

import java.io.Serializable;


public abstract class AbstractMessage implements Serializable {
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
