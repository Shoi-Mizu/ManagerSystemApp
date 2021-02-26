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

import model.DeleteUserInfo;


public class UserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "WEB-INF/jsp/userDelete.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
		request.setCharacterEncoding("utf-8");
		
		String delete = request.getParameter("delete");
		String logind = request.getParameter("logind");
		HttpSession session = request.getSession();
		Map<String, String> user = (Map<String, String>)session.getAttribute("user"); //セッションに保存してあるyユーザー情報を取り出す
		
		//セッション情報が空ならログアウト(タイムアウト時など)
		if(user == null) {
			forward = "WEB-INF/jsp/logout.jsp";
			dispatcher = request.getRequestDispatcher(forward);
			dispatcher.forward(request, response);
		} else {
			//ユーザー削除する時
			if(delete != null) {
				if(delete.equals("delete")) {
					String id = user.get(logind);
					DeleteUserInfo dui = new DeleteUserInfo();
					try {
						dui.deleteTime(id);
						dui.deleteUser(id);
						request.setAttribute("message", "削除が完了しました！！");
					} catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
						request.setAttribute("message", "システムエラーが発生しました");
					}
					session.invalidate();
					forward = "WEB-INF/jsp/deleteOK.jsp";
					dispatcher = request.getRequestDispatcher(forward);
					dispatcher.forward(request, response);
				}
			} else {				
				request.setAttribute("logind", logind);
				dispatcher.forward(request, response);
			}
		}		
	}

}
