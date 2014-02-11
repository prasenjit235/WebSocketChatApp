package com.prasenjit.examples.websocketapp.outbound;

import java.util.List;

import com.prasenjit.examples.websocketapp.utilities.AppInfo.ReplyInfo;
import com.prasenjit.examples.websocketapp.utilities.AppInfo.ResponseInfo;

public class NotifyLoggedInUsers implements WebsocketResponse{
	
	private int status;
	private List<String> connectorIds;
	private String responseId=ResponseInfo.NotifyLoggedInUsers.name();
	private List<Profile> profiles;
	private List<Profile> loggedInUsers;
	private List<Profile> loggedOutUsers;

	public NotifyLoggedInUsers(int status, List<String> connectorIds,List<Profile> profiles) {
		super();
		this.status = status;
		this.connectorIds = connectorIds;
		this.profiles=profiles;
	}
	
	@Override
	public int getReplyMode() {
		return ReplyInfo.SENDTOALL.ordinal();
	}

	@Override
	public String getConnectorId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getConnectorIds() {
		return connectorIds;
	}

	public int getStatus() {
		return status;
	}

	public String getResponseId() {
		return responseId;
	}

	@Override
	public String toString() {
		return "NotifyLoggedInUsers [status=" + status + ", connectorIds="
				+ connectorIds + ", responseId=" + responseId + "]";
	}

	public List<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}

	public List<Profile> getLoggedInUsers() {
		return loggedInUsers;
	}

	public void setLoggedInUsers(List<Profile> loggedInUsers) {
		this.loggedInUsers = loggedInUsers;
	}

	public List<Profile> getLoggedOutUsers() {
		return loggedOutUsers;
	}

	public void setLoggedOutUsers(List<Profile> loggedOutUsers) {
		this.loggedOutUsers = loggedOutUsers;
	}


}
