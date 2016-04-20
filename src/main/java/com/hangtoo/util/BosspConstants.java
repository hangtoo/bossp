package com.hangtoo.util;

public class BosspConstants {
	public static interface BOSSP_RET {
		   byte BOSSP_RET_SUCCESS = 0x00; 
		   byte BOSSP_RET_FAIL_NORECORD = (byte)0x01;
		   byte BOSSP_RET_FAIL_PARAERROR = (byte)0x02;
		   byte BOSSP_RET_FAIL_UNVALIDUSER = (byte)0x03;
		   byte BOSSP_RET_FAIL_DBERROR = (byte)0x04;
		  
		   byte BOSSP_RET_FAIL_PHONEUSED = (byte)0x05;
		   byte BOSSP_RET_FAIL_OTHRE = (byte)0x0F;
	}
	
	public static interface SP_RET {
		   byte SP_RET_SUCCESS = 0x00;
		   
		   byte SP_RET_FAIL_TERTYPE = (byte)0x10;
		   byte SP_RET_FAIL_TERID = (byte)0x11;
		   byte SP_RET_FAIL_USERNUMBER = (byte)0x12;
		   byte SP_RET_FAIL_USERTYPE = (byte)0x13;
		   byte SP_RET_FAIL_PRODID = (byte)0x14;
		   byte SP_RET_FAIL_SUITID = (byte)0x15;
		   byte SP_RET_FAIL_LINKID = (byte)0x16;
		   byte SP_RET_FAIL_HASFEATURESTR = (byte)0x17;
		   byte SP_RET_FAIL_FEATURESTR = (byte)0x18;
		   byte SP_RET_FAIL_ID = (byte)0x19;
		   
		   byte SP_RET_FAIL_IDTYPE = (byte)0x20;
		   byte SP_RET_FAIL_STATUS = (byte)0x21;
		   
		   byte SP_RET_FAIL_OPTYPE = (byte)0x22;
		   
		   byte SP_RET_FAIL_OTHRE = (byte)0x99;
	}
	
	
}
