package com.hik.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.hik.app.entity.CLICK_COUNT_LOG;
import com.hik.app.entity.Happen;
import com.hik.app.entity.SHOW_COUNT_LOG;
import com.hik.framework.utils.Page;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Deprecated
public interface VehicleWifiService {
	public void sendSMSCode(String phone,String code);
	
	public String login(String mac,String phonemac,HttpSession session,Happen happen);
//	/**
//	 * 
//	 * @param mac  设备物理地址
//	 * @return
//	 */
//	public String queryLoginedBusId(String mac,String phone,String phonemac);
//	
	public String portal(String mac,String phone,String phonemac,HttpSession session,String url,Happen happen);
//	/**
//	 * 
//	 * @param advertId  广告ID
//	 * @return
//	 */
//	public String queryAdvertUrl(String advertId);
	/**
	 * 
	 * @param log 日志
	 * @return
	 */
	public String saveClickCount(CLICK_COUNT_LOG log);
	/**
	 * 验证码真为
	 * @param code 验证码
	 * @param phone 手机
	 * @return
	 */
	public boolean toVaild(String code,String phone);
	
	
	public int saveShowCount(SHOW_COUNT_LOG count_LOG);
	
	
	public List<JSONObject> getSpeedAndTime(String gw_id);
	
	
//	/**
//	 * 展示统计
//	 * @param jsons
//	 */
//	public void showCount(JSONArray jsons,String mac,String phone,String phonemac);
	
//	public int test();
}
