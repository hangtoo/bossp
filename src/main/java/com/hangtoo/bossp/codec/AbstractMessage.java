package com.hangtoo.bossp.codec;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;


public abstract class AbstractMessage implements Serializable {
	private static final long serialVersionUID = 544956311354994271L;
	protected Header header ;

	protected AbstractMessage() {
		header = new Header();
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
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
	    
	    sb.append("header:");
	    sb.append(this.getHeader().toString());
	    
	    sb.append("}");  
	    return sb.toString();  
	}
}
