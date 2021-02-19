package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	
	//名前を確認する
	public String checkName(String name) throws ClassNotFoundException,SQLException{
		Connection connection = null;
		String url = "jdbc:mysql://localhost:3306/managerDB";
		String user = "root";
		String password = "access01";

		String sql = "SELECT name FROM users WHERE name=?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement statment = connection.prepareStatement(sql);
			statment.setString(1, name);
			ResultSet rs = statment.executeQuery();
			if(rs.next()) {
				return  "その名前はすでに使われています";
			}
		} finally { 
			if (connection != null) {
				connection.close();
			}
		}
		return "";
	}
	
	//ユーザーを登録する
	public void createUser(String name, String userPass) throws ClassNotFoundException, SQLException{
		Connection connection = null;
		String url = "jdbc:mysql://localhost:3306/managerDB";
		String user = "root";
		String password = "access01";
		
		String sql = "INSERT INTO users (name, password) VALUES (?, ?)";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement statment = connection.prepareStatement(sql);
			statment.setString(1, name);
			statment.setString(2, userPass);
			statment.executeUpdate();
		} finally { 
			if (connection != null) {
				connection.close();
			}
		}	
	}
}