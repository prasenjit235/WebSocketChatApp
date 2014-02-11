package com.prasenjit.examples.websocketapp.utilities;

public class AppInfo {

	public static enum RequestInfo {
		ConnectInit(100), Ping(101), FriendToAll(102), FriendToFriends(103), FriendToFriend(
				104),HandShakeReq(105);

		private int requestId;

		RequestInfo(int requestId) {
			this.requestId = requestId;
		}

		public static int getRequestId(String requestInfo) {
			return RequestInfo.valueOf(requestInfo).requestId;
		}
	}
	
	public static  enum ReplyInfo{
		SENDTOONE,SENDTOMANY,SENDTOALL
	}

	public static enum ResponseInfo{
		Pong,HandShakeRes,NotifyLoggedInUsers,FriendToFriendRes
	}
	
	public static final int SUCCESS=0;
	public static final int FAILURE=-1;
}
