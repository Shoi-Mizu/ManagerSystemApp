package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.TimeBean;
import model.UserBean;
import model.UserSetting;


public class UserSettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "WEB-INF/jsp/user.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
		request.setCharacterEncoding("utf-8");
		
		String change = request.getParameter("change");
		String logind = request.getParameter("logind");
		HttpSession session = request.getSession();
		Map<String, String> user = (Map<String, String>)session.getAttribute("user"); //セッションに保存してあるyユーザー情報を取り出す
		
		//セッション情報が空ならログアウト(タイムアウト時など)
		if(user == null) {
			forward = "WEB-INF/jsp/logout.jsp";
			dispatcher = request.getRequestDispatcher(forward);
			dispatcher.forward(request, response);
		} else {
			String message = "";
			String id = user.get(logind);
			//ユーザー情報変更時
			if(change != null) {
				UserSetting userSetting = new UserSetting();
				UserBean uBean = (UserBean)session.getAttribute("uBean");
				TimeBean tBean = (TimeBean)session.getAttribute("tBean");
				String name = request.getParameter("name");
				String afterId = request.getParameter("id");
				String userPass = request.getParameter("userPass");
				try {
					switch (change) {
						case "name": {
							if(name != null) {
								if(!name.isEmpty()) {
									userSetting.updateName(id, name, uBean);
									message = "ユーザー名が変更されました！";
								}
							}
							break;
						}
						case "id": {
							if(afterId != null) {
								if(!afterId.isEmpty()) {
									if(userSetting.updateId(id, afterId, uBean, tBean)) {										
										user.remove(logind);
										user.put(logind, uBean.getUserId());
										session.setAttribute("user", user);
										session.setAttribute("tBean", tBean);
										message = "ユーザーIDが変更されました！";
									} else {
										message = "新しく入力されたIDは既に登録されています";
									}
								}
							}
							break;
						}
						case "userPass": {
							if(userPass != null) {
								if(!userPass.isEmpty()) {
									userSetting.updateUserPass(id, userPass, uBean);
									message = "パスワードが変更されました！";
								}
							}
							break;
						}
						default:
							break;
					}
					session.setAttribute("uBean", uBean);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
					message = "システムエラーが発生しました";
				}
			}
			request.setAttribute("message", message);
			request.setAttribute("logind", logind);
			dispatcher.forward(request, response);
		}
	}

}
