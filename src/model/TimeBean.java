package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TimeBean implements Serializable{
	private String id; //ユーザーID
	private String day; //日付
	private String admission; //出勤日時
	private String leaving; //退勤日時
	
	public TimeBean() {
	}
	
	public TimeBean(String id, String day, String admission, String leaving) {
		this.id = id;
		this.day = day;
		this.admission = admission;
		this.leaving = leaving;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	
	public String getAdmission() {
		return admission;
	}
	public void setAdmission(String admission) {
		this.admission = admission;
	}
	
	public String getLeaving() {
		return leaving;
	}
	public void setLeaving(String leaving) {
		this.leaving = leaving;
	}
	
}
