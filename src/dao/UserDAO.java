package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	
	public boolean checkName(String name) {
		
		String url = "jdbc:mysql://localhost:3306/managerDB?characterEncoding=UTF-8&serverTimezone=JST";
		String user = "root";
		String password = "ziburi01";
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(Exception e) {
			e.printStackTrace();
		}
	
		String sql = "SELECT name FROM users WHERE name=?";
		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			PreparedStatement statment = connection.prepareStatement(sql);
			statment.setString(1, name);
			ResultSet rs = statment.executeQuery();
			if(rs.next()) {
				return  false;
			}
		} catch(Exception e) {
			e.getStackTrace();
		}
		return true;
	}
	
}
