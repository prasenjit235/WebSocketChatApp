package com.prasenjit.examples.websocketapp.pools;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.prasenjit.examples.websocketapp.outbound.WebsocketResponse;

public class PoolExecutors {

	private static final ExecutorService PES = Executors
	.newFixedThreadPool(10);
	
	public static void addTaskToPES(final WebsocketResponse response,final BlockingQueue<WebsocketResponse> blockingQueue) {
		PES.submit(new Runnable() {
			@Override
			public void run() {
				try {
					blockingQueue.put(response);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
