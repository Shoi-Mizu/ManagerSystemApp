package model;

import java.util.Date;

import dao.TimeDAO;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class Admission {
	//出勤登録
	public boolean canAdmission(String id, Date date, TimeBean tBean) throws ClassNotFoundException, SQLException {
		//日時のフォーマットを整える
		SimpleDateFormat dfD = new SimpleDateFormat("yyyy/MM/dd");
		String day = dfD.format(date);
		SimpleDateFormat dfA = new SimpleDateFormat("HH:mm");
		String admission = dfA.format(date);
		
		TimeDAO tDao = new TimeDAO();
		//出勤しているか確認
		if(!tDao.checkAdmission(id, day, tBean)) {
			tDao.doAdmission(id, day, admission);
			tBean.setId(id);
			tBean.setDay(day);
			tBean.setAdmission(admission);
		} else {
			return false;
		}
		return true;
	}
}
