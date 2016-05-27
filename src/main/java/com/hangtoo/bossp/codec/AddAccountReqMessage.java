package com.hangtoo.bossp.codec;

import com.hangtoo.bossp.util.Constants;


public class AddAccountReqMessage extends AbstractMessage {
	
	private static final long serialVersionUID = -2570246432526437850L;
	private int phonelength;
	private byte[] phone;
	private int imsilength;
	private byte[] imsi;
	private byte templet;
	
	private int notelength;
	private byte[] note;
	
	public AddAccountReqMessage() {
		super();
		header.setType(Constants.COMMAND_ID_ADDACCOUNT_REQ);
		header.setLength((short)(Constants.LENGTH_HEADER+4+phonelength+4+imsilength+1+4+notelength));
	}

	public int getPhonelength() {
		return phonelength;
	}

	public void setPhonelength(int phonelength) {
		this.phonelength = phonelength;
	}

	public byte[] getPhone() {
		return phone;
	}

	public void setPhone(byte[] phone) {
		this.phone = phone;
	}

	public int getImsilength() {
		return imsilength;
	}

	public void setImsilength(int imsilength) {
		this.imsilength = imsilength;
	}

	public byte[] getImsi() {
		return imsi;
	}

	public void setImsi(byte[] imsi) {
		this.imsi = imsi;
	}

	public byte getTemplet() {
		return templet;
	}

	public void setTemplet(byte templet) {
		this.templet = templet;
	}

	public int getNotelength() {
		return notelength;
	}

	public void setNotelength(int notelength) {
		this.notelength = notelength;
	}

	public byte[] getNote() {
		return note;
	}

	public void setNote(byte[] note) {
		this.note = note;
	}
}
