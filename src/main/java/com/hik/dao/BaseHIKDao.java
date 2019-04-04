package com.hik.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import com.hik.framework.utils.Page;
import com.hik.framework.utils.PageObject;

import net.sf.json.JSONObject;

public class BaseHIKDao extends BaseDao{
	protected final Log logger = LogFactory.getLog(this.getClass());
	/**
	 * ��ɾ�ĵ�SQL
	 * @param sql ��Ҫִ�е�sql
	 * @return
	 */
	public int executeUpdateSQL(String sql){
		logger.info("==========>"+sql);
		Query query = entityManager.createNativeQuery(sql);
		int result=query.executeUpdate();
		logger.info("<==============="+result);
		return result;
	}
	/**
	 * 
	 * @param sql ��¼��־ sql
	 * @param logTip  ��־
	 * @return
	 */
	public int executeUpdateSQL(String sql,String logTip){
		logger.info(logTip+":"+sql);
		int result= executeUpdateSQL(sql);
		return result;
	}
	/**
	 * ��ɾ�ĵ�SQL
	 * @param sql ��Ҫִ�е�sql
	 * @param param ��Ҫִ�еĲ���
	 * @return
	 */
	public int executeUpdateSQL(String sql,List param){
		Query query = entityManager.createNativeQuery(sql);
		for(int i=0;i<param.size();i++){
			query.setParameter(i+1, param.get(i));
		}
		int result=query.executeUpdate();
		return result;
	}
	/**
	 * ��ɾ�ĵ�SQL
	 * @param sql ��Ҫִ�е�sql
	 * @param param ��Ҫִ�еĲ���
	 * @param logTip ��־
	 * @return
	 */
	public int executeUpdateSQL(String sql,List param,String logTip){
		logger.info(logTip+":"+getSQL(sql, param));
		int result=executeUpdateSQL(sql,param);
		return result;
	}
	
	
	/**
	 * ��ɾ�ĵ�SQL
	 * @param sql ��Ҫִ�е�sql
	 * @param param ��Ҫִ�еĲ���
	 * @param em ����
	 * @return
	 */
	public int executeUpdateSQL(String sql,List param,EntityManager em){
		Query query = em.createNativeQuery(sql);
		for(int i=0;i<param.size();i++){
			query.setParameter(i+1, param.get(i));
		}
		int result=query.executeUpdate();
		return result;
	}
	/**
	 * ��ɾ�ĵ�SQL
	 * @param sql ��Ҫִ�е�sql
	 * @param param ��Ҫִ�еĲ���
	 * @param em ����
	 * @param logTip ��־
	 * @return
	 */
	public int executeUpdateSQL(String sql,List param,EntityManager em,String logTip){
		logger.info(logTip+":"+getSQL(sql, param));
		int result=executeUpdateSQL(sql,param,em);
		return result;
	}
	
	/**
	 * ����ҳ�ķ���(�в���)
	 * @param sql ��Ҫִ�е�sql
	 * @param param sql�еĲ���
	 * @return �����
	 */
	public List getNoPageObject(String sql,List param){
		Query query = getEm().createNativeQuery(sql);
		for(int i=0;i<param.size();i++){
			query.setParameter(i+1, param.get(i));
		}
		List result = query.getResultList();
		return result;
	}
	
	/**
	 * ����ҳ�ķ���(�в���)
	 * @param sql  ��Ҫִ�е�sql
	 * @param param sql�еĲ���
	 * @return �����
	 */
	public List<JSONObject> getNoPageObject(String sql,List param,String[] str){
		List list = getNoPageObject(sql,param);
		List<JSONObject> result = listToJson(list,str);
		return result;
	}
	
	
	/**
	 * 
	 * @param sql  ��Ҫ��ҳ��sql
	 * @param start ��ʼ����(�ڼ�����ʼ)
	 * @param limit ÿҳ����
	 * @param param sql�еĲ���
	 * @return �����
	 */
	public List pageObject(String sql,int start,int limit,List param){
		
		
//		String Pagesql="SELECT * FROM "+   
//						"( "+
//						"SELECT A.*, ROWNUM RN  "+   
//						"FROM ("+sql+") A  "+   
//						"WHERE ROWNUM <= "+(start+limit-1)+
//						")   "+
//						"WHERE RN >= "+start;  
		String Pagesql="SELECT * FROM ( SELECT A.*, ROWNUM RN FROM ("+sql+") A ) WHERE RN BETWEEN "+start+" AND "+(start+limit-1);
		Query query = getEm().createNativeQuery(Pagesql);
		for(int i=0;i<param.size();i++){
			String t= param.get(i).toString();
			query.setParameter(i+1,t);
		}
		List result = query.getResultList();
		return result;
	}
	/**
	 * 
	 * @param sql  ��Ҫ��ҳ��sql
	 * @param start ��ʼ����(�ڼ�����ʼ)
	 * @param limit ÿҳ����
	 * @return �����
	 */
	public List pageObject(String sql,int start,int limit){
		String Pagesql="SELECT * FROM ( SELECT A.*, ROWNUM RN FROM ("+sql+") A ) WHERE RN BETWEEN "+start+" AND "+(start+limit-1);
//		String Pagesql="SELECT * FROM "+   
//				"( "+
//				"SELECT A.*, ROWNUM RN  "+   
//				"FROM ("+sql+") A  "+   
//				"WHERE ROWNUM <= "+(start+limit-1)+
//				")   "+
//				"WHERE RN >= "+start;  
		Query query = getEm().createNativeQuery(Pagesql);
		List result = query.getResultList();
		return result;
	}
	
	/**
	 * ����ҳ�ķ���(�޲���)
	 * @param sql ��Ҫִ�е�sql
	 * @return
	 */
	public List getNoPageObject(String sql){
		Query query = getEm().createNativeQuery(sql);
		List result = query.getResultList();
		return result;
	}
	/**
	 * ����ҳ�ķ���(�޲���)
	 * @param sql ��Ҫִ�е�SQL
	 * @param str ��ҪתΪJSON��key
	 * @return
	 */
	public List getNoPageObject(String sql,String[] str){
		List list=getNoPageObject(sql);
		List<JSONObject> result =  listToJson(list,str);
		return result;
	}
	/**
	 * ����ҳ�ķ���(�޲���)
	 * @param sql ��Ҫִ�е�SQL
	 * @param sqlTip ��־
	 * @return
	 */
	public List getNoPageObject(String sql,String sqlTip){
		List result=getNoPageObject(sql);
		logger.info(sqlTip+":"+getSQL(sql, null));
		return result;
	}
	/**
	 * ����ҳ�ķ���(�޲���)
	 * @param sql ��Ҫִ�е�SQL
	 * @param str ��ҪתΪJSON��key ����
	 * @param sqlTip ��־
	 * @return
	 */
	public List getNoPageObject(String sql,String[] str,String sqlTip){
		List result=getNoPageObject(sql,str);
		logger.info(sqlTip+":"+getSQL(sql, null));
		return result;
	}
	/**
	 * ����ҳ�ķ���(�в���)
	 * @param sql ��Ҫִ�е�SQL
	 * @param param  ��Ҫִ�еĲ���
	 * @param sqlTip  ��־��ʾ
	 * @return
	 */
	public List getNoPageObject(String sql,List param,String sqlTip){
		logger.info(sqlTip+getSQL(sql, param));
		List result=getNoPageObject(sql,param);
		return result;
	}
	/**
	 * ����ҳ�ķ���(�в���)
	 * @param sql ��Ҫִ�е�SQL
	 * @param param sql�еĲ���
	 * @param str ��ת��Ϊjson���ֶ�
	 * @param sqlTip logger��־
	 * @return
	 */
	public List<JSONObject> getNoPageObject(String sql,List param,String[] str,String sqlTip){
		logger.info(sqlTip+":"+getSQL(sql, param));
		List<JSONObject> result=getNoPageObject(sql,param,str);
		return result;
	}
	/**
	 * ����ҳ�ķ���(�в���)
	 * @param sql  ��Ҫ��ѯ��������sql
	 * @param param sql�еĲ���
	 * @return ������
	 */
	public int getTotal(String sql,List param){
		String Pagesql="select count(1) from ("+sql+")";
		Query query = getEm().createNativeQuery(Pagesql);
		for(int i=0;i<param.size();i++){
			query.setParameter(i+1, param.get(i));
		}
		List list = query.getResultList();
		String result="0";
		if(list!=null&&list.size()>0){
			result=list.get(0).toString();
		}
		return Integer.parseInt(result);
	}
	
	/**
	 * 
	 * @param sql  ��Ҫ��ѯ��������sql
	 * @return ������
	 */
	public int getTotal(String sql){
		String Pagesql="select count(1) from ("+sql+")";
		Query query = getEm().createNativeQuery(Pagesql);
		List list = query.getResultList();
		String result="0";
		if(list!=null&&list.size()>0){
			result=list.get(0).toString();
		}
		return Integer.parseInt(result);
	}
	
	/**
	 * 
	 * @param sql  ��Ҫ��ҳ��sql
	 * @param start ��ʼ����(�ڼ�����ʼ)
	 * @param limit ÿҳ����
	 * @param param sql�еĲ���
	 * @return ��ҳ��������Page
	 */
	@Transactional
	public Page getPageObject(String sql,int start,int limit,List param){
		int total = getTotal(sql,param);
		List list = pageObject(sql,start,limit,param);
		Page page =new PageObject(start,limit,total,list);
		return  page;
	}
	/**
	 * 
	 * @param sql  ��Ҫ��ҳ��sql
	 * @param start ��ʼ����(�ڼ�����ʼ)
	 * @param limit ÿҳ����
	 * @return ��ҳ��������Page
	 */
	@Transactional
	public Page getPageObject(String sql,int start,int limit){
		int total = getTotal(sql);
		List list = pageObject(sql,start,limit);
		Page page =new PageObject(start,limit,total,list);
		return  page;
	}
	
	/**
	 * 
	 * @param sql  ��Ҫ��ҳ��sql
	 * @param start ��ʼ����(�ڼ�����ʼ)
	 * @param limit ÿҳ����
	 * @param param sql�еĲ���
	 * @param str ��ҪתJSON������
	 * @return ��ҳJSON��������Page
	 */
	@Transactional
	public Page getPageObject(String sql,int start,int limit,List param,String[] str){
		int total = getTotal(sql,param);
		List list = pageObject(sql,start,limit,param);
		List<JSONObject> result =  listToJson(list,str);
		Page page =new PageObject(start,limit,total,result);
		return  page;
	}
	/**
	 * 
	 * @param sql  ��Ҫ��ҳ��sql
	 * @param start ��ʼ����(�ڼ�����ʼ)
	 * @param limit ÿҳ����
	 * @param str ��ҪתJSON������
	 * @return ��ҳJSON��������Page
	 */
	@Transactional
	public Page getPageObject(String sql,int start,int limit,String[] str){
		int total = getTotal(sql);
		List list = pageObject(sql,start,limit);
		List<JSONObject> result =  listToJson(list,str);
		Page page =new PageObject(start,limit,total,result);
		return  page;
	}
	/**
	 * 
	 * @param sql  ��Ҫ��ҳ��sql
	 * @param start ��ʼ����(�ڼ�����ʼ)
	 * @param limit ÿҳ����
	 * @param param sql�еĲ���
	 * @param str ��ҪתJSON������
	 * @param logTip ��־
	 * @return ��ҳJSON��������Page
	 */
	public Page getPageObject(String sql,int start,int limit,List param,String[] str,String logTip){
		Page page  = getPageObject(sql,start,limit,param,str);
		logger.info(logTip+":"+getSQL(sql,param));
		return page;
	}
	/**
	 * 
	 * @param sql  ��Ҫ��ҳ��sql
	 * @param start ��ʼ����(�ڼ�����ʼ)
	 * @param limit ÿҳ����
	 * @param str ��ҪתJSON������
	 * @param logTip ��־
	 * @return
	 */
	public Page getPageObject(String sql,int start,int limit,String[] str,String logTip){
		Page page  = getPageObject(sql,start,limit,str);
		logger.info(logTip+":"+getSQL(sql,null));
		return page;
	}
	
	
}
