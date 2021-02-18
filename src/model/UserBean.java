package model;

public class UserBean {
	private String name;
	private String userPass;
	
	public UserBean(String name, String userPass) {
		this.name = name;
		this.userPass = userPass;		
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
