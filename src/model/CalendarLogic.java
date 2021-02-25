package model;

import java.util.ArrayList;
import java.util.Calendar;

public class CalendarLogic {
	public ArrayList<String> calendarCreate(Calendar calendar, int year, int month) {
		String[] week = {"","日", "月", "火", "水", "木", "金", "土"};
		ArrayList<String> weekList = new ArrayList<String>();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		int lastDay = calendar.getActualMaximum(Calendar.DATE);
		for(int i = 0; i < lastDay; i++) {
			calendar.set(year, month, i+1);
			weekList.add(week[calendar.get(Calendar.DAY_OF_WEEK)]);
		}
		return weekList;
	}
}
