package com.hik.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import com.hik.app.entity.CLICK_COUNT_LOG;
import com.hik.app.entity.Happen;
import com.hik.app.entity.SHOW_COUNT_LOG;
import com.hik.framework.utils.Page;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Deprecated
public interface VehicleWifiService2 {
	//发送手机验证码
	public void sendSMSCode(String phone,String code,HttpSession session);
	//用户登陆
	public String login(JSONObject map,HttpSession session,Happen happen);
	//设备认证
	public String portal(HttpSession session);
	//统计点击次数
	public String saveClickCount(String advertid,String modelid,String modelmodelid,HttpSession session);
	//验证
	public boolean toVaild(String code,String phone);
	//统计展示次数
	public int saveShowCount(String advertid,String modelid,String advposid,HttpSession session);
	//获取AP
	public List<JSONObject> getApInfo(String gw_id);
	//登陆成功后
	public String logined(String pubip,String token,HttpSession session);
}
