package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CheckUser;


public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//新規作成のページに移動
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("message", "新規アカウントを作成しましょう！");

		String view = "WEB-INF/jsp/new.jsp" ;
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	//新規作成を行う
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		boolean result = false;
		String name = null;
		String userPass = null;

		request.setCharacterEncoding("utf-8");
		name = request.getParameter("userName");
		userPass = request.getParameter("userPass");
		String forward = "WEB-INF/jsp/new.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
		if((name == null) || (name.length() < 1)) {
			request.setAttribute("message", "アカウント名を入力してください");
			dispatcher.forward(request, response);
			return;
		}
		if((userPass == null) || (userPass.length() < 1)) {
			request.setAttribute("message", "パスワードを入力してください");
			dispatcher.forward(request, response);
			return;
		}

		try {
			CheckUser cUser = new CheckUser(name, userPass);
			result = cUser.userCheck();
		} catch(Exception e) {
			request.setAttribute("message", "Exception : " + e.getMessage());
		}

		if(result == true) {
			request.setAttribute("userName", name);
			forward = "WEB-INF/jsp/home.jsp";
			dispatcher = request.getRequestDispatcher(forward);
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("message", "このアカウント名は既にあります");
			dispatcher = request.getRequestDispatcher(forward);
			dispatcher.forward(request, response);
		}

	}

}
