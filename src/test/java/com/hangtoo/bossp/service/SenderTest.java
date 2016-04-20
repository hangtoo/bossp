package com.hangtoo.bossp.service;

import org.junit.Test;

import com.hangtoo.bossp.util.Constants;
import com.hangtoo.bossp.codec.AbstractMessage;
import com.hangtoo.bossp.codec.HandleReqMessage;

public class SenderTest {

	@Test
	public void testSendMsgCluster() {
		AbstractMessage ret=null;
		
		HandleReqMessage msg= new HandleReqMessage();
		
		msg.getHeader().setSeq(Constants.getMsgId());
		
		ret=Sender.sendMsgCluster(msg);
		System.out.println("----------"+ret.getHeader().getSeq()+":"+ret.getHeader().getType());
	}

}
