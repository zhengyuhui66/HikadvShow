package com.hik.framework.utils;

import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class CommonUtil {
//	public static HttpSession httpSession;
//
//	public static void setHttpSession(HttpServletRequest request){
//		if(httpSession==null){
//			httpSession=request.getSession();
//		}
//	}

	public static String getFullWebContext(HttpServletRequest request) {
		String basePath = "";
		String servletPath = request.getServletPath();
		String fullUrl = request.getRequestURL().toString();
		if (fullUrl.indexOf(servletPath) != -1){
			basePath = fullUrl.substring(0, fullUrl.indexOf(servletPath));
		}else{
			basePath = request.getContextPath();
		}
		return basePath;
	}
	/**
	 * ��ȡ�û�ID
	 * @return
	 */
	public static String getUserId(HttpServletRequest request){
		HttpSession httpSession=request.getSession();
		String userSession=(String) httpSession.getAttribute("userId");
		return userSession;
	}
	/**
	 * ��ȡ�û�����
	 * @return
	 */
	public static String getPassWord(HttpServletRequest request){
		HttpSession httpSession=request.getSession();
		String userSession=(String) httpSession.getAttribute("pword");
		return userSession;
	}
	/**
	 * ��ȡ�û�����
	 * @return
	 */
	public static String getUserName(HttpServletRequest request){
		HttpSession httpSession=request.getSession();
		String userSession=(String) httpSession.getAttribute("userName");
		return userSession;
	}
	/**
	 * ��ȡ�û��ֻ�
	 * @return
	 */
	public static String getTelphone(HttpServletRequest request){
		HttpSession httpSession=request.getSession();
		String userSession=(String) httpSession.getAttribute("telphone");
		return userSession;
	}
	/**
	 * ��ȡ�û�Email
	 * @return
	 */
	public static String getEmail(HttpServletRequest request){
		HttpSession httpSession=request.getSession();
		String userSession=(String) httpSession.getAttribute("email");
		return userSession;
	}
	/**
	 * ��ȡ�û�����ʱ��
	 * @return
	 */
	public static String getCreateTime(HttpServletRequest request){
		HttpSession httpSession=request.getSession();
		String userSession=(String) httpSession.getAttribute("createTime");
		return userSession;
	}
	/**
	 * ��ȡ�û���һ�ε�½ʱ��
	 * @return
	 */
	public static String getLoginTime(HttpServletRequest request){
		HttpSession httpSession=request.getSession();
		String userSession=(String) httpSession.getAttribute("loginTime");
		return userSession;
	}
	/**
	 * ��ȡ�û����һ�ε�½ʱ��
	 * @return
	 */
	public static String getLastLoginTime(HttpServletRequest request){
		HttpSession httpSession=request.getSession();
		String userSession=(String) httpSession.getAttribute("lastLoginTime");
		return userSession;
	}
	/**
	 * ��ȡ�û���½����
	 * @return
	 */
	public static String getLoginTimes(HttpServletRequest request){
		HttpSession httpSession=request.getSession();
		String userSession=(String) httpSession.getAttribute("loginTimes");
		return userSession;
	}
	/**
	 * ��ȡ�û���ɫID
	 * @return
	 */
	public static String getTrid(HttpServletRequest request){
		HttpSession httpSession=request.getSession();
		String userSession=(String) httpSession.getAttribute("trid");
		return userSession;
	}
	/**
	 * ����UUID
	 * @return
	 */
	public static String getUUID(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
}
