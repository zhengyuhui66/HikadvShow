package com.hik.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hik.framework.utils.Page;
import com.hik.framework.utils.PageObject;

import net.sf.json.JSONObject;
//@Component
public class BaseDao {
//	@PersistenceContext
	@Autowired
	protected  EntityManager entityManager;
	
	@Autowired
	protected EntityManagerFactory entityManagerFactory;
	
	
	public EntityManager getEm(){
		return this.entityManager;
//		if(entityManagerFactory!=null){
//			if(entityManager==null){
//				entityManager=entityManagerFactory.createEntityManager();
//			}
//			if(!entityManager.isOpen()){
//				entityManager.clear();
//				entityManager=entityManagerFactory.createEntityManager();
//			}
//			return entityManager;
//		}else{
//			return null;
//		}
	}
	
	/**
	 * 
	 * @param list 需要转换成JSON的List
	 * @param str key集合
	 * @return
	 */
	protected List<JSONObject> listToJson(List list,String[] str){
		List<JSONObject> resultList = new ArrayList<JSONObject>();
		
		int tjsonstr=str.length;
		if(tjsonstr==1){
			for(int i=0;i<list.size();i++){
				Object obj =list.get(i);
				if("null".equals(obj)){
					obj=null;
				}
				JSONObject tempJson = new JSONObject();
				tempJson.accumulate(str[0],obj);
				resultList.add(tempJson);
			}
		}else{
			for(int i=0;i<list.size();i++){
				Object[] tempObj = (Object[]) list.get(i);
				JSONObject tempJson = new JSONObject();
				for(int j=0;j<tjsonstr;j++){
					Object tempvalue="null".equals(tempObj[j])?null:tempObj[j];
					tempJson.accumulate(str[j],tempvalue);
				}
				resultList.add(tempJson);
			}
		}
		
		return resultList;
	}
	
	public String getSQL(String sql,List paramList){
		if(paramList!=null){
			for(int i=0;i<paramList.size();i++){
				Object obj=paramList.get(i);
				if(obj==null){
					sql=sql.replaceFirst("\\?","");
				}else if(obj instanceof String){
					sql=sql.replaceFirst("\\?","'"+obj+"'");			
				}else{
					sql=sql.replaceFirst("\\?",obj.toString());
				}
			}
		}
		return sql;
	}
	
}
