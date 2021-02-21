package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CheckUser;
import model.CreateUser;


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

		String errMessage = "";
		String id = "";
		String name = "";
		String userPass = "";

		request.setCharacterEncoding("utf-8");
		id = request.getParameter("userId");
		name = request.getParameter("userName");
		userPass = request.getParameter("userPass");
		
		String forward = "WEB-INF/jsp/new.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(forward);

		CheckUser cUser = new CheckUser();
		errMessage = cUser.userCheck(id, name, userPass); //登録情報のチェック
		if(errMessage.equals("")) {
			CreateUser create = new CreateUser();
			errMessage = create.create(id, name, userPass); //ユーザーの登録
		}
		
		//登録成功したらユーザーのログインページへ移動
		if(errMessage.equals("")) {
			request.setAttribute("message", "登録されました！ログインしてください！");
			forward = "WEB-INF/jsp/login.jsp";
			dispatcher = request.getRequestDispatcher(forward);
			dispatcher.forward(request, response);
		//やり直し
		} else {
			request.setAttribute("message", errMessage);
			dispatcher = request.getRequestDispatcher(forward);
			dispatcher.forward(request, response);
		}

	}

}
