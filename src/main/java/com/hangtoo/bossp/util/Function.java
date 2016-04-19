package com.hangtoo.bossp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Function {
	
	public static byte[] string2scii(String src){
		
		char[] srcbyte=src.toCharArray();
		byte[] ret=new byte[srcbyte.length];
		
		for(int i=0;i<srcbyte.length;i++){
			ret[i]=new Integer((int)srcbyte[i]).byteValue();
		}
		return ret;
	}
	
	public static byte[] formatString(String src, int length) {//���㲿�ֲ���ո�
		
		char[] srcbyte=src.toCharArray();
		byte[] ret = new byte[length];
		
		for (int i = 0; i < ret.length; i++) {
			if (i < srcbyte.length) {
				ret[i] = new Integer((int)srcbyte[i]).byteValue();
			} else {
				ret[i] = 0x20;
			}
		}

		return ret;
	}
	
	public static byte[] formatString0(String src, int length) {//���㲿��ǰ�油��0
		
		char[] srcbyte=src.toCharArray();
		byte[] ret = new byte[length];
		
		for(int i=0;i<ret.length;i++){
			ret[i] = 0x30;
		}
		
		for (int i = ret.length-srcbyte.length; i < ret.length; i++) {
			ret[i] = new Integer((int)srcbyte[i-ret.length+srcbyte.length]).byteValue();
		}

		return ret;
	}
	
	public static String getFormateDate(Date date,String format) {
		SimpleDateFormat matter = new SimpleDateFormat(format);
		String dateStr = matter.format(date);
		return dateStr;
	}
	
	public static String getServeraddr(String ip,int port) {
		return "/"+ip+":"+port;
	}
	
	public static String getHost(String serveraddr) {
		String ip=serveraddr.substring(1,serveraddr.indexOf(":"));
		return ip;
	}
	
	public static int getPort(String serveraddr) {
		String port=serveraddr.substring(serveraddr.indexOf(":")+1);
		return Integer.valueOf(port);
	}
	
    public static String trim(String str) {
        return str.replaceAll("\n", "").replaceAll("\t", "").replaceAll(" ", "");
    }
	
    public static boolean isNullorEmpty(String str) {
        if ((str == null) || "".equalsIgnoreCase(trim(str)) ||
                "null".equalsIgnoreCase(trim(str).toLowerCase())) {
            return true;
        } else {
            return false;
        }
    }
}
