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
	//�����ֻ���֤��
	public void sendSMSCode(String phone,String code,HttpSession session);
	//�û���½
	public String login(JSONObject map,HttpSession session,Happen happen);
	//�豸��֤
	public String portal(HttpSession session);
	//ͳ�Ƶ������
	public String saveClickCount(String advertid,String modelid,String modelmodelid,HttpSession session);
	//��֤
	public boolean toVaild(String code,String phone);
	//ͳ��չʾ����
	public int saveShowCount(String advertid,String modelid,String advposid,HttpSession session);
	//��ȡAP
	public List<JSONObject> getApInfo(String gw_id);
	//��½�ɹ���
	public String logined(String pubip,String token,HttpSession session);
}
