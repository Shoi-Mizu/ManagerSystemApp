package model;

import java.sql.SQLException;

import dao.UserDAO;

public class CheckLogin {
	public String userCheck(String id, String userPass) {
		//空欄チェック
		if(id == null) {
			return "名前を入力してください";
		} else if(id.isEmpty()) {
			return "名前を入力してください";			
		}
		if(userPass == null) {
			return "パスワードを入力してください";
		} else if(userPass.isEmpty()) {
			return "パスワードを入力してください";			
		}
		
		//登録されているかチェック
		try {
			UserDAO uDao = new UserDAO();
			if(uDao.checkId(id)) {
				if(!uDao.checkPassword(userPass)) {
					return "ユーザーID、もしくはパスワードが間違っています";
				}
			} else {				
				return "ユーザーID、もしくはパスワードが間違っています";
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return "システムエラーが発生しました。もう一度操作をやり直してください";
		}
		return "";
	}
}
