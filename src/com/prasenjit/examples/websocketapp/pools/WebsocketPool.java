package com.prasenjit.examples.websocketapp.pools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

import org.apache.log4j.Logger;
import org.eclipse.jetty.websocket.WebSocket;


import com.prasenjit.examples.websocketapp.servlets.ChatWebsocket;
import com.prasenjit.examples.websocketapp.utilities.Memorizers;
/**
*
* @author prasenjit
*
*/
public class WebsocketPool {

	private int MAX_POOL_SIZE;
	private Semaphore semaphore;
	private List<ChatWebsocket> blockingQ;
	private int CURRENT_LOCATION;
	private BlockingQueue<String> inboundQ;
	private static final Logger LOGGER=Logger.getLogger(WebsocketPool.class);
	
	public void init() {
		LOGGER.info("WebsocketPool.init()");
		this.CURRENT_LOCATION=this.MAX_POOL_SIZE-1;
		this.semaphore=new Semaphore(MAX_POOL_SIZE);
		this.blockingQ=new ArrayList<ChatWebsocket>(MAX_POOL_SIZE);
		initializePool();
	}

	private void initializePool(){
		LOGGER.info("WebsocketPool.initializePool():::size:::"+MAX_POOL_SIZE);
		Map<String, WebSocket> tempConnectorIdVsWebsocket=new HashMap<String, WebSocket>();
		for (int i = 0; i < MAX_POOL_SIZE; i++) {
			ChatWebsocket chatWebsocket=new ChatWebsocket(this.inboundQ,this);
			tempConnectorIdVsWebsocket.put(String.valueOf(chatWebsocket.hashCode()), chatWebsocket);
			blockingQ.add(chatWebsocket);
		}
		Memorizers.setConnectorIdVsWebsocket(tempConnectorIdVsWebsocket);
	}
	
	
	public ChatWebsocket getWebsocketFromPool() {
		try {
			semaphore.acquire();
			synchronized (blockingQ) {
				return blockingQ.remove(CURRENT_LOCATION--);
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
			semaphore.release();
		}
		
		return null;
	}
	
	public void returnToPool(ChatWebsocket chatWebsocket) {
		synchronized (blockingQ) {
			blockingQ.add(chatWebsocket);
			CURRENT_LOCATION++;
		}
		semaphore.release();
	}
	public int getMAX_POOL_SIZE() {
		return MAX_POOL_SIZE;
	}

	public void setMAX_POOL_SIZE(int mAX_POOL_SIZE) {
		MAX_POOL_SIZE = mAX_POOL_SIZE;
	}

	public BlockingQueue<String> getInboundQ() {
		return inboundQ;
	}

	public void setInboundQ(BlockingQueue<String> inboundQ) {
		this.inboundQ = inboundQ;
	}
}
