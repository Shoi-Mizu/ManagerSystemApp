package model;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dao.TimeDAO;

public class Leaving {
	//退勤登録
		public boolean canLeaving(String id, Date date, TimeBean tBean) throws ClassNotFoundException, SQLException {
			//日時のフォーマットを整える
			SimpleDateFormat dfD = new SimpleDateFormat("yyyy/MM/dd");
			String day = dfD.format(date);
			SimpleDateFormat dfL = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			String leaving = dfL.format(date);
			
			TimeDAO tDao = new TimeDAO();
			//出勤しているか確認
			if(tDao.checkAdmission(id, day, tBean)) {
				//退勤しているか確認
				if(!tDao.checkLeaving(id, day, tBean)) {
					tDao.doLeaving(id, day, leaving);
					tBean.setLeaving(leaving);
				} else {
					return false;
				}
			} else {
				return false;
			}
			return true;
		}
}
