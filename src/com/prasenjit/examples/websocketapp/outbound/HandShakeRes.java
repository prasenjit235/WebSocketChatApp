package com.prasenjit.examples.websocketapp.outbound;

import java.util.List;

import com.prasenjit.examples.websocketapp.utilities.AppInfo;
import com.prasenjit.examples.websocketapp.utilities.AppInfo.ResponseInfo;
/**
*
* @author prasenjit
*
*/

public class HandShakeRes implements  WebsocketResponse{

	private int status;
	private String connectorId;
	private String responseId=ResponseInfo.HandShakeRes.name();
	
	public HandShakeRes(int status, String connectorId) {
		super();
		this.status = status;
		this.connectorId = connectorId;
	}
	
	@Override
	public int getReplyMode() {
		return AppInfo.ReplyInfo.SENDTOONE.ordinal();
	}

	@Override
	public String getConnectorId() {
		return this.connectorId;
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
