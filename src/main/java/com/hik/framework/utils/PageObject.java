package com.hik.framework.utils;

import java.util.List;

import net.sf.json.JSONObject;

public class PageObject implements Page{
	//����:�ڼ�ҳ;
	private int pageNo;
	//����:ÿҳ����(���һҳ����ֻ�м�����limit��һ�����)
	private int pageSize;
	//һ���м�ҳ
	private int totalPageNo;
	//һ���м���
	private int total;
	
	private List elementList;
	
	
	public List getElementList() {
		return elementList;
	}


	public void setElementList(List elementList) {
		this.elementList = elementList;
	}


	//	public PageObject(String sql,int start,int limit,List param){
//		String Pagesql="SELECT * FROM ( SELECT A.*, ROWNUM RN FROM ("+sql+") A ) WHERE RN BETWEEN "+start+" AND "+(start+limit);
//	}
	public PageObject(int start,int limit,int total,List elementList){
		this.pageNo=(start%limit==0?start/limit:(start/limit+1));
		this.pageSize=limit;
		this.elementList=elementList;
		this.total=total;
		this.totalPageNo=(this.total%limit==0?this.total/limit:(this.total/limit+1));
	}


	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPageNo() {
		return totalPageNo;
	}

	public void setTotalPageNo(int totalPageNo) {
		this.totalPageNo = totalPageNo;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
