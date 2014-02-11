package com.prasenjit.examples.websocketapp.outbound;

import java.util.List;
/**
*
* @author prasenjit
*
*/
public interface WebsocketResponse {

	public int getReplyMode();
	public String getConnectorId() ;
	public List<String> getConnectorIds();
}
