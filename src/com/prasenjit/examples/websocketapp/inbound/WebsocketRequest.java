package com.prasenjit.examples.websocketapp.inbound;

import java.util.ArrayList;
import java.util.List;

public class WebsocketRequest {

	private String requestId;
	private String connectorId;
	private String userId;
	private ArrayList<String> friendList;
	private String msgToFriend;
	
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<String> getFriendList() {
		return friendList;
	}
	public void setFriendList(ArrayList<String> friendList) {
		this.friendList = friendList;
	}
	
	public String getConnectorId() {
		return connectorId;
	}
	public void setConnectorId(String connectorId) {
		this.connectorId = connectorId;
	}
	@Override
	public String toString() {
		return "WebsocketRequest [requestId=" + requestId + ", connectorId="
				+ connectorId + ", userId=" + userId + ", friendList="
				+ friendList + "]";
	}
	public String getMsgToFriend() {
		return msgToFriend;
	}
	public void setMsgToFriend(String msgToFriend) {
		this.msgToFriend = msgToFriend;
	}
	
}
