package com.hik.dao;

import java.util.List;
import com.hik.app.entity.CLICK_COUNT_LOG;
import com.hik.app.entity.Happen;
import com.hik.app.entity.PRICONDITION;
import com.hik.app.entity.SHOW_COUNT_LOG;

import net.sf.json.JSONObject;

public interface WechatVehicleWifiDao {
	/**
	 * 发送一条信息
	 * @return
	 */
	public void sendSMSCodeDao(String phone,String smsContent,String time,int type);
//	/**
//	 * 
//	 * @param mac  设备物理地址
//	 * @return
//	 */
//	public List queryBusId(String mac);
//	/**
//	 * 
//	 * @param mac  设备物理地址
//	 * @return
//	 */
//	public List queryLoginedBusId(String mac);
//	/**
//	 * 得到地址
//	 * @param advertId 广告ID
//	 * @return
//	 */
//	public List<JSONObject> queryAdvertUrl(String advertId);
	/**
	 * 
	 * @param log 广告日志
	 * @return
	 */
	public int saveClickCount(CLICK_COUNT_LOG log);
//	/**
//	 * 查询mac  的BUSID
//	 * @param mac
//	 * @return
//	 */
//	public List queryBusiByMAC(String mac);
	/**
	 * 
	 * @param showlogs 插入的数据信息
	 * @return
	 */
	public int saveShowCount(SHOW_COUNT_LOG showlogs);
//	/**
//	 * 
//	 * @param mac
//	 * @return
//	 */
//	public List queryPausedByMac(String mac);
//	
//	/**
//	 * 
//	 * @param mac
//	 * @return
//	 */
//	public List queryLoginedPausedByMac(String mac);
	/**
	 * 
	 * @param phone_mac 手机物理地址
	 * @param phone 手机号
	 * @return
	 */
	public int saveRecordNet(String phone_mac,String phone,String apmac);
//	/**
//	 * 取得WIFIAuth的ID
//	 * @return
//	 */
//	public int getWifiAuthId();
	
	public List<JSONObject> getApInfo(String gw_id);
//	public List getLineIdByMac(String mac);
//	/**
//	 * 验证码真为
//	 * @param code 验证码
//	 * @param phone 手机
//	 * @return
//	 */
//	public List toVaild(String code,String phone);
//	-------------------------------------------------------------------------------------------
	public List getLoginDate(String phonemac);
	
	/**
	 * 获取当前模版，皮肤，以及所广告位上的广告集
	 * @param mac
	 * @param cycid
	 * @return
	 */
	public List getAlladvgroups(String mac,String cycid);
	/**
	 * 查询当前的公众号产品上
	 * @param mac
	 * @param cycid
	 * @return
	 */
	public List getAllMMPP(String mac, String cycid);
	/**
	 * 获取当前模版，皮肤，以及所广告位上的优先广告集
	 * @param mac
	 * @param cycid
	 * @return
	 */
	public List getAllpriadvgroups(List priids,String groupId);
	/**
	 * 取出符合优先级条件的ID
	 * @param pricondition
	 * @return
	 */
	public List getPriid(PRICONDITION pricondition);
	/**
	 * 
	 * @param groupId
	 * @return
	 */
	public List getAdvGroup(String groupId);
	/**
	 * 
	 * @param advid
	 * @return
	 */
	public List<JSONObject> getAdvInfo(String advid);
	/**
	 * 
	 * @param advid
	 * @return
	 */
	public List<JSONObject> getMmppInfo(String mmppid);
	/**
	 * 更新轮播展示序号
	 * @param advgroupid
	 * @param times
	 * @return
	 */
	public int updateAdvgrouptimes(String advgroupid,int times);
	
	/**
	 * 更新公众号轮播展示序号
	 * @param advgroupid
	 * @param times
	 * @return
	 */
	public int updatemmpProducttimes(String mmppid,int times);
	/**
	 * 	
	 * @param mac AP物理ID
	 * @param advertid  广告ID
	 * @return
	 */
	public List<JSONObject> queryDetailInfo(String mac,String advertid);
	
	public List<JSONObject> getSpeedAndTime(String gw_id);
	
	public List<JSONObject> queryPriByCondition(List<String> advid,String timeid,/*String clientsys,String clienttype,*/String phone);
	
	public List<JSONObject> getLatLong(String addressid);
	
	/**
	 * 获取当前AP的经纬度
	 * @param Mac
	 * @return
	 */
	public List<JSONObject> getCurrentLatLongByMac(String Mac);
//	====================================================================
	public List<JSONObject> getConditionByadvid(String id);
	
	public List getifadv(String advid,Happen happen,JSONObject json);
	
	
	public List getAccesstoken(String appid,String appSecret);
	
	public List<JSONObject> getPriadvByCondit(List advids,Happen happen,String apmac);
	
}
