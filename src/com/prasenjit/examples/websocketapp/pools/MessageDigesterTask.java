package com.prasenjit.examples.websocketapp.pools;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.prasenjit.examples.websocketapp.inbound.WebsocketRequest;
import com.prasenjit.examples.websocketapp.outbound.ConnectionInitRes;
import com.prasenjit.examples.websocketapp.outbound.Friend2FriendRes;
import com.prasenjit.examples.websocketapp.outbound.HandShakeRes;
import com.prasenjit.examples.websocketapp.outbound.Pong;
import com.prasenjit.examples.websocketapp.outbound.WebsocketResponse;
import com.prasenjit.examples.websocketapp.utilities.AppInfo;
import com.prasenjit.examples.websocketapp.utilities.AppInfo.RequestInfo;
import com.prasenjit.examples.websocketapp.utilities.Memorizers;
/**
*
* @author prasenjit
*
*/
public class MessageDigesterTask implements Runnable{

	private String msg;
	private ObjectMapper requestMapper;
	private BlockingQueue<WebsocketResponse> outboundQ;
	private static final Logger  LOGGER=Logger.getLogger(MessageDigesterTask.class);
	public MessageDigesterTask(String msg,BlockingQueue<WebsocketResponse> queue){
		this.msg=msg;
		this.requestMapper=new ObjectMapper();
		this.outboundQ=queue;
	}
	
	@Override
	public void run() {
		handleMsg();
	}

	private void handleMsg() {
		try {
			WebsocketRequest websocketRequest=this.requestMapper.readValue(msg, WebsocketRequest.class);
			int requestId=RequestInfo.getRequestId(websocketRequest.getRequestId());
			switch (requestId) {
			case 100:
				handleConnectionInit(websocketRequest);
				break;
			case 101:
				handlePing(websocketRequest);
				break;	
			case 102:
				
				break;	
			case 103:
				
				break;
			case 104:
				handleFriendToFriend(websocketRequest);
				break;
			case 105:
				handleHandShake(websocketRequest);
				break;	
			default:
				break;
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void handleConnectionInit(WebsocketRequest request) {
		LOGGER.info("MessageDigesterTask.handleConnectionInit()::"+request.toString());
		ConnectionInitRes connectionInitRes=new ConnectionInitRes(AppInfo.SUCCESS);
		connectionInitRes.setConnectorId(request.getConnectorId());
		try {
			this.outboundQ.put(connectionInitRes);
		} catch (InterruptedException e) {
			LOGGER.error("MessageDigesterTask.handleConnectionInit()", e);
		}
	}
	
	private void handlePing(WebsocketRequest request) {
		LOGGER.info("MessageDigesterTask.handlePing()::"+request.toString());
		Pong pong=new Pong(AppInfo.SUCCESS, request.getConnectorId());
		try {
			this.outboundQ.put(pong);
		} catch (InterruptedException e) {
			LOGGER.error("MessageDigesterTask.handlePing()",e);
		}
	}
	
	private void handleHandShake(WebsocketRequest request) {
		LOGGER.info("MessageDigesterTask.handleHandShake()");
		HandShakeRes handShakeRes=new HandShakeRes(AppInfo.SUCCESS, request.getConnectorId());
		try {
			this.outboundQ.put(handShakeRes);
		} catch (InterruptedException e) {
			LOGGER.error("MessageDigesterTask.handleHandShake()",e);
		}
	}
	
	private void handleFriendToFriend(WebsocketRequest request){
		LOGGER.info("MessageDigesterTask.handleFriendToFriend()");
		String user=request.getFriendList().get(0);
		Friend2FriendRes friend2FriendRes=new Friend2FriendRes(Memorizers.getUserVsConnectorId().get(user), AppInfo.SUCCESS);
		friend2FriendRes.setMsgFromFriend(request.getMsgToFriend());
		friend2FriendRes.setFriendId(request.getUserId());
		try {
			this.outboundQ.put(friend2FriendRes);
		} catch (InterruptedException e) {
			LOGGER.error("MessageDigesterTask.handleFriendToFriend()",e);
		}
	}
}

