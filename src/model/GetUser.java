package model;

import java.sql.SQLException;

import dao.UserDAO;

public class GetUser {
	public String getUserName(String id) {
		String name = "";
		try {
			UserDAO uDao = new UserDAO();
			name = uDao.getUserName(id);
		} catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return name;
	}
}
