package com.hik.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

public class MMPPPRODUCT {
	
public MMPPPRODUCT(){}
private String id;
private String mmppid1;
private String mmppid2;
private String mmppid3;
private String mmppid4;
private String mmppid5;
private String mmppid6;
private String mmppid7;
private String mmppid8;
private String mmppid9;
private String creator;
private String creatime;
private String modifier;
private String modifytime;
private String playstrager;
private String playpristrager;
private String descr;
private String name;
public MMPPPRODUCT(String iD,  String mmppid1, String mmppid2, String mmppid3, String mmppid4, String mmppid5,
		String mmppid6, String mmppid7, String mmppid8, String mmppid9, String creator, String creatime, String modifier,
		String modifytime, String playstrager, String playpristrager, String descr, String name) {
	super();
	id = iD;
	this.mmppid1 = mmppid1;
	this.mmppid2 = mmppid2;
	this.mmppid3 = mmppid3;
	this.mmppid4 = mmppid4;
	this.mmppid5 = mmppid5;
	this.mmppid6 = mmppid6;
	this.mmppid7 = mmppid7;
	this.mmppid8 = mmppid8;
	this.mmppid9 = mmppid9;
	this.creator = creator;
	this.creatime = creatime;
	this.modifier = modifier;
	this.modifytime = modifytime;
	this.playstrager = playstrager;
	this.playpristrager = playpristrager;
	this.descr = descr;
	this.name = name;
}

public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}

public String getmmppid1() {
	return mmppid1;
}
public void setmmppid1(String mmppid1) {
	this.mmppid1 = mmppid1;
}

public String getmmppid2() {
	return mmppid2;
}
public void setmmppid2(String mmppid2) {
	this.mmppid2 = mmppid2;
}

public String getmmppid3() {
	return mmppid3;
}
public void setmmppid3(String mmppid3) {
	this.mmppid3 = mmppid3;
}

public String getmmppid4() {
	return mmppid4;
}
public void setmmppid4(String mmppid4) {
	this.mmppid4 = mmppid4;
}

public String getmmppid5() {
	return mmppid5;
}
public void setmmppid5(String mmppid5) {
	this.mmppid5 = mmppid5;
}

public String getmmppid6() {
	return mmppid6;
}
public void setmmppid6(String mmppid6) {
	this.mmppid6 = mmppid6;
}

public String getmmppid7() {
	return mmppid7;
}
public void setmmppid7(String mmppid7) {
	this.mmppid7 = mmppid7;
}

public String getmmppid8() {
	return mmppid8;
}
public void setmmppid8(String mmppid8) {
	this.mmppid8 = mmppid8;
}

public String getmmppid9() {
	return mmppid9;
}
public void setmmppid9(String mmppid9) {
	this.mmppid9 = mmppid9;
}

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

public String getPlaystrager() {
	return playstrager;
}
public void setPlaystrager(String playstrager) {
	this.playstrager = playstrager;
}

public String getPlaypristrager() {
	return playpristrager;
}
public void setPlaypristrager(String playpristrager) {
	this.playpristrager = playpristrager;
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



}
