package com.prasenjit.examples.websocketapp.servlets;

import org.apache.log4j.Logger;

import com.prasenjit.examples.websocketapp.pools.WebsocketPool;
/**
*
* @author prasenjit
*
*/
public class ChatWebsocketFactory {

	private WebsocketPool websocketPool;
	private static final Logger LOGGER=Logger.getLogger(ChatWebsocketFactory.class);
	public ChatWebsocketFactory(){
		LOGGER.info("ChatWebsocketFactory.ChatWebsocketFactory()");
	}
	
	public  ChatWebsocket getChatWebsocket() {
		return websocketPool.getWebsocketFromPool();
	}

	public WebsocketPool getWebsocketPool() {
		return websocketPool;
	}

	public void setWebsocketPool(WebsocketPool websocketPool) {
		this.websocketPool = websocketPool;
	}
}
