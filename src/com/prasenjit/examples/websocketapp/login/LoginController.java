package com.prasenjit.examples.websocketapp.login;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.prasenjit.examples.websocketapp.inbound.LoginRequest;
import com.prasenjit.examples.websocketapp.outbound.LoginResponse;
import com.prasenjit.examples.websocketapp.outbound.NotifyLoggedInUsers;
import com.prasenjit.examples.websocketapp.outbound.Profile;
import com.prasenjit.examples.websocketapp.outbound.WebsocketResponse;
import com.prasenjit.examples.websocketapp.pools.PoolExecutors;
import com.prasenjit.examples.websocketapp.utilities.AppInfo;
import com.prasenjit.examples.websocketapp.utilities.Memorizers;
/**
 * 
 * @author prasenjit
 *
 */
@Path("/login")
public class LoginController {

	@Context
	private ServletContext context;
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public LoginResponse doLogin(LoginRequest loginRequest) {
		LoginResponse loginResponseObject=new LoginResponse();
		boolean isAuthencated=authenticateUser(loginRequest);
		if (isAuthencated) {
			loginResponseObject.setStatus(0);
			Set<String> users=Memorizers.getUserVsConnectorId().keySet();
			List<Profile> usersList=new ArrayList<Profile>();
			for (String string : users) {
				Profile userProfile=new Profile(true,string);
				usersList.add(userProfile);
			}
			loginResponseObject.setProfiles(usersList);
			notifyOnlineUsers(loginResponseObject.getProfiles());
		}else{
			loginResponseObject.setStatus(-1);
		}
		return loginResponseObject;
	}
	
	private boolean authenticateUser(LoginRequest LoginRequest){
		if (isValidLogin(LoginRequest.getUsername(), LoginRequest.getPassword())) {
			String connectorId=Memorizers.getUserVsConnectorId().get(LoginRequest.getUsername());
			if (connectorId==null) {
				Memorizers.getUserVsConnectorId().putIfAbsent(LoginRequest.getUsername(), LoginRequest.getConnectorId());
			}
			return true;
		}
		return false;
	}
	
	private void notifyOnlineUsers(List<Profile> profiles){
		ApplicationContext applicationContext=WebApplicationContextUtils.getWebApplicationContext(context);
		BlockingQueue<WebsocketResponse> outboundQ=(LinkedBlockingQueue<WebsocketResponse>)applicationContext.getBean("outBoundQ");
		Set<String> users=Memorizers.getUserVsConnectorId().keySet();
		List<Profile> usersList=new ArrayList<Profile>();
		for (String string : users) {
			Profile userProfile=new Profile(true,string);
			usersList.add(userProfile);
		}
		NotifyLoggedInUsers loggedInUsers=new NotifyLoggedInUsers(AppInfo.SUCCESS,new ArrayList<String>(Memorizers.getUserVsConnectorId().values()), profiles);
		loggedInUsers.setLoggedInUsers(usersList);
		System.out.println("Notifying online users!!");
		PoolExecutors.addTaskToPES(loggedInUsers, outboundQ);
	}
	
	private boolean isValidLogin(String userName,String password) {
		String loginRegex="(login)([1-9]|10)";
		if (userName!=null && password!=null && userName.matches(loginRegex) && "123456".equals(password)) {
			return true;
		}
		
		return false;
	}
}

