package model;

import java.util.Date;

import dao.TimeDAO;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Attendance {
	//出勤情報全取得
	public ArrayList<String> getAdmission(Calendar calendar, String id) throws ClassNotFoundException, SQLException {
		ArrayList<String> admission = new ArrayList<String>();
		Date date = new Date();
		TimeBean tBean = new TimeBean();
		TimeDAO tDao = new TimeDAO();
		for(int i = 0; i < calendar.getActualMaximum(Calendar.DATE); i++) {
			calendar.set(Calendar.DATE, i+1);
			date = calendar.getTime();
			SimpleDateFormat dfD = new SimpleDateFormat("yyyy/MM/dd");
			String day = dfD.format(date);
			if(tDao.checkAdmission(id, day, tBean)) {
				admission.add(tBean.getAdmission());
			} else {
				admission.add("----------");
			}
		}
		return admission;
	}
	
	//退勤情報全取得
	public ArrayList<String> getLeaving(Calendar calendar, String id) throws ClassNotFoundException, SQLException {
		ArrayList<String> leaving = new ArrayList<String>();
		Date date = new Date();
		TimeBean tBean = new TimeBean();
		TimeDAO tDao = new TimeDAO();
		for(int i = 0; i < calendar.getActualMaximum(Calendar.DATE); i++) {
			calendar.set(Calendar.DATE, i+1);
			date = calendar.getTime();
			SimpleDateFormat dfD = new SimpleDateFormat("yyyy/MM/dd");
			String day = dfD.format(date);
			if(tDao.checkLeaving(id, day, tBean)) {
				leaving.add(tBean.getLeaving());
			} else {
				leaving.add("----------");
			}
		}
		return leaving;
		
	}
}
