package com.prasenjit.examples.websocketapp;

import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;



import com.prasenjit.examples.websocketapp.outbound.WebsocketResponse;
import com.prasenjit.examples.websocketapp.pools.AsyncMsgSender;
import com.prasenjit.examples.websocketapp.pools.MessageDispatcher;

public class WebsocketApp {

	private int noOfMsgDispatcher;
	private int noOfMsgSender;
	private BlockingQueue<String> inBoundQ;
	private BlockingQueue<WebsocketResponse> outBoundQ;
	private static final Logger LOGGER= Logger.getLogger(WebsocketApp.class);
	
	public void init() {
		LOGGER.info("WebsocketApp.init()");
		for (int i = 0; i < noOfMsgDispatcher; i++) {
			new Thread(new MessageDispatcher(this.inBoundQ,this.outBoundQ)).start();
		}
		for (int i = 0; i < noOfMsgSender; i++) {
			new Thread(new AsyncMsgSender(this.outBoundQ)).start();
		}
	}

	public int getNoOfMsgDispatcher() {
		return noOfMsgDispatcher;
	}

	public void setNoOfMsgDispatcher(int noOfMsgDispatcher) {
		this.noOfMsgDispatcher = noOfMsgDispatcher;
	}

	public int getNoOfMsgSender() {
		return noOfMsgSender;
	}

	public void setNoOfMsgSender(int noOfMsgSender) {
		this.noOfMsgSender = noOfMsgSender;
	}

	public BlockingQueue<String> getInBoundQ() {
		return inBoundQ;
	}

	public void setInBoundQ(BlockingQueue<String> inBoundQ) {
		this.inBoundQ = inBoundQ;
	}

	public BlockingQueue<WebsocketResponse> getOutBoundQ() {
		return outBoundQ;
	}

	public void setOutBoundQ(BlockingQueue<WebsocketResponse> outBoundQ) {
		this.outBoundQ = outBoundQ;
	}
}
