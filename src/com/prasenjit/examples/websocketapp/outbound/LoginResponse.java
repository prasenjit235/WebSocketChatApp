package com.prasenjit.examples.websocketapp.outbound;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * 
 * @author prasenjit
 *
 */
@XmlRootElement
public class LoginResponse {

	private int status ;
	private List<Profile> profiles;
	
	public LoginResponse() {
		if (profiles==null) {
			profiles=new ArrayList<Profile>();
		}
	}

	public LoginResponse(int status, List<Profile> profiles) {
		super();
		this.status = status;
		this.profiles = profiles;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}
}
