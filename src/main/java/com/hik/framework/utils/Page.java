package com.hik.framework.utils;

import java.util.List;

import net.sf.json.JSONObject;

public interface Page {
	public List getElementList();
	public int getPageNo();
	public int getPageSize();
	public int getTotalPageNo();
	public int getTotal();
	
//	List<JSONObject> getElement(int start,int limit);
//	 int curPage ; // ��ǰҳ
//     int pageSize; // ÿҳ������
//     int totalRow; // ��������
//     int start;// ��ǰҳ��ʼ��
//     int end;// ������
//     int totalPage; // ������ҳ
//    List<JSONObject> resultList;
    
//    
//    
//    public List<JSONObject> getResultList() {
//		return resultList;
//	}
//
//	public void setResultList(List<JSONObject> resultList) {
//		this.resultList = resultList;
//	}
//
//	public int getCurPage() {
//        return curPage;
//    }
//
//    public void setCurPage(int curPage) {
//        if (curPage < 1) {
//            curPage = 1;
//        } else {
//            start = pageSize * (curPage - 1);
//        }
//        end = start + pageSize > totalRow ? totalRow : start + pageSize;
//        this.curPage = curPage;
//    }
//
//    public int getStart() {
//        // start=curPage*pageSize;
//        return start;
//    }
//
//    public int getEnd() {
//
//        return end;
//    }
//
//    public int getPageSize() {
//        return pageSize;
//    }
//
//    public void setPageSize(int pageSize) {
//        this.pageSize = pageSize;
//    }
//
//    public int getTotalRow() {
//        return totalRow;
//    }
//
//    public void setTotalRow(int totalRow) {
//        totalPage = (totalRow + pageSize - 1) / pageSize;
//        this.totalRow = totalRow;
//        if (totalPage < curPage) {
//            curPage = totalPage;
//            start = pageSize * (curPage - 1);
//            end = totalRow;
//        }
//        end = start + pageSize > totalRow ? totalRow : start + pageSize;
//    }
//
//    public int getTotalPage() {
//
//        return this.totalPage;
//    }
}
