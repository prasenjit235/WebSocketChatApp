package com.prasenjit.examples.websocketapp.outbound;

import java.util.List;

import com.prasenjit.examples.websocketapp.utilities.AppInfo;
import com.prasenjit.examples.websocketapp.utilities.AppInfo.ResponseInfo;

public class Friend2FriendRes implements WebsocketResponse{

	private String connectorId;
	private int status;
	private String responseId=ResponseInfo.FriendToFriendRes.name();
	private String msgFromFriend;
	private String friendId;
	
	public Friend2FriendRes(String connectorId, int status) {
		super();
		this.connectorId = connectorId;
		this.status = status;
	}
	
	@Override
	public int getReplyMode() {
		return AppInfo.ReplyInfo.SENDTOONE.ordinal();
	}

	@Override
	public String getConnectorId() {
		return connectorId;
	}

	@Override
	public List<String> getConnectorIds() {
		return null;
	}

	public int getStatus() {
		return status;
	}

	public String getResponseId() {
		return responseId;
	}

	public String getMsgFromFriend() {
		return msgFromFriend;
	}

	public void setMsgFromFriend(String msgFromFriend) {
		this.msgFromFriend = msgFromFriend;
	}

	public String getFriendId() {
		return friendId;
	}

	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}

	@Override
	public String toString() {
		return "Friend2FriendRes [connectorId=" + connectorId + ", status="
				+ status + ", responseId=" + responseId + ", msgFromFriend="
				+ msgFromFriend + ", friendId=" + friendId + "]";
	}


}
