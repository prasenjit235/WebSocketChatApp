package com.prasenjit.examples.websocketapp.pools;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.prasenjit.examples.websocketapp.outbound.WebsocketResponse;
import com.prasenjit.examples.websocketapp.servlets.ChatWebsocket;
import com.prasenjit.examples.websocketapp.utilities.Memorizers;

public class AsyncMsgSender implements Runnable{

	private BlockingQueue<WebsocketResponse> outBoundQ;
	private static final Logger LOGGER=Logger.getLogger(AsyncMsgSender.class);
	
	public AsyncMsgSender(BlockingQueue<WebsocketResponse> outBoundQ) {
		super();
		this.outBoundQ = outBoundQ;
	}

	public BlockingQueue<WebsocketResponse> getOutBoundQ() {
		return outBoundQ;
	}

	public void setOutBoundQ(BlockingQueue<WebsocketResponse> outBoundQ) {
		this.outBoundQ = outBoundQ;
	}

	@Override
	public void run() {
		try {
		while (true) {
				processMsg(this.outBoundQ.take());
		}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void processMsg(WebsocketResponse response) {
		int replyMode=response.getReplyMode();
		switch (replyMode) {
		case 0:
			handleMsgForSENDTOONE(response);
			break;
		case 1:
			
			break;
		case 2:
			handleMsgForSENDTOALL(response);
		break;
		default:
			break;
		}
	}
	
	private void handleMsgForSENDTOONE(WebsocketResponse websocketResponse){
		LOGGER.info("AsyncMsgSender.handleMsgForSENDTOONE()::connector id::"+websocketResponse.getConnectorId());
		ChatWebsocket webSocket=(ChatWebsocket)Memorizers.getConnectorIdVsWebsocket().get(websocketResponse.getConnectorId());
		ObjectMapper objectMapper=new ObjectMapper();
		String response=null;
		try {
			response = objectMapper.writeValueAsString(websocketResponse);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		webSocket.sendMsg(response);
	}
	
	private void handleMsgForSENDTOALL(WebsocketResponse response) {
		LOGGER.info("AsyncMsgSender.handleMsgForSENDTOALL()");
		List<String> connectorIds=response.getConnectorIds();
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("AsyncMsgSender.handleMsgForSENDTOALL()::Length::"+connectorIds.size());
		}
		for (String string : connectorIds) {
			ChatWebsocket webSocket=(ChatWebsocket)Memorizers.getConnectorIdVsWebsocket().get(string);
			ObjectMapper objectMapper=new ObjectMapper();
			String resultObj=null;
			try {
				resultObj = objectMapper.writeValueAsString(response);
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			webSocket.sendMsg(resultObj);
			LOGGER.info("AsyncMsgSender.handleMsgForSENDTOALL()::MSG sent to "+string);
		}
	}

}
