package servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class HomeTransitionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	


	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "WEB-INF/jsp/home.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
		
		String logind = request.getParameter("logind");
		HttpSession session = request.getSession();
		Map<String, String> user = (Map<String, String>)session.getAttribute("user"); //セッションに保存してあるyユーザー情報を取り出す

		
		//セッション情報が空ならログアウト(タイムアウト時など)
		if(user == null) {
			forward = "WEB-INF/jsp/logout.jsp";
			dispatcher = request.getRequestDispatcher(forward);
			dispatcher.forward(request, response);
		} else {
			request.setCharacterEncoding("utf-8");	
			request.setAttribute("logind", logind);
			dispatcher.forward(request, response);
		}
	}

}
