package model;

import dao.UserDAO;

public class CheckUser {
	String name;
	String userPass;
	
	public CheckUser(String name, String userPass) {
		this.name = name;
		this.userPass = userPass;
	}
	
	public boolean userCheck() {
		UserDAO uDao = new UserDAO();
		uDao.checkName(name);
		return true;
	}
}
