package com.hik.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


public class CLICK_COUNT_LOG {
	
	public CLICK_COUNT_LOG() {
		super();
	}
	public CLICK_COUNT_LOG(String id, String click_time, String busid, String apmac, String phone, String phonemac,
			String modelid, String modelmodeid, String materid, String advert_url,String advertid,String lineid) {
		super();
		this.id = id;
		this.click_time = click_time;
		this.busid = busid;
		this.apmac = apmac;
		this.phone = phone;
		this.phonemac = phonemac;
		this.modelid = modelid;
		this.modelmodeid = modelmodeid;
		this.materid = materid;
		this.advert_url = advert_url;
		this.advertid=advertid;
		this.lineid=lineid;
	}
	private String id;
	private String click_time;
	private String busid;
	private String apmac;
	private String phone;
	private String phonemac;
	private String modelid;
	private String modelmodeid;
	private String materid;
	private String advert_url;
	private String advertid;
	private String lineid;
	public String getAdvertid() {
		return advertid;
	}
	public void setAdvertid(String advertid) {
		this.advertid = advertid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClick_time() {
		return click_time;
	}
	public void setClick_time(String click_time) {
		this.click_time = click_time;
	}
	public String getBusid() {
		return busid;
	}
	public void setBusid(String busid) {
		this.busid = busid;
	}
	public String getApmac() {
		return apmac;
	}
	public void setApmac(String apmac) {
		this.apmac = apmac;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPhonemac() {
		return phonemac;
	}
	public void setPhonemac(String phonemac) {
		this.phonemac = phonemac;
	}
	public String getModelid() {
		return modelid;
	}
	public void setModelid(String modelid) {
		this.modelid = modelid;
	}
	public String getModelmodeid() {
		return modelmodeid;
	}
	public void setModelmodeid(String modelmodeid) {
		this.modelmodeid = modelmodeid;
	}
	public String getMaterid() {
		return materid;
	}
	public void setMaterid(String materid) {
		this.materid = materid;
	}
	public String getAdvert_url() {
		return advert_url;
	}
	public void setAdvert_url(String advert_url) {
		this.advert_url = advert_url;
	}
	public String getLineid() {
		return lineid;
	}
	public void setLineid(String lineid) {
		this.lineid = lineid;
	}
	
	
}
