package model;

import java.sql.SQLException;

import dao.TimeDAO;
import dao.UserDAO;

public class DeleteUserInfo {
	//ユーザーを削除する
	public void deleteUser(String id) throws ClassNotFoundException, SQLException {
		UserDAO uDao = new UserDAO();
		uDao.deleteUser(id);
	}
	
	//勤怠データを削除する
	public void deleteTime(String id) throws ClassNotFoundException, SQLException {
		TimeDAO tDao = new TimeDAO();
		tDao.deleteTime(id);
	}
}
