package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.TimeBean;

public class TimeDAO {
	private String url = "jdbc:mysql://localhost:3306/managerDB";
	private String user = "root";
	private String password = "access01";
	
	//出勤チェック(出勤していたらtrue)
	public boolean checkAdmission(String id, String day, TimeBean tBean) throws ClassNotFoundException, SQLException {
		Connection connection = null;
		
		String sql = "SELECT * FROM time WHERE id=? AND day=?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement statment = connection.prepareStatement(sql);
			statment.setString(1, id);
			statment.setString(2, day);
			ResultSet rs = statment.executeQuery();
			if(!rs.next()) {
				return false;
			} else {
				tBean.setId(rs.getString("id"));
				tBean.setDay(rs.getString("day"));
				tBean.setAdmission(rs.getString("admission"));
				tBean.setLeaving(rs.getString("leaving"));
			}
		} finally { 
			if (connection != null) {
				connection.close();
			}
		}
		
		return true;
	}
	
	//出勤登録する
	public void doAdmission(String id, String day, String admission) throws ClassNotFoundException, SQLException {
		Connection connection = null;
		
		String sql = "INSERT INTO time(id, day, admission) VALUES (?, ?, ?)";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement statment = connection.prepareStatement(sql);
			statment.setString(1, id);
			statment.setString(2, day);
			statment.setString(3, admission);
			statment.executeUpdate();
		} finally { 
			if (connection != null) {
				connection.close();
			}
		}	
	}
	
	//退勤チェック(退勤していたらtrue)
	public boolean checkLeaving(String id, String day, TimeBean tBean) throws ClassNotFoundException, SQLException {
		Connection connection = null;
		
		String sql = "SELECT * FROM time WHERE id=? AND day=?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement statment = connection.prepareStatement(sql);
			statment.setString(1, id);
			statment.setString(2, day);
			ResultSet rs = statment.executeQuery();
			if(rs.next()) {
				if(rs.getString("leaving") == null) {
					return false;
				} else {
					tBean.setLeaving(rs.getString("leaving"));					
				}
			}
		} finally { 
			if (connection != null) {
				connection.close();
			}
		}
		
		return true;
	}
	
	//退勤登録する
	public void doLeaving(String id, String day, String leaving) throws ClassNotFoundException, SQLException {
		Connection connection = null;
		
		String sql = "UPDATE time SET leaving=? WHERE id=? AND day=?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			PreparedStatement statment = connection.prepareStatement(sql);
			statment.setString(1, leaving);
			statment.setString(2, id);
			statment.setString(3, day);
			statment.executeUpdate();
		} finally { 
			if (connection != null) {
				connection.close();
			}
		}	
	}
	
	
}
