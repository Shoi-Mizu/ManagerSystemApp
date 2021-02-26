package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Attendance;
import model.CalendarLogic;


public class AttendanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stringYear = request.getParameter("year");
		String stringMonth = request.getParameter("month");
		String changeMonth = request.getParameter("changeMonth");
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		ArrayList<String> week = new ArrayList<String>(); //曜日
		ArrayList<String> admission = new ArrayList<String>(); //出勤
		ArrayList<String> leaving = new ArrayList<String>(); //退勤
		
		String logind = request.getParameter("logind"); //ログイン中
		HttpSession session = request.getSession(); //セッション情報取得
		Map<String, String> user = (Map<String, String>)session.getAttribute("user"); //セッションに保存してあるyユーザー情報を取り出す
		String id = "";
		if(user != null) {
			id = user.get(logind); //idの取得
		}
		
		String forward = "WEB-INF/jsp/attendance.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
		
		//セッション情報が空ならログアウト(タイムアウト時など)
		if(user == null) {
			forward = "WEB-INF/jsp/logout.jsp";
			dispatcher = request.getRequestDispatcher(forward);
			dispatcher.forward(request, response);
		} else {
			//次月または前月を押した場合
			if(changeMonth != null) {
				year = Integer.parseInt(stringYear);
				month = Integer.parseInt(stringMonth);
				if(changeMonth.equals("before")) {
					month--;
					if(month < 0) {
						month = 11;
						if(year - 1 < 1) {
							month = 0;
						} else {
							year--;
						}
					}
				} else if(changeMonth.equals("after")) {
					month++;
					if(month > 11) {
						month = 0;
						year++;
					}
				}
			}
			
			CalendarLogic cLogic = new CalendarLogic();
			week = cLogic.calendarCreate(calendar, year, month); //曜日取得
			try {
				Attendance attendance = new Attendance();
				admission = attendance.getAdmission(calendar, id); //出勤情報取得
				leaving = attendance.getLeaving(calendar, id); //退勤情報取得
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				request.setAttribute("errMessage", "システムエラーが発生しました");
			}
			
			request.setCharacterEncoding("utf-8");
			request.setAttribute("year", year);
			request.setAttribute("month", month);
			request.setAttribute("week", week);
			request.setAttribute("admission", admission);
			request.setAttribute("leaving", leaving);
			request.setAttribute("logind", logind);
			dispatcher.forward(request, response);
		}
	}

}
