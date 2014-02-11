package com.prasenjit.examples.websocketapp.pools;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.prasenjit.examples.websocketapp.outbound.WebsocketResponse;

public class MessageDispatcher implements Runnable{

	private final ExecutorService poolExecutorService = Executors
			.newFixedThreadPool(100);

	private static final Logger LOGGER=Logger.getLogger(MessageDispatcher.class);
	public void init() {
		LOGGER.info("MessageDispatcher.init()");
	}
	
	private BlockingQueue<String> inBoundQ;
	private BlockingQueue<WebsocketResponse> outBoundQ;
	
	public MessageDispatcher(BlockingQueue<String> inBoundQ,
			BlockingQueue<WebsocketResponse> outBoundQ) {
		super();
		this.inBoundQ = inBoundQ;
		this.outBoundQ = outBoundQ;
	}

	public BlockingQueue<String> getInBoundQ() {
		return inBoundQ;
	}

	public void setInBoundQ(BlockingQueue<String> inBoundQ) {
		this.inBoundQ = inBoundQ;
	}

	@Override
	public void run() {
		try {
		while (true) {
				poolExecutorService.submit(new MessageDigesterTask(inBoundQ.take(),outBoundQ));
		}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public BlockingQueue<WebsocketResponse> getOutBoundQ() {
		return outBoundQ;
	}

	public void setOutBoundQ(BlockingQueue<WebsocketResponse> outBoundQ) {
		this.outBoundQ = outBoundQ;
	}


}
