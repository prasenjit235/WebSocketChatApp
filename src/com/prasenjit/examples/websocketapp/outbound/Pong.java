package com.prasenjit.examples.websocketapp.outbound;

import java.util.List;

import com.prasenjit.examples.websocketapp.utilities.AppInfo.ReplyInfo;
import com.prasenjit.examples.websocketapp.utilities.AppInfo.ResponseInfo;
/**
*
* @author prasenjit
*
*/
public class Pong implements WebsocketResponse{

	private int status;
	private String connectorId;
	private String responseId=ResponseInfo.Pong.name();
	
	public Pong(int status, String connectorId) {
		this.status = status;
		this.connectorId = connectorId;
	}

	@Override
	public String toString() {
		return "Pong [status=" + status + ", connectorId=" + connectorId + "]";
	}

	@Override
	public int getReplyMode() {
		return ReplyInfo.SENDTOONE.ordinal();
	}

	@Override
	public String getConnectorId() {
		return connectorId;
	}
	
	public void setConnectorId(String connectorId) {
		this.connectorId = connectorId;
	}

	public String getResponseId() {
		return responseId;
	}

	@Override
	public List<String> getConnectorIds() {
		// TODO Auto-generated method stub
		return null;
	}
}
