package model;

import java.util.Date;

import dao.TimeDAO;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class Admission {
	public TimeBean canAdmission(String id, Date date) throws ClassNotFoundException, SQLException {
		SimpleDateFormat dfD = new SimpleDateFormat("yyyy/MM/dd");
		String day = dfD.format(date);
		SimpleDateFormat dfA = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String admission = dfA.format(date);		
		TimeDAO tDao = new TimeDAO();
		if(!tDao.checkAdmission(id, day)) {
			tDao.doAdmission(id, day, admission);
		} else {
			return null;
		}
		TimeBean tBean = new TimeBean(id, day, admission, "");
		return tBean;
	}
}
