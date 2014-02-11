package com.prasenjit.examples.websocketapp.utilities;

import java.io.IOException;


import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.prasenjit.examples.websocketapp.outbound.ConnectionInitRes;

public class AppUtils {

	public static String prepareConnectInitResponse(String connectorId) {
		String res=null;
		try {
		ConnectionInitRes connectionInitRes=new ConnectionInitRes(AppInfo.SUCCESS);
		connectionInitRes.setConnectorId(connectorId);
		ObjectMapper mapper=new ObjectMapper();
			res=mapper.writeValueAsString(connectionInitRes);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	
}
