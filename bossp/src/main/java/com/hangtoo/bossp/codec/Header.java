package com.hangtoo.bossp.codec;



public class Header{
	short length;
	byte type;
	int seq;
	
	String serviceaddr;
	
	public Header(){}
	public short getLength() {
		return length;
	}
	public void setLength(short length) {
		this.length = length;
	}
	public byte getType() {
		return type;
	}
	public void setType(byte type) {
		this.type = type;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getServiceaddr() {
		return serviceaddr;
	}
	public void setServiceaddr(String serviceaddr) {
		this.serviceaddr = serviceaddr;
	}
	
	@Override
	public String toString(){
		StringBuilder ret=new StringBuilder();
		ret.append("{length:"+length+",");
		ret.append("{type:"+type+",");
		ret.append("{seq:"+seq+"}");
		
		return ret.toString();
	}
}