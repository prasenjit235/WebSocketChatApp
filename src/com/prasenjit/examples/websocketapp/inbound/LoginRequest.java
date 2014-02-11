package com.prasenjit.examples.websocketapp.inbound;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author prasenjit
 *
 */
@XmlRootElement
public class LoginRequest {

	private String username;
	private String password;
	private String connectorId;
	
	public LoginRequest() {
		super();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConnectorId() {
		return connectorId;
	}
	public void setConnectorId(String connectorId) {
		this.connectorId = connectorId;
	}
	
	
}
