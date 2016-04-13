package com.hangtoo.bossp;



public class Constants {

  public final static short LENGTH_HEADER = 7;
  
  public final static byte COMMAND_ID_ADDACCOUNT_REQ = 0x01; 
  public final static byte COMMAND_ID_ADDACCOUNT_RSP = (byte)0x81;

  
  public final static byte COMMAND_ID_DELETEACCOUNT_REQ = 0x02; 
  public final static byte COMMAND_ID_DELETEACCOUNT_RSP = (byte)0x82;
  
  
  public final static byte COMMAND_ID_BATCHADDACCOUNT_REQ = 0x03; 
  public final static byte COMMAND_ID_BATCHADDACCOUNT_RSP = (byte)0x83;
  
  public final static byte COMMAND_ID_BATCHDELETEACCOUNT_REQ = 0x04; 
  public final static byte COMMAND_ID_BATCHDELETEACCOUNT_RSP = (byte)0x84;
  
  public final static byte COMMAND_ID_BATCHQUERYACCOUNT_REQ = 0x05; 
  public final static byte COMMAND_ID_BATCHQUERYACCOUNT_RSP = (byte)0x85;
  
  public final static byte COMMAND_ID_ANTIADDACCOUNT_REQ = 0x06; 
  public final static byte COMMAND_ID_ANTIADDACCOUNT_RSP = (byte)0x86;
  
  public final static byte COMMAND_ID_ANTIDELETEACCOUNT_REQ = 0x07; 
  public final static byte COMMAND_ID_ANTIDELETEACCOUNT_RSP = (byte)0x87;
  
  public final static byte COMMAND_ID_ANTIRETACCOUNT_REQ = 0x08; 
  public final static byte COMMAND_ID_ANTIRETACCOUNT_RSP = (byte)0x88;
  
  public final static byte COMMAND_ID_FORBID_REQ = 0x09; 
  public final static byte COMMAND_ID_FORBID_RSP = (byte)0x89;
  
  public final static byte COMMAND_ID_CHANGEINF_REQ = 0x0A; 
  public final static byte COMMAND_ID_CHANGEINF_RSP = (byte)0x8A;
  
  public final static byte COMMAND_ID_HAND_REQ = 0x0F; 
  public final static byte COMMAND_ID_HAND_RSP = (byte)0x8F;

  public static final int STACKLSIZE = 256;
  public static final long TIMEOUTTIME = 11 * 1000; //��ݻ�ȡ��ʱʱ��
  public static final long DATEREADWAITTIME = 3*10; //��ݻ�ȡ�ȴ�ʱ��
  
  public static final long POPTIME = 3 * 100; //��ݻ�ȡ��ʱʱ��
  
  public static final long SLEEPTIME = 3*100;
  
  public static final int MINMSGID = 0x01000000;
  public static final int MAXMSGID = 0x01FFFFFF;
  public static int msgId = MINMSGID;
  public static int getMsgId() { //������һ��msgId����ֵ

      if (msgId > MAXMSGID) {
          msgId = MINMSGID;
      }

      return msgId++;
  }
  
  public static final int SMINMSGID = 0x00000000;
  public static final int SMAXMSGID = 0x00FFFFFF;
  public static int smsgId = MINMSGID;
  public static int getSMsgId() { //������һ��msgId����ֵ

      if (smsgId > SMAXMSGID) {
          smsgId = SMINMSGID;
      }

      return smsgId++;
  }
  
}