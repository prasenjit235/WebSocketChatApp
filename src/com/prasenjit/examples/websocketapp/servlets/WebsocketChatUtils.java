package com.prasenjit.examples.websocketapp.servlets;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
/**
*
* @author prasenjit
*
*/
public class WebsocketChatUtils {

	private static AtomicLong connectionIdGenerator=new AtomicLong(0);
	
	private static Map<String, ChatWebsocket> connectionIdVsChatWebsocket=new HashMap<String, ChatWebsocket>();
	
	public static String createConnectionIdAndRegisterWebsocket(ChatWebsocket chatWebsocket) {
		String connectionId=String.valueOf(generateConnectionId());
		connectionIdVsChatWebsocket.put(connectionId, chatWebsocket);
		return connectionId;
	}
	
	public static long generateConnectionId() {
		return connectionIdGenerator.incrementAndGet();
	}
}
