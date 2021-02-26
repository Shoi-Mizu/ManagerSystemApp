package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Leaving;
import model.TimeBean;


public class LeavingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isLeaving = false; //退勤したかどうか
		Date date = new Date(); //今日の日時を取得
		String logind = request.getParameter("logind"); //ログイン中
		HttpSession session = request.getSession(); //セッション情報取得
		Map<String, String> user = (Map<String, String>)session.getAttribute("user"); //セッションに保存してあるyユーザー情報を取り出す
		String id = "";
		if(user != null) {
			id = user.get(logind); //idの取得
		}
		
		request.setCharacterEncoding("utf-8");	
		String forward = "WEB-INF/jsp/leaving.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(forward);

		
		//セッション情報が空ならログアウト(タイムアウト時など)
		if(user == null) {
			forward = "WEB-INF/jsp/logout.jsp";
			dispatcher = request.getRequestDispatcher(forward);
			dispatcher.forward(request, response);
		} else {
			TimeBean tBean = new TimeBean();
			try {			
				Leaving leaving = new Leaving();
				isLeaving = leaving.canLeaving(id, date, tBean); //退勤する
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				request.setAttribute("errMessage", "システムエラーが発生しました");
				request.setAttribute("logind", logind);
				forward = "WEB-INF/jsp/home.jsp";
				dispatcher = request.getRequestDispatcher(forward);
				dispatcher.forward(request, response);
			}
			
			//退勤登録できたら退勤完了画面に遷移
			if(isLeaving) {
				session.setAttribute("tBean", tBean);
				request.setAttribute("logind", logind);
				dispatcher.forward(request, response);
			//既に登録済み、もしくは出勤登録していなかったらホーム画面にエラーメッセージ表示
			} else {
				session.setAttribute("tBean", tBean);
				request.setAttribute("errMessage", "出勤登録がされていないか、もしくは本日は既に退勤しています");
				request.setAttribute("logind", logind);
				forward = "WEB-INF/jsp/home.jsp";
				dispatcher = request.getRequestDispatcher(forward);
				dispatcher.forward(request, response);
				
			}
		}
	}

}
