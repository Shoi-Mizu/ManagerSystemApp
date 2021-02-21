package model;

import java.sql.SQLException;

import dao.UserDAO;

public class CheckUser {	
	public String userCheck(String id, String name, String userPass) {
		//空欄チェック
		if(id == null) {
			return "ユーザーIDを入力してください";
		} else if(id.isEmpty()) {
			return "ユーザーIDを入力してください";			
		}
		
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
			if(uDao.checkId(id)) {
				return "そのIDはすでに使われています";
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return "システムエラーが発生しました。もう一度操作をやり直してください";
		}
		return "";
	}
}
