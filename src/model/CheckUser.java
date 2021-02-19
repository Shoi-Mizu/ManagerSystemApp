package model;

import java.sql.SQLException;

import dao.UserDAO;

public class CheckUser {
	String name;
	String userPass;
	
	public CheckUser(String name, String userPass) {
		this.name = name;
		this.userPass = userPass;
	}
	
	public String userCheck() {
		String errMessage = "";
		//空欄チェック
		if(name == null) {
			return "名前を入力してください";
		} else if(name.isEmpty()) {
			return "名前を入力してください";			
		}
		if(userPass == null) {
			return "パスワードを入力してください";
		} else if(userPass.isEmpty()) {
			return "パスワードを入力してください";			
		}
		
		//重複チェック
		try {
			UserDAO uDao = new UserDAO();
			errMessage = uDao.checkName(name);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return errMessage;
	}
}
