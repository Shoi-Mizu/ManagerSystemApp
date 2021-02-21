package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	
	//IDを確認する(登録してあればtrue)
	public boolean checkId(String id) throws ClassNotFoundException,SQLException{
		Connection connection = null;
		String url = "jdbc:mysql://localhost:3306/managerDB";
		String user = "root";
		String password = "access01";

		String sql = "SELECT id FROM users WHERE id=?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement statment = connection.prepareStatement(sql);
			statment.setString(1, id);
			ResultSet rs = statment.executeQuery();
			if(!rs.next()) {
				return false;
			}
		} finally { 
			if (connection != null) {
				connection.close();
			}
		}
		return true;
	}
	
	//パスワードを確認する(登録してあればtrue)
	public boolean checkPassword(String userPass) throws ClassNotFoundException,SQLException{
		Connection connection = null;
		String url = "jdbc:mysql://localhost:3306/managerDB";
		String user = "root";
		String password = "access01";
		
		String sql = "SELECT password FROM users WHERE password=?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement statment = connection.prepareStatement(sql);
			statment.setString(1, userPass);
			ResultSet rs = statment.executeQuery();
			if(!rs.next()) {
				return false;
			}
		} finally { 
			if (connection != null) {
				connection.close();
			}
		}
		return true;
	}
	
	
	//ユーザーを登録する
	public void createUser(String id, String name, String userPass) throws ClassNotFoundException, SQLException{
		Connection connection = null;
		String url = "jdbc:mysql://localhost:3306/managerDB";
		String user = "root";
		String password = "access01";
		
		String sql = "INSERT INTO users (id, name, password) VALUES (?, ?, ?)";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement statment = connection.prepareStatement(sql);
			statment.setString(1, id);
			statment.setString(2, name);
			statment.setString(3, userPass);
			statment.executeUpdate();
		} finally { 
			if (connection != null) {
				connection.close();
			}
		}	
	}
	
	//ユーザー名を取得する
	public String getUserName(String id) throws ClassNotFoundException, SQLException{
		String name = "";
		Connection connection = null;
		String url = "jdbc:mysql://localhost:3306/managerDB";
		String user = "root";
		String password = "access01";
		
		String sql = "SELECT * FROM users WHERE id=?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement statment = connection.prepareStatement(sql);
			statment.setString(1, id);
			ResultSet rs = statment.executeQuery();
			if(rs.next()) {				
				name = rs.getString("name");
			}
		} finally { 
			if (connection != null) {
				connection.close();
			}
		}	
		return name;
	}
	
}