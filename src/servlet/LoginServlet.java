package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CheckLogin;
import model.GetUser;
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
			GetUser gUser = new GetUser();
			name = gUser.getUserName(id);
			UserBean uBean = new UserBean(id, name, userPass);
			request.setAttribute("user", uBean);
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
