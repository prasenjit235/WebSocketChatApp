package com.prasenjit.examples.websocketapp.utilities;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.eclipse.jetty.websocket.WebSocket;

/**
*
* @author prasenjit
*
*/
public class Memorizers {

	private static Map<String, WebSocket> connectorIdVsWebsocket=new HashMap<String, WebSocket>();
	private static ConcurrentMap<String, String>  userVsConnectorId=new ConcurrentHashMap<String, String>();

	public static Map<String, WebSocket> getConnectorIdVsWebsocket() {
		return connectorIdVsWebsocket;
	}

	public static void setConnectorIdVsWebsocket(
			Map<String, WebSocket> connectorIdVsWebsocket) {
		Memorizers.connectorIdVsWebsocket = connectorIdVsWebsocket;
	}

	public static ConcurrentMap<String, String> getUserVsConnectorId() {
		return userVsConnectorId;
	}

	
}
