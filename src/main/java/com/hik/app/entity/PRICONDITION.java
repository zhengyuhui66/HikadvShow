package com.hik.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
public class PRICONDITION {
	
public PRICONDITION(){}



private int id;
private String timeid;
//private String stime;
//private String etime;      
private String addredssid; 
private String eventid;    
//private String phonetype;  
//private String phonesystem;
private String phone;      
//private String clicktype;  
//private String viewtype;   
private String creator;    
private String creatime;   
private String modifier;
private String modifytime;
private String descr;
private String name;



public PRICONDITION(String timeid, String addredssid, String eventid,/* String phonetype, String phonesystem,*/
		String phone/*, String clicktype, String viewtype*/) {
	super();
//	this.stime = stime;
//	this.etime = etime;
	this.timeid=timeid;
	this.addredssid = addredssid;
	this.eventid = eventid;
//	this.phonetype = phonetype;
//	this.phonesystem = phonesystem;
	this.phone = phone;
//	this.clicktype = clicktype;
//	this.viewtype = viewtype;
}
public PRICONDITION(int id, String timeid, String addredssid, String eventid,/* String phonetype,
		String phonesystem,*/ String phone,/* String clicktype, String viewtype, */String creator, String creatime,
		String modifier, String modifytime, String descr, String name) {
	super();
	this.id = id;
//	this.stime = stime;
	this.timeid = timeid;
	this.addredssid = addredssid;
	this.eventid = eventid;
//	this.phonetype = phonetype;
//	this.phonesystem = phonesystem;
	this.phone = phone;
//	this.clicktype = clicktype;
//	this.viewtype = viewtype;
	this.creator = creator;
	this.creatime = creatime;
	this.modifier = modifier;
	this.modifytime = modifytime;
	this.descr = descr;
	this.name = name;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

public String getTimeid(){
	return timeid;
}
public void setTimeid(String timeid){
	this.timeid=timeid;
}
//@Column(length=20,nullable=true)
//public String getStime() {
//	return stime;
//}
//public void setStime(String stime) {
//	this.stime = stime;
//}
//@Column(length=20,nullable=true)
//public String getEtime() {
//	return etime;
//}
//public void setEtime(String etime) {
//	this.etime = etime;
//}
public String getAddredssid() {
	return addredssid;
}
public void setAddredssid(String addredssid) {
	this.addredssid = addredssid;
}
public String getEventid() {
	return eventid;
}
public void setEventid(String eventid) {
	this.eventid = eventid;
}
//@Column(length=20,nullable=true)
//public String getPhonetype() {
//	return phonetype;
//}
//public void setPhonetype(String phonetype) {
//	this.phonetype = phonetype;
//}
//@Column(length=20,nullable=true)
//public String getPhonesystem() {
//	return phonesystem;
//}
//public void setPhonesystem(String phonesystem) {
//	this.phonesystem = phonesystem;
//}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
//@Column(length=20,nullable=true)
//public String getClicktype() {
//	return clicktype;
//}
//public void setClicktype(String clicktype) {
//	this.clicktype = clicktype;
//}
//@Column(length=20,nullable=true)
//public String getViewtype() {
//	return viewtype;
//}
//public void setViewtype(String viewtype) {
//	this.viewtype = viewtype;
//}
public String getCreator() {
	return creator;
}
public void setCreator(String creator) {
	this.creator = creator;
}
public String getCreatime() {
	return creatime;
}
public void setCreatime(String creatime) {
	this.creatime = creatime;
}
public String getModifier() {
	return modifier;
}
public void setModifier(String modifier) {
	this.modifier = modifier;
}
public String getModifytime() {
	return modifytime;
}
public void setModifytime(String modifytime) {
	this.modifytime = modifytime;
}
public String getDescr() {
	return descr;
}
public void setDescr(String descr) {
	this.descr = descr;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String toString() {
	return "PRICONDITION [id=" + id + ", timeid=" + timeid + ", addredssid=" + addredssid
			+ ", eventid=" + eventid + ", phone=" + phone
			+ ", creator=" + creator + ", creatime=" + creatime
			+ ", modifier=" + modifier + ", modifytime=" + modifytime + ", descr=" + descr + ", name=" + name + "]";
}


}
