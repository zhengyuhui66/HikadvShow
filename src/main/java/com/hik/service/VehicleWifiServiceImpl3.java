package com.hik.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hik.app.entity.CLICK_COUNT_LOG;
import com.hik.app.entity.Happen;
import com.hik.app.entity.SHOW_COUNT_LOG;
import com.hik.dao.ITimeSetDao;
import com.hik.dao.VehicleWifiDao2;
import com.hik.framework.utils.DateUtil;
import com.hik.framework.utils.JSONUtils;
import com.hik.util.Area;
import com.hik.util.GraphicalMain;
import com.hik.util.PROCEDURCES;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class VehicleWifiServiceImpl3 implements VehicleWifiService3 {
	protected Log log = LogFactory.getLog(this.getClass());

	@Autowired
	private VehicleWifiDao2 vehicleWifiDao;
//
//	@Autowired
//	EventMangerDao eventMangerDao;

	@Autowired
	private ITimeSetDao iTimeSetDao;

	@Value("${serverhttp}")
	private String serverhttp;
	// ��֤��¼����
	@Value("${defaultwifiauth_url}")
	private String defaultwifiauth_url;

	// ��֤һ����¼
	@Value("${defaultwifiauthdirect_url}")
	private String defaultwifiauthdirect_url;

	// ��֤�ɹ�
	@Value("${defaultwifiauthsuccess_url}")
	private String defaultwifiauthsuccess_url;

	@Transactional(transactionManager = "smsDatatransactionManager")
	public void sendSMSCode(String phone, String code) {
		// TODO Auto-generated method stub
		// log.info("���յ���code:"+code);
		List smss = iTimeSetDao.querySMS();
		String smsContent = null;
		if (!smss.isEmpty() && smss.size() > 0) {
			smsContent = smss.get(0).toString();
		}
		String _smsContent = smsContent.replace("XXXXXX", code);
		// log.info("׼�����͵Ķ�����==>"+_smsContent);
//		vehicleWifiDao.sendSMSCodeDao(phone, _smsContent, DateUtil.getCurrentDate(), 1);
	}

	@Transactional(readOnly = true)
	public String login(String mac, String phonemac, HttpSession session, Happen happen) {
		// TODO Auto-generated method stub

		String URL;
		long f = System.currentTimeMillis();
		// log.info("��ʼ��ѯ����½ʱ��");
		List<JSONObject> getLastimeList = vehicleWifiDao.getLoginDate(phonemac);
		// log.info("��ʼ��ѯ����½ʱ�������ѵ�ʱ��"+(System.currentTimeMillis()-f));
		// log.info("��ʼ��ѯ���AP��Ϣ");
		long f2 = System.currentTimeMillis();
		List<JSONObject> getApInfo = vehicleWifiDao.getApInfo(mac);
		String lat = "";
		String longit = "";// "speed","timeout","longitude","latitude
		 log.info("��ʼ��ѯ���AP��Ϣ:"+getApInfo.size());
		if (getApInfo.size() > 0) {
			JSONObject apInfo = getApInfo.get(0);
			String speed = JSONUtils.getString(apInfo, "speed");
			String timeout = JSONUtils.getString(apInfo, "timeout");
			log.info("speed====>:"+speed+"  timeout===>:"+timeout);
			lat = JSONUtils.getString(apInfo, "longitude");
			longit = JSONUtils.getString(apInfo, "latitude");
			if (StringUtils.isNotBlank(longit) && StringUtils.isNotBlank(lat)) {
				happen.setNowpoint(new Area(Double.parseDouble(longit), Double.parseDouble(lat)));
			}
			session.setAttribute("speed", speed);
			session.setAttribute("timeout", timeout);
		}
		List<JSONObject> result = null;

		// log.info("����½��¼��"+getLastimeList);
		if (getLastimeList != null && getLastimeList.size() > 0) {
			JSONObject lastTime = getLastimeList.get(0);
			String _lastTime = lastTime.getString("authtime");
			String phone = lastTime.getString("phone");
			happen.setPhone(phone);
			session.setAttribute("phone", phone);
			Date lastdate = DateUtil.getStrToDate(_lastTime);
			// Date tempdate=DateUtil.getAddaDay(lastdate);
			Date tempdate = DateUtil.getAddaMonth(lastdate);
			Date now = new Date();
			long f3 = System.currentTimeMillis();
			if (now.after(tempdate)) {
				// ����һ��ʱ���������֤��½
				// ��ѯ��ǰAP��Ͷ�ŵ����еĹ�漯��ģ�棬���λ
				result = vehicleWifiDao.getAlladvgroups(mac,
						new String[] { PROCEDURCES.AUTHFLAG, PROCEDURCES.AUTHSUCCESSFLAG });
//				log.info("mac:" + mac + "phonemac:" + phonemac + "������֤��½�����еĹ�����õ�ʱ��"
//						+ (System.currentTimeMillis() - f3)+"   result:::::"+result);
				
				session.setAttribute("authInfo", result.toString());
				Object authInfos = session.getAttribute("authInfo");
				URL = authlogin(getByAuthFlag(result, PROCEDURCES.AUTHFLAG), mac, phone, phonemac, happen);
				if (URL == null) {
					URL = serverhttp + defaultwifiauth_url;
				}
				log.info("mac:" + mac + "phonemac:" + phonemac +"  URL:"+ URL);
				return URL;
			} else {
				// ����һ��ʱ����һ����½
				// ��ѯ��ǰAP��Ͷ�ŵ����еĹ�漯��ģ�棬���λ
				result = vehicleWifiDao.getAlladvgroups(mac,
						new String[] { PROCEDURCES.DIRECTFLAG, PROCEDURCES.AUTHSUCCESSFLAG });
//				log.info("mac:" + mac + "phonemac:" + phonemac + "����һ��ʱ����һ����½ �����еĹ�����õ�ʱ��"+ (System.currentTimeMillis() - f)+"   result:::::"+result);
				
				//log.info("authInfo::::::>"+result);
				session.setAttribute("authInfo", result.toString());
				Object authInfos = session.getAttribute("authInfo");
				URL = authlogin(getByAuthFlag(result, PROCEDURCES.DIRECTFLAG), mac, phone, phonemac, happen);
				if (URL == null) {
					log.info("URL is null");
					URL = serverhttp + defaultwifiauthdirect_url + "?phone=" + phone;
				} else {
					log.info("URL is not null");
					URL += "&phone=" + phone;
				}
				log.info("mac:" + mac + "phonemac:" + phonemac +"���ظ��û��ĵ���֤URL��"+URL);
				return URL;
			}
		} else {
			// û����֤��������֤��½
			// ��ѯ��ǰAP��Ͷ�ŵ����еĹ�漯��ģ�棬���λ
			result = vehicleWifiDao.getAlladvgroups(mac,
					new String[] { PROCEDURCES.AUTHFLAG, PROCEDURCES.AUTHSUCCESSFLAG });
//			log.info("mac:" + mac + "phonemac:" + phonemac + "û����֤�� �����еĹ�����õ�ʱ��" + (System.currentTimeMillis() - f)+"   result:::::"+result);
			//log.info("authInfo::::::>"+result);
			session.setAttribute("authInfo", result.toString());
			Object authInfos = session.getAttribute("authInfo");
			URL = authlogin(getByAuthFlag(result, PROCEDURCES.AUTHFLAG), mac, null, phonemac, happen);
			if (URL == null) {
				URL = serverhttp + defaultwifiauth_url;
			}
			log.info("mac:" + mac + "phonemac:" + phonemac +"���ظ��û��ĵ���֤URL��"+URL);
			return URL;
		}
	}

	/**
	 * ��ȡ��֤�����������
	 * 
	 * @param json
	 * @param flag
	 * @return
	 */
	public JSONObject getByAuthFlag(List<JSONObject> json, String flag) {
		List<JSONObject> tJson = new ArrayList<JSONObject>();
		for (JSONObject js : json) {
			String authorder = JSONUtils.getString(js, "authorder");
			if (flag.equals(authorder)) {
				tJson.add(js);
			}
		}
		JSONObject rJson = setADVOrder(tJson);
		return rJson;
	}

	public JSONObject setADVOrder(List<JSONObject> json) {
		if (json.size() == 0) {
			return null;
		}
		JSONObject result = new JSONObject();
		JSONObject tsj = json.get(0);
		result.accumulate("date", tsj.get("date"));
		result.accumulate("stime", tsj.get("stime"));
		result.accumulate("etime", tsj.get("etime"));
		result.accumulate("modelid", tsj.get("modelid"));
		result.accumulate("busid", tsj.get("busid"));
		result.accumulate("routeid", tsj.get("routeid"));
		result.accumulate("url", tsj.get("url"));
		result.accumulate("skinname", tsj.get("skinname"));
		// �Թ�漯���λΪ��λ���� �����Ʒ���Ӽ���
		JSONArray posJson = new JSONArray();
		for (int i = 0; i < json.size(); i++) {

			JSONObject advgroup = new JSONObject();
			advgroup.accumulate("advposid", JSONUtils.getString(json.get(i), "advposid"));
			advgroup.accumulate("advgroupid", JSONUtils.getString(json.get(i), "advgroupid"));
			advgroup.accumulate("playstrager", JSONUtils.getString(json.get(i), "playstrager"));
			advgroup.accumulate("playintervalseral", JSONUtils.getString(json.get(i), "playintervalseral"));
			advgroup.accumulate("playtimesseral", JSONUtils.getString(json.get(i), "playtimesseral"));

			List<JSONObject> advs = new ArrayList<JSONObject>();

			for (int j = 1; j <= 9; j++) {
				String advert = JSONUtils.getString(json.get(i), "advertid" + j);
				if (StringUtils.isNotBlank(advert)) {
					JSONObject advJson = new JSONObject();
					advJson.accumulate("materid", JSONUtils.getString(json.get(i), "materid" + j));
					advJson.accumulate("adverturl", JSONUtils.getString(json.get(i), "adverturl" + j));
					advJson.accumulate("advertid", JSONUtils.getString(json.get(i), "advertid" + j));
					advJson.accumulate("name", JSONUtils.getString(json.get(i), "advertname" + j));
					advJson.accumulate("materpath", JSONUtils.getString(json.get(i), "materpath" + j));
					advs.add(advJson);
				}
			}
			advgroup.accumulate("advList", advs);
			posJson.add(advgroup);
		}
		result.accumulate("groupList", posJson);
		return result;
	}

	@Transactional(readOnly = true)
	public String portal(String mac, String phone, String phonemac, HttpSession session, String url, Happen happen) {
		// TODO Auto-generated method stub
		String URL = null;
		Object authInfos = session.getAttribute("authInfo");
		List<JSONObject> authInfo = JSONArray.fromObject(authInfos);
//		log.info("=====================authInfo:"+authInfo.toString());
		JSONObject result = getByAuthFlag(authInfo, PROCEDURCES.AUTHSUCCESSFLAG);
//		log.info("=====================result:"+result.toString());
		URL = authlogin(result, mac, phone, phonemac, happen);
		if (URL == null) {
			
			URL = serverhttp + defaultwifiauthsuccess_url + "?url='" + url + "'";
			log.info("������֤�ɹ�Ĭ�Ͻ���"+url);
		} else {
			URL += "&url='" + url + "'";
			log.info("������֤�ɹ�������:"+url);
		}
//		int wifiAuthId = vehicleWifiDao.getWifiAuthId();
		log.info("��¼������־");
		vehicleWifiDao.saveRecordNet( phonemac, phone, mac);
		return URL;
	}

	public String authlogin(JSONObject products, String apmac, String phone, String phonemac, Happen happen) {
		java.util.Random random = new java.util.Random();// ���������
		List<JSONObject> advinfoList = null;
//		 log.info("=======>:��ʼ��ѯ��֤��");
		if (products != null && products.size() > 0) {
			// ��ȡ��ƷͶ�����ڵĳ�������·��ģ�棬Ƥ����ģ��ID
			String busid = JSONUtils.getString(products, "busid");
			String routeid = JSONUtils.getString(products, "routeid");
			String URL = JSONUtils.getString(products, "url");
			String skinname = JSONUtils.getString(products, "skinname");
			String modelid = JSONUtils.getString(products, "modelid");
			URL=serverhttp+URL;
			URL += "?modelid='" + modelid + "'&modelskin=" + skinname + "&";
			log.info("======<<<<::::"+products.getJSONArray("groupList").size());
			for (int i = 0; i < products.getJSONArray("groupList").size(); i++) {
				// ��������������ѯ��漯
				/**
				 * products Ͷ�ŵĲ�Ʒ,(�����˽����) happen ���������� apmac AP�����ַ
				 * ����һ�����λ��һ�����Ľ��
				 */
				JSONObject tempJson = getAdvInfo(random, products, i, happen, apmac);
				String advposid = JSONUtils.getString(products.getJSONArray("groupList").getJSONObject(i), "advposid");
				if (tempJson != null && !tempJson.isEmpty() && tempJson.size() > 0
						&& StringUtils.isNotEmpty(advposid)) {
					String advertid = JSONUtils.getString(tempJson, "advertid");
					String materpath = JSONUtils.getString(tempJson, "materpath");
					URL += "materurl" + advposid + "='" + serverhttp+materpath + "'&" + "advertid" + advposid + "='" + advertid
							+ "'&";
				}
			}
			URL = URL.substring(0, URL.length() - 1);
			log.info("apmac:"+apmac+"  phonemac:"+phonemac+"  ɸѡ������2URL��"+URL);
			return URL;
		} else {
			return null;// Ĭ�Ͻ���
		}
	}

	/**
	 * ��ȡ��漯�е�ÿһ�����
	 * 
	 * @param priids
	 *            ��������
	 * @param random
	 *            �����
	 * @param products
	 *            һ��AP��Ӧ��һ�����
	 * @param advgroupid
	 * @return
	 */
	private JSONObject getAdvInfo(java.util.Random random, JSONObject products, int advgroupid, Happen happen,
			String apmac) {
		List<JSONObject> advinfoList = new ArrayList<JSONObject>();
		/**
		 * advgroup1 Ͷ�ŵĹ�漯 datetime ��漯��Ч��ʼʱ�� edatetime ��漯��Ч����ʱ��
		 */
		String stime = JSONUtils.getString(products, "stime");// products.getString("stime");
		String etime = JSONUtils.getString(products, "etime");
		String date = JSONUtils.getString(products, "date");
		String datetime = date + " " + stime;
		String edatetime = date + " " + etime;
		// ȡ����ǰ��漯�����ȼ��ֲ�����,
		JSONObject radvid = null;
		JSONArray advs = products.getJSONArray("groupList").getJSONObject(advgroupid).getJSONArray("advList");
//		log.info("advs===>"+advgroupid+"  ====>"+advs.toString());
		// ȡ����漯�����еĹ��
		// ���ݿͻ���������Ϣ��ȡ����������Ͷ�ŵĹ�漯��
		JSONArray priids = getPriAdv(advs, happen, apmac);
		// ���������ȥɸѡ�Ƿ��з�������������Ͷ�ŵĹ��
		if (!priids.isEmpty() && priids.size() > 0) {
//			log.info("==============>�������ȼ�ѡ��===��" + priids + "  ����:" + priids.size());
			int radomNumAdv = random.nextInt(priids.size());
			radvid = priids.getJSONObject(radomNumAdv);
			// log.info("�з�������������Ͷ�Ź�漯��ֱ�ӽ��������ѡ����棺"+advid);
		} else {
			// ���û�����ȼ��������������ֲ�
			radvid = getCommonadv(random, datetime, edatetime, products, advgroupid, advs);
			// log.info("���û�����ȼ��������������ֲ�:"+advid);
		}
		return radvid;
	}

	/**
	 * ��ȡ��������Ϣ
	 * 
	 * @param random
	 * @param advgroup1
	 * @param stime
	 * @param playtype
	 * @param advs
	 * @return
	 */
	private JSONObject getCommonadv(java.util.Random random, String stime, String edatetime, JSONObject products,
			int advgroupid, JSONArray advs) {
		JSONObject radvid = null;

		JSONObject group = products.getJSONArray("groupList").getJSONObject(advgroupid);
		// ���û�з������� �����ȼ���漯�������ֲ�
		String playstrage = JSONUtils.getString(group, "playstrager");
		String advgroupids = JSONUtils.getString(group, "advgroupid");
		// �������"playstrager","playintervalseral","playtimesseral
		int stragertime = 0;
		String stragertimes = JSONUtils.getString(group, "playintervalseral");
		if (!StringUtils.isEmpty(stragertimes)) {
			stragertime = Integer.parseInt(stragertimes);
		}
		if (PROCEDURCES.DATESTREGER.equals(playstrage)) {
			// log.info("==============��ʱ��������ֲ���");
			long nowTime = System.currentTimeMillis();
			Date starttime = DateUtil.getStrToDate(stime, "yyyy-MM-dd hh:mm:ss");
			Date endtime = DateUtil.getStrToDate(stime, "yyyy-MM-dd hh:mm:ss");
			long sttime = starttime.getTime();
			int totaltimes = (int) (nowTime - sttime) / 1000;
//			 log.info("==============��ǰʱ������ֲ�ʱ����ʼʱ��:"+stime+
//			 "��ǰʱ��:"+DateUtil.getCurrentDate()+" ����ʱ��:"+edatetime+
//			 "һ����"+totaltimes+"��");

			int advsSize = advs.size();
			// log.info("���������:"+advsSize);
			int advnumber = totaltimes / stragertime;
			int advsnum = advnumber % advsSize;
//			 log.info("�ֲ����Լ������:"+stragertime+"�룬ִ���˵�"+advnumber+"�β��� �ֵ���"+advsnum+"��");
			radvid = advs.getJSONObject(advsnum);
		} else if (PROCEDURCES.TIMESSTREGER.equals(playstrage)) {
			String actualtimes = JSONUtils.getString(group, "playtimesseral");
			if (StringUtils.isEmpty(actualtimes)) {
				actualtimes = "0";
			}
			int actualtime = 0;
			if (!StringUtils.isEmpty(actualtimes)) {
				actualtime = Integer.parseInt(actualtimes);
			}
			if (actualtime >= advs.size() * stragertime) {
				actualtime = 0;
			}
			// �������ʵ��չʾ����

			int advnumber = actualtime / stragertime;
//			 log.info("���ǵ�"+actualtime+"�β��� �ֲ�������:"+stragertime+"��"+advnumber+"�����");
			radvid = advs.getJSONObject(advnumber);
			// �����Ż���
			int updatetimes = vehicleWifiDao.updateAdvgrouptimes(advgroupids, ++actualtime);
		} else if (PROCEDURCES.RANDOMSTREGER.equals(playstrage)) {
			int radomNumAdv = random.nextInt(advs.size());
			radvid = advs.getJSONObject(radomNumAdv);
		}
		return radvid;
	}

	@Transactional
	public String saveClickCount(CLICK_COUNT_LOG logg) {
		// // TODO Auto-generated method stub
		String url = null;
		String mac = logg.getApmac();
		String advertid = logg.getAdvertid();

		int result = vehicleWifiDao.saveClickCount(logg);
		// log.info("��� �Ĺ��������:"+url);
		return url;
	}

	@Transactional(readOnly = true)
	public boolean toVaild(String code, String phone) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public int saveShowCount(SHOW_COUNT_LOG count_LOG) {
		// TODO Auto-generated method stub
		String mac = count_LOG.getApmac();
		String advertid = count_LOG.getAdvertid();
		int result = vehicleWifiDao.saveShowCount(count_LOG);
		return result;
	}

	@Override
	public List<JSONObject> getApInfo(String gw_id) {
		// TODO Auto-generated method stub
		return vehicleWifiDao.getApInfo(gw_id);
	}

	private JSONArray getPriAdv(JSONArray advs, Happen happen, String apmac) {
		List<String> advids = new ArrayList<String>();
		for (int i = 0; i < advs.size(); i++) {
			advids.add(JSONUtils.getString(advs.getJSONObject(i), "advertid"));
		}
		long f = System.currentTimeMillis();
		// log.info("��ѯ����Ͷ����Ϣ");
		List<JSONObject> json = vehicleWifiDao.getPriadvByCondit(advids, happen, apmac);
		// log.info("��ѯ����Ͷ����Ϣ ������ʱ��"+(System.currentTimeMillis()-f));
		List tempadvIds = new ArrayList();
		for (int i = 0; i < json.size(); i++) {
			JSONObject tempJson = json.get(i);
			String adrid = JSONUtils.getString(tempJson, "adrid");
			String adrname = JSONUtils.getString(tempJson, "adrname");
			String advid = JSONUtils.getString(tempJson, "advid");
			if (StringUtils.isBlank(adrid) && StringUtils.isBlank(adrname)) {
				tempadvIds.add(advid);
			} else {
				Area area = happen.getNowpoint();
				if (area == null) {
					continue;
				}
				List<Area> areas = new ArrayList<Area>();
				for(int j=1;j<=9;j++){
					String _long = JSONUtils.getString(tempJson, "long"+j);
					String _lat = JSONUtils.getString(tempJson, "lat"+j);
					if (StringUtils.isNoneEmpty(_long) && StringUtils.isNoneEmpty(_lat)) {
						Area a = new Area(Double.parseDouble(_long), Double.parseDouble(_lat));
						areas.add(a);
					}
				}
				if (GraphicalMain.isPointInPolygon(area.getPy(), area.getPx(), areas)) {
					tempadvIds.add(advid);
				}
			}

		}
		JSONArray result = new JSONArray();
		for (int i = 0; i < advs.size(); i++) {
			String advertId = JSONUtils.getString(advs.getJSONObject(i), "advertid");
			for (int j = 0; j < tempadvIds.size(); j++) {
				if (advertId.equals(tempadvIds.get(j))) {
					result.add(advs.getJSONObject(i));
					break;
				}
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		String s="http://218.108.10.25:8080/pages/advertModel/wifisuccess2.3.jsp?modelid='268'&modelskin=css/advertModel/model_a.css&materurlwifibg='http://218.108.10.25:8080/resource/fengjing.jpg'&advertidwifibg='441'&";
		System.out.println(s.substring(0, s.length()-1));
	}
}