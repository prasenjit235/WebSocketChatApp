package com.prasenjit.examples.websocketapp.outbound;

import java.util.List;

import com.prasenjit.examples.websocketapp.utilities.AppInfo.ReplyInfo;
import com.prasenjit.examples.websocketapp.utilities.AppInfo.RequestInfo;


public class ConnectionInitRes implements WebsocketResponse{

	private int status;
	private String connectorId;
	private String responseId=RequestInfo.ConnectInit.name();
	
	public ConnectionInitRes() {
	}
	
	public ConnectionInitRes(int status) {
		this.status=status;
	}

	@Override
	public int getReplyMode() {
		return ReplyInfo.SENDTOONE.ordinal();
	}

	
	public void setConnectorId(String connectorId) {
		this.connectorId = connectorId;
	}

	@Override
	public String toString() {
		return "ConnectionInitRes [status=" + status + ", connectorId="
				+ connectorId + "]";
	}

	@Override
	public String getConnectorId() {
		return connectorId;
	}

	public int getStatus() {
		return status;
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
