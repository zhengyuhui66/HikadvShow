package com.hik.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpRequest;


import net.sf.json.JSONObject;
/**
 * 
 * @author zhengyuhui
 *
 */
public class BaseController {
	protected Log log = LogFactory.getLog(this.getClass()); 
	public static final String SUCCESS="true";
	public static final String FAILURE="false";
	public static final String QUERY_SUCCESS_INFO=SUCCESS+"|查询成功";
	public static final String QUERY_FAILURE_INFO=FAILURE+"|查询失败";
	public static final String INSERT_SUCCESS_INFO=SUCCESS+"|插入成功";
	public static final String INSERT_FAILURE_INFO=FAILURE+"|插入失败";
	public static final String UPDATE_SUCCESS_INFO=SUCCESS+"|更新成功";
	public static final String UPDATE_FAILURE_INFO=FAILURE+"|更新失败";
	public static final String DELETE_SUCCESS_INFO=SUCCESS+"|删除成功";
	public static final String DELETE_FAILURE_INFO=FAILURE+"|删除失败";
	public static final String ADD_SUCCESS_INFO=SUCCESS+"|新增成功";
	public static final String ADD_FAILURE_INFO=FAILURE+"|新增失败";
	public static final String PUSH_SUCCESS_INFO=SUCCESS+"|消息推送成功";
	public static final String PUSH_FAILURE_INFO=FAILURE+"|消息推送失败";
	
	
	
	private Object obj;
	public void setResultInfo(String ifsuccessInfo,Object obj){
		JSONObject jsonObj = new JSONObject();
		String[] info=ifsuccessInfo.split("\\|");
		jsonObj.accumulate("success",info[0]);
		jsonObj.accumulate("responseInfo", info[1]);
		jsonObj.accumulate("data", obj);
		this.obj=jsonObj;
	}
	
	public Object getResultInfo(){
		return this.obj;
	}
	
	public void returnResponse(HttpServletResponse response,Object obj){
		response.setHeader("Content-type","text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer=null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer.print(obj);	
		writer.flush(); 
		writer.close();
	}
	
	public int getStartOrLimit(HttpServletRequest httpRequest,String startOrLimit){
		int _sl=-1;
		try{
		String _startOrLimit=httpRequest.getParameter(startOrLimit);
			if(StringUtils.isNotEmpty(_startOrLimit)){
				_sl=Integer.parseInt(_startOrLimit);
			}
			if("start".equals(startOrLimit)){
				_sl+=1;
			}
		}catch(Exception e){
			e.printStackTrace();
			_sl=-1;
		}finally{
			return _sl;
		}
	}
	
	public int getIntRequestParam(HttpServletRequest httpRequest,String params){
		int _sl=0;
		try{
			String _startOrLimit=httpRequest.getParameter(params);
				if(StringUtils.isNotEmpty(_startOrLimit)){
					_sl=Integer.parseInt(_startOrLimit);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				return _sl;
			}
	}
	
	public String getUUID(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	
}
