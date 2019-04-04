package com.hik.app.entity;

import java.util.List;

import com.hik.util.Area;

import net.sf.json.JSONObject;

public class Happen {
	private Area nowpoint;
	private String nowTime;
//	private String clientSys;
//	private String clientType;
	private String phone;
	private List<JSONObject> event;
	
	public Happen(Area nowpoint, String nowTime,/* String clientSys, String clientType,*/ String phone,List<JSONObject> event) {
		super();
		this.nowpoint = nowpoint;
		this.nowTime = nowTime;
//		this.clientSys = clientSys;
//		this.clientType = clientType;
		this.event=event;
		this.phone = phone;
	}
	
	public List<JSONObject> getEvent() {
		return event;
	}

	public void setEvent(List<JSONObject> event) {
		this.event = event;
	}

	public Area getNowpoint() {
		return nowpoint;
	}
	public void setNowpoint(Area nowpoint) {
		this.nowpoint = nowpoint;
	}
	public String getNowTime() {
		return nowTime;
	}
	public void setNowTime(String nowTime) {
		this.nowTime = nowTime;
	}
//	public String getClientSys() {
//		return clientSys;
//	}
//	public void setClientSys(String clientSys) {
//		this.clientSys = clientSys;
//	}
//	public String getClientType() {
//		return clientType;
//	}
//	public void setClientType(String clientType) {
//		this.clientType = clientType;
//	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
