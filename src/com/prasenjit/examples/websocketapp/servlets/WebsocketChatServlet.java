package com.prasenjit.examples.websocketapp.servlets;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketServlet;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class WebsocketChatServlet extends WebSocketServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1433567677561132601L;
	
	private ChatWebsocketFactory chatWebsocketFactory;
	
	@Override
	public WebSocket doWebSocketConnect(HttpServletRequest arg0, String arg1) {
		ApplicationContext applicationContext=WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		this.chatWebsocketFactory=(ChatWebsocketFactory)applicationContext.getBean("chatWebsocketFactory");
		return chatWebsocketFactory.getChatWebsocket();
	}
	
	public ChatWebsocketFactory getChatWebsocketFactory() {
		return chatWebsocketFactory;
	}
	public void setChatWebsocketFactory(ChatWebsocketFactory chatWebsocketFactory) {
		this.chatWebsocketFactory = chatWebsocketFactory;
	}
	

}
