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
	 * 增删改的SQL
	 * @param sql 需要执行的sql
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
	 * @param sql 记录日志 sql
	 * @param logTip  日志
	 * @return
	 */
	public int executeUpdateSQL(String sql,String logTip){
		logger.info(logTip+":"+sql);
		int result= executeUpdateSQL(sql);
		return result;
	}
	/**
	 * 增删改的SQL
	 * @param sql 需要执行的sql
	 * @param param 需要执行的参数
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
	 * 增删改的SQL
	 * @param sql 需要执行的sql
	 * @param param 需要执行的参数
	 * @param logTip 日志
	 * @return
	 */
	public int executeUpdateSQL(String sql,List param,String logTip){
		logger.info(logTip+":"+getSQL(sql, param));
		int result=executeUpdateSQL(sql,param);
		return result;
	}
	
	
	/**
	 * 增删改的SQL
	 * @param sql 需要执行的sql
	 * @param param 需要执行的参数
	 * @param em 引擎
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
	 * 增删改的SQL
	 * @param sql 需要执行的sql
	 * @param param 需要执行的参数
	 * @param em 引擎
	 * @param logTip 日志
	 * @return
	 */
	public int executeUpdateSQL(String sql,List param,EntityManager em,String logTip){
		logger.info(logTip+":"+getSQL(sql, param));
		int result=executeUpdateSQL(sql,param,em);
		return result;
	}
	
	/**
	 * 不分页的方法(有参数)
	 * @param sql 需要执行的sql
	 * @param param sql中的参数
	 * @return 结果集
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
	 * 不分页的方法(有参数)
	 * @param sql  需要执行的sql
	 * @param param sql中的参数
	 * @return 结果集
	 */
	public List<JSONObject> getNoPageObject(String sql,List param,String[] str){
		List list = getNoPageObject(sql,param);
		List<JSONObject> result = listToJson(list,str);
		return result;
	}
	
	
	/**
	 * 
	 * @param sql  需要分页的sql
	 * @param start 开始条数(第几条开始)
	 * @param limit 每页条数
	 * @param param sql中的参数
	 * @return 结果集
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
	 * @param sql  需要分页的sql
	 * @param start 开始条数(第几条开始)
	 * @param limit 每页条数
	 * @return 结果集
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
	 * 不分页的方法(无参数)
	 * @param sql 需要执行的sql
	 * @return
	 */
	public List getNoPageObject(String sql){
		Query query = getEm().createNativeQuery(sql);
		List result = query.getResultList();
		return result;
	}
	/**
	 * 不分页的方法(无参数)
	 * @param sql 需要执行的SQL
	 * @param str 需要转为JSON的key
	 * @return
	 */
	public List getNoPageObject(String sql,String[] str){
		List list=getNoPageObject(sql);
		List<JSONObject> result =  listToJson(list,str);
		return result;
	}
	/**
	 * 不分页的方法(无参数)
	 * @param sql 需要执行的SQL
	 * @param sqlTip 日志
	 * @return
	 */
	public List getNoPageObject(String sql,String sqlTip){
		List result=getNoPageObject(sql);
		logger.info(sqlTip+":"+getSQL(sql, null));
		return result;
	}
	/**
	 * 不分页的方法(无参数)
	 * @param sql 需要执行的SQL
	 * @param str 需要转为JSON的key 数组
	 * @param sqlTip 日志
	 * @return
	 */
	public List getNoPageObject(String sql,String[] str,String sqlTip){
		List result=getNoPageObject(sql,str);
		logger.info(sqlTip+":"+getSQL(sql, null));
		return result;
	}
	/**
	 * 不分页的方法(有参数)
	 * @param sql 需要执行的SQL
	 * @param param  需要执行的参数
	 * @param sqlTip  日志提示
	 * @return
	 */
	public List getNoPageObject(String sql,List param,String sqlTip){
		logger.info(sqlTip+getSQL(sql, param));
		List result=getNoPageObject(sql,param);
		return result;
	}
	/**
	 * 不分页的方法(有参数)
	 * @param sql 需要执行的SQL
	 * @param param sql中的参数
	 * @param str 需转换为json的字段
	 * @param sqlTip logger日志
	 * @return
	 */
	public List<JSONObject> getNoPageObject(String sql,List param,String[] str,String sqlTip){
		logger.info(sqlTip+":"+getSQL(sql, param));
		List<JSONObject> result=getNoPageObject(sql,param,str);
		return result;
	}
	/**
	 * 不分页的方法(有参数)
	 * @param sql  需要查询总条数的sql
	 * @param param sql中的参数
	 * @return 总条数
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
	 * @param sql  需要查询总条数的sql
	 * @return 总条数
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
	 * @param sql  需要分页的sql
	 * @param start 开始条数(第几条开始)
	 * @param limit 每页条数
	 * @param param sql中的参数
	 * @return 分页数组类型Page
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
	 * @param sql  需要分页的sql
	 * @param start 开始条数(第几条开始)
	 * @param limit 每页条数
	 * @return 分页数组类型Page
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
	 * @param sql  需要分页的sql
	 * @param start 开始条数(第几条开始)
	 * @param limit 每页条数
	 * @param param sql中的参数
	 * @param str 需要转JSON的数据
	 * @return 分页JSON数组类型Page
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
	 * @param sql  需要分页的sql
	 * @param start 开始条数(第几条开始)
	 * @param limit 每页条数
	 * @param str 需要转JSON的数据
	 * @return 分页JSON数组类型Page
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
	 * @param sql  需要分页的sql
	 * @param start 开始条数(第几条开始)
	 * @param limit 每页条数
	 * @param param sql中的参数
	 * @param str 需要转JSON的数据
	 * @param logTip 日志
	 * @return 分页JSON数组类型Page
	 */
	public Page getPageObject(String sql,int start,int limit,List param,String[] str,String logTip){
		Page page  = getPageObject(sql,start,limit,param,str);
		logger.info(logTip+":"+getSQL(sql,param));
		return page;
	}
	/**
	 * 
	 * @param sql  需要分页的sql
	 * @param start 开始条数(第几条开始)
	 * @param limit 每页条数
	 * @param str 需要转JSON的数据
	 * @param logTip 日志
	 * @return
	 */
	public Page getPageObject(String sql,int start,int limit,String[] str,String logTip){
		Page page  = getPageObject(sql,start,limit,str);
		logger.info(logTip+":"+getSQL(sql,null));
		return page;
	}
	
	
}
