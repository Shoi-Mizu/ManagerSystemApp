package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CheckLogin;
import model.GetUser;
import model.TimeBean;
import model.UserBean;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//ログインのページに移動
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("message", "ログインしましょう！");

		String view = "WEB-INF/jsp/login.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	//ログインする
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String errMessage = "";
		String logind = ""; //ログイン済み確認用
		String id = "";
		String name = "";
		String userPass = "";
		
		request.setCharacterEncoding("utf-8");
		id = request.getParameter("userId");
		userPass = request.getParameter("userPass");
		
		String forward = "WEB-INF/jsp/login.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
		
		CheckLogin cLogin = new CheckLogin();
		errMessage = cLogin.userCheck(id, userPass); //ID、パスワードをチェック
		
		//IDとパスワードが合っていたらマイページへ移動
		if(errMessage.equals("")) {
			HttpSession session = request.getSession(); //セッションを取得
			//ログイン情報
			logind = UUID.randomUUID().toString(); //ランダムな値を取得
			Map<String, String> user = new HashMap<String, String>();
			GetUser gUser = new GetUser();
			name = gUser.getUserName(id); //IDを検索してユーザー名を取得
			UserBean uBean = new UserBean(id, name, userPass);
			user.put(logind, uBean.getUserId()); //IDを保存
			TimeBean tBean = new TimeBean(); //勤怠データ（空）
			//ログイン情報をセッションに保存
			session.setAttribute("uBean", uBean);
			session.setAttribute("tBean", tBean);
			session.setAttribute("user", user);
			
			request.setAttribute("logind", logind);
			
			forward = "WEB-INF/jsp/home.jsp";
			dispatcher = request.getRequestDispatcher(forward);
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("message", errMessage);
			dispatcher = request.getRequestDispatcher(forward);
			dispatcher.forward(request, response);
		}
	}

}
