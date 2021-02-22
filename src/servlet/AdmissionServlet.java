package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Admission;
import model.TimeBean;


public class AdmissionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date date = new Date();
		String id = "";
		
		request.setCharacterEncoding("utf-8");
		id = request.getParameter("userId");
		
		TimeBean tBean = new TimeBean();
		try {			
			Admission admission = new Admission();
			admission.canAdmission(id, date);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			request.setAttribute("errMessage", "システムエラーが発生しました");
		}
		
		if(tBean == null) {
			
		} else {
			
		}
	}

}
