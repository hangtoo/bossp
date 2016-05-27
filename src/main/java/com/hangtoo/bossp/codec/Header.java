package com.hangtoo.bossp.codec;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    StringBuffer sb = new StringBuffer();  
	    sb.append("{");  
	    Field[] fields = this.getClass().getDeclaredFields();  
	    for (Field field : fields) {
	        try {  
	            field.setAccessible(true);  
	            sb.append(field.getName());  
	            sb.append(":");  
	            if (field.get(this) instanceof Date) {  
	                // 日期的处理  
	                sb.append(sdf.format(field.get(this)));  
	            } else {  
	                sb.append(field.get(this));  
	            }  
	            sb.append(",");  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    }
	    
	    sb.append("}");  
	    return sb.toString();  
	}
}