package com.prasenjit.examples.websocketapp.servlets;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;
import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocket.OnBinaryMessage;
import org.eclipse.jetty.websocket.WebSocket.OnTextMessage;

import com.prasenjit.examples.websocketapp.pools.WebsocketPool;
import com.prasenjit.examples.websocketapp.utilities.AppUtils;
/**
*
* @author prasenjit
*
*/
public class ChatWebsocket implements WebSocket,OnBinaryMessage,OnTextMessage{

	private Connection connection;
	private BlockingQueue<String> blockingQueue;
	private WebsocketPool pool;
	private static final Logger LOGGER=Logger.getLogger(ChatWebsocket.class);
	
	public ChatWebsocket(BlockingQueue<String> blockingQueue,WebsocketPool pool){
		this.blockingQueue=blockingQueue;
		this.pool=pool;
	}
	
	@Override
	public void onClose(int arg0, String arg1) {
		LOGGER.info("ChatWebsocket.onClose()");
		this.pool.returnToPool(this);
	}

	@Override
	public void onOpen(Connection arg0) {
		LOGGER.info("ChatWebsocket.onOpen()");
		this.connection=arg0;
		try {
			this.connection.sendMessage(AppUtils.prepareConnectInitResponse(String.valueOf(this.hashCode())));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onMessage(byte[] arg0, int arg1, int arg2) {
		LOGGER.info("ChatWebsocket.onMessage()");
		
	}

	@Override
	public void onMessage(String arg0) {
		LOGGER.info("ChatWebsocket.onMessage()::::"+arg0);
		try {
			this.blockingQueue.put(arg0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void sendMsg(String Msg) {
		try {
			this.connection.sendMessage(Msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
