package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserBean implements Serializable{
	private String userId;
	private String name;
	private String userPass;
	
	public UserBean() {
	}
	
	public UserBean(String userId, String name, String userPass) {
		this.userId = userId;
		this.name = name;
		this.userPass = userPass;		
	}

	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUserPass() {
		return userPass;
	}
	
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	
}
