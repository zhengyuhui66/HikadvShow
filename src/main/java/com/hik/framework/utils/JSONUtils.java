package com.hik.framework.utils;

import net.sf.json.JSONObject;

public class JSONUtils {
	public static String getString(JSONObject json,String key){
		String tempStr=json.getString(key);
		if("null".equals(tempStr)){
			return null;
		}else{
			return tempStr;
		}
	}
	
	public static Object getObject(JSONObject json,Object obj){
		Object tempStr=json.get(obj);
		if("null".equals(tempStr)){
			return null;
		}else{
			return tempStr;
		}
	}
	
	public static void main(String[] args) {
		JSONObject json = new JSONObject();
		json.accumulate("key", null);
		Object g=json.get("key");
		if(g==null){
			System.out.println("is really null");			
		}else{
			System.out.println("is "+g);
		}
	}
}
