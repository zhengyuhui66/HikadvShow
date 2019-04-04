package com.hik.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import com.hik.app.entity.CLICK_COUNT_LOG;
import com.hik.app.entity.Happen;
import com.hik.app.entity.PRICONDITION;
import com.hik.app.entity.SHOW_COUNT_LOG;

import net.sf.json.JSONObject;

public interface VehicleWifiDao2 {
	/**
	 * ����һ����Ϣ
	 * @return
	 */
//	public void sendSMSCodeDao(String phone,String smsContent,String time,int type);
//	/**
//	 * 
//	 * @param mac  �豸�����ַ
//	 * @return
//	 */
//	public List queryBusId(String mac);
//	/**
//	 * 
//	 * @param mac  �豸�����ַ
//	 * @return
//	 */
//	public List queryLoginedBusId(String mac);
//	/**
//	 * �õ���ַ
//	 * @param advertId ���ID
//	 * @return
//	 */
//	public List<JSONObject> queryAdvertUrl(String advertId);
	/**
	 * 
	 * @param log �����־
	 * @return
	 */
	public int saveClickCount(CLICK_COUNT_LOG log);
//	/**
//	 * ��ѯmac  ��BUSID
//	 * @param mac
//	 * @return
//	 */
//	public List queryBusiByMAC(String mac);
	/**
	 * 
	 * @param showlogs �����������Ϣ
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
	 * @param phone_mac �ֻ������ַ
	 * @param phone �ֻ���
	 * @return
	 */
	public int saveRecordNet(String phone_mac,String phone,String apmac);
//	/**
//	 * ȡ��WIFIAuth��ID
//	 * @return
//	 */
//	public int getWifiAuthId();
	
	
//	public List getLineIdByMac(String mac);
//	/**
//	 * ��֤����Ϊ
//	 * @param code ��֤��
//	 * @param phone �ֻ�
//	 * @return
//	 */
//	public List toVaild(String code,String phone);
//	-------------------------------------------------------------------------------------------
	public List getLoginDate(String phonemac);
	
	/**
	 * ��ȡ��ǰģ�棬Ƥ�����Լ������λ�ϵĹ�漯
	 * @param mac
	 * @param cycid
	 * @return
	 */
	public List getAlladvgroups(String mac,String[] cycid);
//	/**
//	 * ��ȡ��ǰģ�棬Ƥ�����Լ������λ�ϵ����ȹ�漯
//	 * @param mac
//	 * @param cycid
//	 * @return
//	 */
//	public List getAllpriadvgroups(List priids,String groupId);
//	/**
//	 * ȡ���������ȼ�������ID
//	 * @param pricondition
//	 * @return
//	 */
//	public List getPriid(PRICONDITION pricondition);
//	/**
//	 * 
//	 * @param groupId
//	 * @return
//	 */
//	public List getAdvGroup(String groupId);
//	/**
//	 * 
//	 * @param advid
//	 * @return
//	 */
//	public List<JSONObject> getAdvInfo(String advid);
	/**
	 * �����ֲ�չʾ���
	 * @param advgroupid
	 * @param times
	 * @return
	 */
	public int updateAdvgrouptimes(String advgroupid,int times);
	/**
	 * 	
	 * @param mac AP����ID
	 * @param advertid  ���ID
	 * @return
	 */
//	public List<JSONObject> queryDetailInfo(String mac,String advertid);
	
	public List<JSONObject> getApInfo(String gw_id);
	
	
//	public List<JSONObject> queryPriByCondition(List<String> advid,String timeid,/*String clientsys,String clienttype,*/String phone);
	
//	public List<JSONObject> getLatLong(String addressid);
	
	/**
	 * ��ȡ��ǰAP�ľ�γ��
	 * @param Mac
	 * @return
	 */
//	public List<JSONObject> getCurrentLatLongByMac(String Mac);
//	====================================================================
//	public List<JSONObject> getConditionByadvid(String id);
//	
//	public List getifadv(String advid,Happen happen,JSONObject json);
//	======================================================================
	
	public List<JSONObject> getPriadvByCondit(List advids,Happen happen,String apmac);
	
}
