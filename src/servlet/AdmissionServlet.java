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

import model.Admission;
import model.TimeBean;


public class AdmissionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isAdmission = false; //出勤したかどうか
		Date date = new Date(); //今日の日時を取得
		String logind = request.getParameter("logind"); //ログイン中
		HttpSession session = request.getSession(); //セッション情報取得
		Map<String, String> user = (Map<String, String>)session.getAttribute("user"); //セッションに保存してあるyユーザー情報を取り出す
		String id = "";
		if(user != null) {
			id = user.get(logind); //idの取得
		}
		
		request.setCharacterEncoding("utf-8");	
		String forward = "WEB-INF/jsp/admission.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
		
		TimeBean tBean = new TimeBean();
		try {			
			Admission admission = new Admission();
			isAdmission = admission.canAdmission(id, date, tBean); //出勤する
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			request.setAttribute("errMessage", "システムエラーが発生しました");
			request.setAttribute("logind", logind);
			forward = "WEB-INF/jsp/home.jsp";
			dispatcher = request.getRequestDispatcher(forward);
			dispatcher.forward(request, response);
		}
		
		//出勤登録できたら出勤完了画面に遷移
		if(isAdmission) {
			session.setAttribute("tBean", tBean);
			request.setAttribute("logind", logind);
			dispatcher.forward(request, response);
		//既に登録済みであったらホーム画面にエラーメッセージ表示
		} else {
			session.setAttribute("tBean", tBean);
			request.setAttribute("errMessage", "本日は既に出勤しています");
			request.setAttribute("logind", logind);
			forward = "WEB-INF/jsp/home.jsp";
			dispatcher = request.getRequestDispatcher(forward);
			dispatcher.forward(request, response);
			
		}
	}

}
