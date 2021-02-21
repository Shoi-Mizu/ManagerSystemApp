package model;

import java.sql.SQLException;

import dao.UserDAO;

public class CreateUser {
	public String create(String id, String name,String userPass) {
		try {			
			UserDAO uDao = new UserDAO();
			uDao.createUser(id, name, userPass);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return "システムエラーが発生しました。もう一度操作をやり直してください";
		}
		return "";
	}
}
