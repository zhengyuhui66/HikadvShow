package com.hik.util;

import javax.servlet.http.HttpSession;

public class Utils {
	public static String getSessionId(HttpSession session){
		if(session==null){
			return null;
		}
		String sessionId = session.getId();
		String[] sessionIds=sessionId.split("\\.");
		return sessionIds[0];
	}
	
	public static void main(String[] args) {
		System.out.println("seeddd".split("\\.")[0]);
		
	}
}
