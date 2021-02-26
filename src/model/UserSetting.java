package model;

import java.sql.SQLException;

import dao.TimeDAO;
import dao.UserDAO;

public class UserSetting {
	//名前を変更する
	public void updateName(String id, String name, UserBean uBean) throws ClassNotFoundException, SQLException {
		UserDAO uDAO = new UserDAO();
		uDAO.updateUserName(id, name);
		uBean.setName(name);
	}
	
	//IDを変更する
	public boolean updateId(String beforeId, String afterId, UserBean uBean, TimeBean tBean) throws ClassNotFoundException, SQLException {
		UserDAO uDAO = new UserDAO();
		TimeDAO tDao = new TimeDAO();
		//重複チェックしてなければ登録
		if(!uDAO.checkId(afterId)) {
			tDao.changeId(beforeId, afterId, tBean); //勤怠データ側のID書き換え
			uDAO.updateUserId(beforeId, afterId);
			uBean.setUserId(afterId);
		} else {
			return false;
		}
		return true;
	}
	
	//パスワードを変更する
	public void updateUserPass(String id, String userPass, UserBean uBean) throws ClassNotFoundException, SQLException {
		UserDAO uDAO = new UserDAO();
		uDAO.updateUserPass(id, userPass);
		uBean.setUserPass(userPass);
	}
	

}
