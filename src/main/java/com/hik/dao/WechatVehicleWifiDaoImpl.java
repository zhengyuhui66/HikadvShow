package com.hik.dao;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.hik.app.entity.CLICK_COUNT_LOG;
import com.hik.app.entity.Happen;
import com.hik.app.entity.PRICONDITION;
import com.hik.app.entity.SHOW_COUNT_LOG;
import com.hik.framework.utils.JSONUtils;

import net.sf.json.JSONObject;

@Repository
public class WechatVehicleWifiDaoImpl extends BaseHIKDao implements WechatVehicleWifiDao{
	protected Log log = LogFactory.getLog(this.getClass()); 
	
	@Autowired
	@Qualifier(value="smsDataSource")
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	@Qualifier(value="jdbcTemplate")
	private JdbcTemplate jdbc;

	public void sendSMSCodeDao(String phone,String smsContent,String time,int type){
		// TODO Auto-generated method stub
		jdbcTemplate.update( "insert into sms_sxggjtjt_send(Col_receiver,Col_content,Col_sdate,Col_type) values(?,?,?,?)" ,
                new Object[]{phone,smsContent,time,type},
                new int[]{java.sql.Types.VARCHAR,java.sql.Types.VARCHAR,java.sql.Types.DATE,java.sql.Types.INTEGER});
	}


	public int saveClickCount(CLICK_COUNT_LOG logg) {
		// TODO Auto-generated method stub
		List param = new ArrayList();
		param.add(logg.getClick_time());
		param.add(logg.getBusid());
		param.add(logg.getApmac());
		param.add(logg.getPhone());
		param.add(logg.getPhonemac());
		param.add(logg.getModelid());
		param.add(logg.getModelmodeid());
		param.add(logg.getMaterid());
		param.add(logg.getAdvert_url());
		param.add(logg.getAdvertid());
		param.add(logg.getLineid());
		String sql="insert into CLICK_COUNT_LOG(id,click_time,busid,apmac,phone,phonemac,modelid,modelmodeid,materid,advert_url,advertid,lineid) values(CLICKOUNTLOG_SEQ.nextval,?,?,?,?,?,?,?,?,?,?,?)";
		int t=executeUpdateSQL(sql, param, "记录点击日志");
		return t;
	}

	public int saveShowCount(SHOW_COUNT_LOG showlogs) {
		// TODO Auto-generated method stub
		List param = new ArrayList();
//		param.add(showlogs.getShow_time());
		param.add(showlogs.getBusid());
		param.add(showlogs.getApmac());
		param.add(showlogs.getPhone());
		param.add(showlogs.getPhonemac());
		param.add(showlogs.getModelid());
		param.add(showlogs.getModelmodeid());
		param.add(showlogs.getMaterid());
		param.add(showlogs.getAdvert_url());
		param.add(showlogs.getAdvertid());
		param.add(showlogs.getLineid());
		String sql="INSERT INTO SHOW_COUNT_LOG(ID,show_time,busid,apmac,phone,phonemac,Modelid,modelmodeid,Materid,Advert_Url,advertid,lineid) "+
				   "VALUES  "+
				   "(SHOWCOUNTLOG_SEQ.NEXTVAL,TO_CHAR(SYSDATE, 'yyyy-mm-dd hh24:mi:ss'),?,?,?,?,?,?,?,?,?,?) ";
		int t=executeUpdateSQL(sql, param,"记录查询次数");
		return t;
	}



	public int saveRecordNet(String phone_mac, String phone,String apmac) {
		// TODO Auto-generated method stub
		List param = new ArrayList();
		param.add(phone_mac);
		param.add(phone);
		param.add(apmac);
		String sql="INSERT INTO WIFIAUTH(ID,mac,phone,deviceid,authtime,createtime) values(wifiauth_seq.nextval,?,?,(select ID from device t where apmac=?),to_date(to_char(SYSDATE, 'yyyy-MM-dd hh24:mi:ss'),'yyyy-MM-dd hh24:mi:ss'), to_date(to_char(SYSDATE, 'yyyy-MM-dd hh24:mi:ss'),'yyyy-MM-dd hh24:mi:ss'))";
		return executeUpdateSQL(sql, param,"插入WIFIauth的语句是");
	}

//	public int getWifiAuthId() {
//		// TODO Auto-generated method stub  
//		String sql="select wifiauth_seq.nextval from dual";
//		return executeUpdateSQL(sql);
//		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("SP_GETTABLESEQUENCE");
//		storedProcedure.registerStoredProcedureParameter("TABLE_NAME", String.class, ParameterMode.IN);
//		storedProcedure.registerStoredProcedureParameter("TABLE_PREFIX", String.class, ParameterMode.IN);
//		storedProcedure.registerStoredProcedureParameter("TABLE_SEQUENCE", Integer.class, ParameterMode.OUT);
//		storedProcedure.setParameter("TABLE_NAME","WIFIAUTH");
//		storedProcedure.setParameter("TABLE_PREFIX","");
//		Object obj=storedProcedure.getOutputParameterValue("TABLE_SEQUENCE");
//		int result=Integer.parseInt(obj.toString());
//		return result;
//	}

	@Override
	public List getLoginDate(String phonemac) {
		// TODO Auto-generated method stub
		List param = new ArrayList();
		param.add(phonemac);
		String sql="SELECT id,mac,phone,to_char(authtime,'yyyy-MM-dd hh24:mi:ss'),deviceid FROM (SELECT * FROM WIFIAUTH WHERE mac=? ORDER BY authtime DESC) WHERE ROWNUM=1";
		String[] str = new String[]{"id","mac","phone","authtime","deviceid"};
		return getNoPageObject(sql, param, str);
	}
	@Override
	public List<JSONObject> getApInfo(String gw_id) {
		// TODO Auto-generated method stub
		List params = new ArrayList();
		params.add(gw_id);
//		String sql="select temp.* FROM (SELECT R.SPEED, R.TIMEOUT,tr.longitude,tr.latitude "+
//			 "FROM DEVICE T "+
//			  "LEFT JOIN VEHICLE V "+
//			    "ON (T.VEHICLEID = V.ID) "+
//			  "LEFT JOIN ROUTE R "+
//			    "ON (V.ROUTEID = R.ID) "+
//			  "left JOIN DEVICETRACK Tr "+
//			   "ON (tr.DEVICEID = T.ID) "+
//			  "WHERE T.APMAC = ? AND t.state='1' "+
//			  "ORDER BY tr.CAPTURETIME DESC) temp WHERE ROWNUM = 1";
		String sql="SELECT T.SPEED, T.TIMEOUT,'' AS longitude,'' AS latitude "+
					"FROM DEVICE T "+
					"WHERE T.APMAC =? AND t.state='1'";
	        
		String[] str = new String[]{"speed","timeout","longitude","latitude"};
		return getNoPageObject(sql, params, str/*,"查询AP状态信息"*/);
	}
	@Override
	public List getAlladvgroups(String mac, String cycid) {
		// TODO Auto-generated method stub
		List param = new ArrayList();
		param.add(mac);
		param.add(cycid);
		String sql="select s.datetime,t.starttime,t.endtime,a.id,v.vehicleid,vv.routeid,a.url,sk.skinname,a.advposid1,a.advposid2,a.advposid3,a.advposid4,a.advposid5,p.advgroup1,p.advgroup2,p.advgroup3,p.advgroup4,p.advgroup5 from SCHEDULE s "+
				"LEFT JOIN TIMESETTING t ON(t.id=s.interval) "+
				"LEFT JOIN DEVICE v ON(s.apid=v.id) "+
				"LEFT JOIN PUTPRODUCT p ON(s.productid=p.id) "+
				"LEFT JOIN ADVERTMODELMANGER a ON(a.id=p.modelid)  "+
				"LEFT JOIN SKIN_MANGER sk ON(sk.id=a.modelskin) "+
				"LEFT JOIN VEHICLE vv ON(vv.id=v.vehicleid) "+
				"WHERE v.apmac=?  "+
				"AND to_char(TO_CHAR(SYSDATE, 'yyyy-mm-dd'))=s.datetime "+ 
				"AND to_char(TO_CHAR(SYSDATE, 'hh24:mi:ss'))>t.starttime  "+
				"AND to_char(TO_CHAR(SYSDATE, 'hh24:mi:ss'))<t.endtime "+
				"AND s.authorder=? "+
				"AND s.state=1 ";
		String[] str = new String[]{"date","stime","etime","modelid","busid","routeid","url","skinname","advposid1","advposid2","advposid3","advposid4","advposid5","advgroup1","advgroup2","advgroup3","advgroup4","advgroup5"};
		return getNoPageObject(sql, param, str,"查询所有的符合条件的广告产品");
	}

	@Override
	public List getAllMMPP(String mac, String cycid){
		// TODO Auto-generated method stub
		List param = new ArrayList();
		param.add(mac);
		param.add(cycid);
		String sql= "SELECT s.datetime,t.starttime,t.endtime,v.vehicleid,vv.routeid,"+
				 	"m.id,m.mmppid1,m.mmppid2,m.mmppid3,m.mmppid4,m.mmppid5,m.mmppid6,m.mmppid7,m.mmppid8,m.mmppid9,m.playstrager,m.playtimeseral,m.playintervalseral,p.interval from SCHEDULE s "+
					"LEFT JOIN TIMESETTING t ON(t.id=s.interval) "+
					"LEFT JOIN DEVICE v ON(s.apid=v.id) "+
					"LEFT JOIN VEHICLE vv ON(vv.id=v.vehicleid) "+
					"LEFT JOIN MMPP_PRODUCT m ON(m.id=s.productid) "+
					"LEFT JOIN PLAY_FUN p ON(m.playstrager=p.cid) "+
					"WHERE v.apmac=? "+
					"AND to_char(TO_CHAR(SYSDATE, 'yyyy-mm-dd'))=s.datetime "+
					"AND to_char(TO_CHAR(SYSDATE, 'hh24:mi:ss'))>t.starttime "+
					"AND to_char(TO_CHAR(SYSDATE, 'hh24:mi:ss'))<t.endtime "+
					"AND s.authorder=? "+
					"AND s.state=1 ";
		String[] str = new String[]{"datetime","starttime","endtime","vehicleid","routeid","id","mmppid1","mmppid2","mmppid3","mmppid4","mmppid5","mmppid6","mmppid7","mmppid8","mmppid9","playstrager","playtimeseral","playintervalseral","interval"};
		return getNoPageObject(sql, param, str,"查询所有的符合条件的广告产品");
	}

	@Override
	public List getPriid(PRICONDITION pricondition) {
		// TODO Auto-generated method stub
		List param = new ArrayList();

		String sql="select ID from PRICONDITION t "+
					"WHERE to_char(TO_CHAR(SYSDATE, 'hh24:mi:ss'))>t.stime "+ 
					"AND  to_char(TO_CHAR(SYSDATE, 'hh24:mi:ss'))<t.etime ";
		String address=pricondition.getAddredssid();
		if(!StringUtils.isEmpty(address)){
			sql+="AND t.ADDREDSSID=? ";
			param.add(address);
		}
		
		String eventid=pricondition.getEventid();
		if(!StringUtils.isEmpty(eventid)){
			sql+="AND t.EVENTID=? ";
			param.add(eventid);
		}
		
		String phone=pricondition.getPhone();
		if(!StringUtils.isEmpty(phone)){
			sql+="AND PHONE=? ";
			param.add(phone);
		}
		
//		String phonesys=pricondition.getPhonesystem();
//		if(!StringUtils.isEmpty(phonesys)){
//			sql+="AND PHONESYSTEM=? ";
//			param.add(phonesys);
//		}
//		
//		String phonetype=pricondition.getPhonetype();
//		if(!StringUtils.isEmpty(phonetype)){
//			sql+="AND PHONETYPE=? ";
//			param.add(phonetype);
//		}
//		
//		String viewtype=pricondition.getViewtype();
//		if(!StringUtils.isEmpty(viewtype)){
//			sql+="AND VIEWTYPE=? ";
//			param.add(viewtype);
//		}
//		
//		String clicktype=pricondition.getClicktype();
//		if(!StringUtils.isEmpty(clicktype)){
//			sql+="AND CLICKTYPE=? ";
//			param.add(clicktype);
//		}
		return getNoPageObject(sql, param,"优先条件的SQL");
	}

	@Override
	public List getAllpriadvgroups(List priids,String groupId) {
		// TODO Auto-generated method stub
		List param = new ArrayList();
		if(priids.isEmpty()||priids.size()==0){
			return null;
		}
	String sql="SELECT t.advid1 AS advid from ADVGROUP t JOIN adv_pricondition a ON (t.advid1=a.advid) WHERE t.ID=? AND a.priconditionid IN (";
		param.add(groupId);
		for(int i=0;i<priids.size();i++){
			sql+="?,";
		}
		param.addAll(priids);
		sql=sql.substring(0, sql.length()-1);
		sql+=")";
		sql+="UNION select t.advid2 AS advid from ADVGROUP t JOIN adv_pricondition a ON (t.advid2=a.advid) WHERE t.ID=? AND a.priconditionid IN (";
		param.add(groupId);
		for(int i=0;i<priids.size();i++){
			sql+="?,";
		}
		param.addAll(priids);
		sql=sql.substring(0, sql.length()-1);
		sql+=")";	
		sql+="UNION select t.advid3 AS advid from ADVGROUP t JOIN adv_pricondition a ON (t.advid3=a.advid) WHERE t.ID=? AND a.priconditionid IN (";
		param.add(groupId);
		for(int i=0;i<priids.size();i++){
			sql+="?,";
		}
		param.addAll(priids);
		sql=sql.substring(0, sql.length()-1);
		sql+=")";	
		sql+="UNION select t.advid4 AS advid from ADVGROUP t JOIN adv_pricondition a ON (t.advid4=a.advid) WHERE t.ID=? AND a.priconditionid IN (";
		param.add(groupId);
		for(int i=0;i<priids.size();i++){
			sql+="?,";
		}
		param.addAll(priids);
		sql=sql.substring(0, sql.length()-1);
		sql+=")";	
		sql+="UNION select t.advid5 AS advid from ADVGROUP t JOIN adv_pricondition a ON (t.advid5=a.advid) WHERE t.ID=? AND a.priconditionid IN (";
		param.add(groupId);
		for(int i=0;i<priids.size();i++){
			sql+="?,";
		}
		param.addAll(priids);
		sql=sql.substring(0, sql.length()-1);
		sql+=")";	
		sql+="UNION select t.advid6 AS advid from ADVGROUP t JOIN adv_pricondition a ON (t.advid6=a.advid) WHERE t.ID=? AND a.priconditionid IN (";
		param.add(groupId);
		for(int i=0;i<priids.size();i++){
			sql+="?,";
		}
		param.addAll(priids);
		sql=sql.substring(0, sql.length()-1);
		sql+=")";	
		sql+="UNION select t.advid7 AS advid from ADVGROUP t JOIN adv_pricondition a ON (t.advid7=a.advid) WHERE t.ID=? AND a.priconditionid IN (";
		param.add(groupId);
		for(int i=0;i<priids.size();i++){
			sql+="?,";
		}
		param.addAll(priids);
		sql=sql.substring(0, sql.length()-1);
		sql+=")";	
		sql+="UNION select t.advid8 AS advid from ADVGROUP t JOIN adv_pricondition a ON (t.advid8=a.advid) WHERE t.ID=? AND a.priconditionid IN (";
		param.add(groupId);
		for(int i=0;i<priids.size();i++){
			sql+="?,";
		}
		param.addAll(priids);
		sql=sql.substring(0, sql.length()-1);
		sql+=")";	
		sql+="UNION select t.advid9 AS advid from ADVGROUP t JOIN adv_pricondition a ON (t.advid9=a.advid) WHERE t.ID=? AND a.priconditionid IN (";
		param.add(groupId);
		for(int i=0;i<priids.size();i++){
			sql+="?,";
		}
		param.addAll(priids);
		sql=sql.substring(0, sql.length()-1);
		sql+=")";	
		return getNoPageObject(sql, param);
	}

	@Override
	public List getAdvGroup(String groupId) {
		// TODO Auto-generated method stub
		List param = new ArrayList();
		param.add(groupId);
		String sql="SELECT a.ADVID1,"
					+ "a.ADVID2,"
					+ "a.ADVID3,"
					+ "a.ADVID4,"
					+ "a.ADVID5,"
					+ "a.ADVID6,"
					+ "a.ADVID7,"
					+ "a.ADVID8,"
					+ "a.ADVID9,"
					+ "a.PLAYSTRAGER,"
					+ "a.PLAYINTERVALSERAL,"
					+ "a.PLAYTIMESSERAL,"
					+ "p.interval FROM ADVGROUP a LEFT JOIN PLAY_FUN p ON(a.playstrager=p.cid) where a.id=?";
		String[] str = new String[]{"advid1","advid2","advid3","advid4","advid5","advid6","advid7","advid8","advid9","playstrager","playintervalseral","playtimesseral","interval"};
		return getNoPageObject(sql, param, str,"查询广告集具体信息");
	}

	@Override
	public List<JSONObject> getAdvInfo(String advid) {
		// TODO Auto-generated method stub
		List param = new ArrayList();
		param.add(advid);
		String sql="select t.id,t.advert_url,m.mater_path,m.mater_id from ADVERTMANGER t LEFT JOIN materiel_info m on(t.mater_id=m.mater_id) WHERE t.ID=?";
		String[] str = new String[]{"advertid","adverturl","materpath","materid"};
		return getNoPageObject(sql, param, str,"查询广告信息");
	}
	@Override
	public List<JSONObject> getMmppInfo(String mmppid) {
		// TODO Auto-generated method stub
		List param = new ArrayList();
		param.add(mmppid);
		String sql="select t.id,t.name,t.wechatid,t.appid,t.appsecret,t.orgid,t.shopid,t.secretkey from MMPP t WHERE t.id=?";
		String[] str = new String[]{"id","name","wechatid","appid","appsecret","orgid","shopid","secretKey"};
		return getNoPageObject(sql, param, str,"查询公众号信息");
	}

	@Override
	public int updateAdvgrouptimes(String advgroupid, int times) {
		// TODO Auto-generated method stub
		List param = new ArrayList();
		param.add(times);
		param.add(advgroupid);
		String sql="update ADVGROUP set PLAYTIMESSERAL=? where id=?";
		return executeUpdateSQL(sql, param,"更新新增次数");
	}
	@Override
	public int updatemmpProducttimes(String mmppid, int times) {
		// TODO Auto-generated method stub
		List param = new ArrayList();
		param.add(times);
		param.add(mmppid);
		String sql="update MMPP_PRODUCT set PLAYTIMESERAL=? where id=?";
		return executeUpdateSQL(sql, param,"更新新增次数");
	}
	@Override
	public List<JSONObject> queryDetailInfo(String mac, String advertid) {
		// TODO Auto-generated method stub
		/**
		 * 车辆ID
		 * 线路ID
		 * 广告链接地址
		 * 物料ID
		 */
		List param = new ArrayList();
		param.add(advertid);
		param.add(mac);
		String sql="select t.advert_url,t.mater_id,V.id,V.routeid from ADVERTMANGER t join VEHICLE V LEFT JOIN DEVICE d ON(V.id=d.vehicleid) on t.id=? AND D.APMAC=?";
		String[] str = new String[]{"adverturl","materid","busid","routeid"};
		return getNoPageObject(sql, param, str);
	}
	@Override
	public List<JSONObject> getSpeedAndTime(String gw_id) {
		// TODO Auto-generated method stub
		List params = new ArrayList();
		params.add(gw_id);
		String sql="select r.speed,r.timeout from DEVICE t LEFT JOIN vehicle v ON(t.vehicleid=v.id) LEFT JOIN ROUTE r ON(v.routeid=r.id) WHERE t.apmac=?";
		String[] str = new String[]{"speed","timeout"};
		return getNoPageObject(sql, params, str);
	}


	@Override
	public List<JSONObject> queryPriByCondition(List<String> advid, String timeid,/* String clientsys, String clienttype,*/
			String phone) {
		// TODO Auto-generated method stub
		List params = new ArrayList();
		String sql="";
		log.info("广告集advid:"+advid);
		log.info("优先时间timeid:"+timeid);
//		log.info("优先系统clientsys:"+clientsys);
//		log.info("优先终端类型clienttype:"+clienttype);
		log.info("优先手机号phone:"+phone);
		for(int i=0;i<advid.size();i++){
			String tempadvid=advid.get(i).toString();
			params.add(tempadvid);
//			sql+="SELECT p.timeid,p.addredssid,p.eventid,p.phonetype,p.phonesystem,p.phone,'"+tempadvid+"' as advid FROM pricondition p "+
			sql+="SELECT p.timeid,p.addredssid,p.eventid,p.phone,'"+tempadvid+"' as advid FROM pricondition p "+
				"LEFT JOIN  timerelation t on(p.timeid=t.groupid) LEFT JOIN timesign ts ON (t.timeid=ts.id) "+
//				"LEFT JOIN  clienttyperelation ctr on(p.phonetype=ctr.groupid) LEFT JOIN clienttypesign cts ON (ctr.typeid=cts.id) "+
//				"LEFT JOIN  sysoperrelation srt on(p.phonesystem=srt.sysoperid) LEFT JOIN sysopersign sos ON (srt.sysoperid=sos.id) "+
				"LEFT JOIN  telphonerelation tpr on(p.phone=tpr.groupid) LEFT JOIN telphonesign tps ON (tps.id=tpr.telphoneid) "+
				"LEFT JOIN  eventrelation er on(p.eventid=er.groupid) LEFT JOIN eventsign es ON (es.id=er.eventid) "+
				"WHERE p.ID IN  (SELECT PRICONDITIONID FROM ADV_PRICONDITION WHERE advid=?) ";
				if(StringUtils.isNotEmpty(timeid)){
					sql+="AND ts.id IN (SELECT ID FROM timesign WHERE (stime||' '||sdtime)<? AND (etime||' '||edtime)>?) ";
					params.add(timeid);
					params.add(timeid);
				}
				if(StringUtils.isNotEmpty(phone)){
					sql+="AND tps.id IN (SELECT ID FROM telphonesign WHERE name=?) ";
					params.add(phone);
				}
//				if(StringUtils.isNotEmpty(clienttype)){
//					sql+="AND cts.id IN (SELECT ID FROM clienttypesign WHERE NAME=? ) ";
//					params.add(clienttype);
//				}
//				if(StringUtils.isNotEmpty(clientsys)){
//					sql+="AND sos.id IN (SELECT ID FROM sysopersign WHERE name=?) ";
//					params.add(clientsys);
//				}
				sql+="AND es.id IN (SELECT ID FROM eventsign WHERE stime< to_char(TO_CHAR(SYSDATE, 'hh24:mi:ss')) AND etime> to_char(TO_CHAR(SYSDATE, 'hh24:mi:ss')))";
			if(i<advid.size()-1){
				sql+=" union ";				
			}
		}
		
		
		String[] strs= new String[]{"timeid","addressid","eventid","phonetype","phonesystem","phone","advid"};
		return getNoPageObject(sql, params, strs,"取出符合优先条件的条件信息");
	}


	@Override
	public List<JSONObject> getLatLong(String addressid) {
		// TODO Auto-generated method stub
		 String sql="SELECT longitude1,latitude1,"+
				    "longitude2,latitude2,"+
			        "longitude3,latitude3,"+
			        "longitude4,latitude4,"+
			        "longitude5,latitude5,"+
			        "longitude6,latitude6,"+
			        "longitude7,latitude7,"+
			        "longitude8,latitude8,"+
			        "longitude9,latitude9,"+
			        "longitude10,latitude10 "+
			        "FROM ADDRESSSIGN ASS "+
			        "LEFT JOIN ADDRESSRELATION ASR "+
			          "ON (ASS.ID = ASR.ADDRESSID) "+
			       "WHERE ASR.GROUPID = ? ";
		List param = new ArrayList();
		param.add(addressid);
		String[] str = new String[]{"longitude1","latitude1",
								   "longitude2","latitude2",
							        "longitude3","latitude3",
							        "longitude4","latitude4",
							        "longitude5","latitude5",
							        "longitude6","latitude6",
							        "longitude7","latitude7",
							        "longitude8","latitude8",
							        "longitude9","latitude9",
							        "longitude10","latitude10"};
		return getNoPageObject(sql, param,str);
	}


	@Override
	public List<JSONObject> getCurrentLatLongByMac(String Mac) {
		// TODO Auto-generated method stub
		List<String> params = new ArrayList<String>();
		params.add(Mac);
		String sql="SELECT * FROM (select t.longitude,t.latitude from DEVICETRACK t LEFT JOIN device d ON(t.deviceid=d.id) WHERE d.apmac=? ORDER BY capturetime DESC)  WHERE ROWNUM=1";
		String[] str = new String[]{"longitude","latitude"};
		return getNoPageObject(sql, params, str, "查询当前AP的物理地址");
	}
	//===================================================================================================
		@Override
		public List<JSONObject> getConditionByadvid(String id) {
			// TODO Auto-generated method stub
			
		       String sql="SELECT p.timeid,p.addredssid,p.eventid,p.phone FROM PRICONDITION p WHERE P.ID IN "+
		    		   		"(SELECT PRICONDITIONID FROM ADV_PRICONDITION WHERE ADVID = ?)";
		       String[] str = new String[]{"timeid","addredssid","eventid","phone"};
		       List param = new ArrayList();
		       param.add(id);
			return getNoPageObject(sql, param, str/*, "查询当前AP的物理地址"*/);
		}


		@Override
		public List getifadv(String advid, Happen happen, JSONObject json){
			// TODO Auto-generated method stub
			List params = new ArrayList();
			String timeid=JSONUtils.getString(json, "timeid");
			String eventid=JSONUtils.getString(json, "eventid");
			String phone=JSONUtils.getString(json, "phone");
			String sql="SELECT p.timeid,p.addredssid,p.eventid,p.phone,'"+advid+"' as advid FROM pricondition p "+
						"LEFT JOIN  timerelation t on(p.timeid=t.groupid) LEFT JOIN timesign ts ON (t.timeid=ts.id) "+
						"LEFT JOIN  telphonerelation tpr on(p.phone=tpr.groupid) LEFT JOIN telphonesign tps ON (tps.id=tpr.telphoneid) "+
						"LEFT JOIN  eventrelation er on(p.eventid=er.groupid) LEFT JOIN eventsign es ON (es.id=er.eventid) "+
						"WHERE p.ID IN  (SELECT PRICONDITIONID FROM ADV_PRICONDITION WHERE advid=?) ";
			params.add(advid);
			if(StringUtils.isNotEmpty(timeid)&&StringUtils.isNotEmpty(happen.getNowTime())){
				sql+="AND (ts.STIME || ' ' || ts.SDTIME) < ? "+
				     "AND (ts.ETIME || ' ' || ts.EDTIME) > ? "+
					"AND t.groupid=? ";
				params.add(happen.getNowTime());
				params.add(happen.getNowTime());
				params.add(timeid);
			}
			if(StringUtils.isNotEmpty(phone)&&StringUtils.isNotEmpty(happen.getPhone())){
				sql+="AND tps.id IN (SELECT ID FROM telphonesign WHERE name=?)"
						+ "and  tpr.groupid=?";
				params.add(happen.getPhone());
				params.add(phone);
			}
			if(StringUtils.isNotEmpty(eventid)&&happen.getEvent().size()>0){
				List<JSONObject> _json=happen.getEvent();
				sql+="AND es.id IN (";
				for(int i=0;i<_json.size();i++){
					sql+="?";
					params.add(JSONUtils.getString(_json.get(i), "id"));
				}
				sql=sql.substring(0,sql.length()-1);
				sql+=")";
				sql+="  and er.groupid=?";
				params.add(eventid);
			}
			String[] str = new String[]{"timeid","addredssid","eventid","phone","advid"};
			return getNoPageObject(sql, params, str, "查询条件结果");
		}


		@Override
		public List getAccesstoken(String appid, String appSecret) {
			// TODO Auto-generated method stub
			String sql="SELECT accesstoken FROM MMPP WHERE appid=? AND appsecret=?";
			List param = new ArrayList();
			param.add(appid);
			param.add(appSecret);
			return getNoPageObject(sql, param);
		}


		@Override
		public List<JSONObject> getPriadvByCondit(List advids,Happen happen,String apmac) {
			// TODO Auto-generated method stub
			String time = happen.getNowTime();
			String phone = happen.getPhone();
			List param = new ArrayList();
			String sql="";
			for(int i=0;i<advids.size();i++){
			sql+="SELECT ts.id as timeid,ts.name AS timename,tps.id as phoneid,tps.name AS phone,es.id as eventid,es.name AS event,adr.id as addressid,adr.name AS address,"+
					"adr.longitude1,adr.latitude1,"+
					"adr.longitude2,adr.latitude2,"+
					"adr.longitude3,adr.latitude3,"+
					"adr.longitude4,adr.latitude4,"+
					"adr.longitude5,adr.latitude5,"+
					"adr.longitude6,adr.latitude6,"+
					"adr.longitude7,adr.latitude7,"+
					"adr.longitude8,adr.latitude8,"+
					"adr.longitude9,adr.latitude9,"+
					"ap.advid AS advid FROM pricondition p "+
					"LEFT JOIN  timerelation t on(p.timeid=t.groupid)"+
					"LEFT JOIN timesign ts ON (ts.id=t.timeid)"+
					"LEFT JOIN  telphonerelation tpr on(p.phone=tpr.groupid)"+
					"LEFT JOIN telphonesign tps ON (tps.id=tpr.telphoneid)"+
					"LEFT JOIN  eventrelation er on(p.eventid=er.groupid)"+
					"LEFT JOIN eventsign es ON (es.id=er.eventid)"+
					"LEFT JOIN  addressrelation ad on(p.addredssid=ad.groupid)"+
					"LEFT JOIN addresssign adr ON (adr.id=ad.addressid) "+
					"LEFT JOIN ADV_PRICONDITION ap ON(ap.priconditionid=p.id) WHERE ap.advid=?"+
					"AND (es.stime< to_char(TO_CHAR(SYSDATE, 'yyyy-MM-dd hh24:mi:ss')) AND es.etime> to_char(TO_CHAR(SYSDATE, 'yyyy-MM-dd hh24:mi:ss')) OR p.eventid IS NULL)"+
					"AND ((ts.stime||' '||ts.sdtime<? AND ts.etime||' '||ts.edtime>?) OR p.timeid IS NULL)"+
					"AND (TPS.NAME=? OR p.phone IS NULL) UNION ALL ";
			param.add(advids.get(i));
			param.add(time);
			param.add(time);
			param.add(phone==null?"":phone);
			}
			sql=sql.substring(0, sql.length()-11);
			String[] str = new String[]{"timeid","timename","phoneid","phonename","eventid","eventname","adrid","adrname","long1","lat1"
										,"long2","lat2","long3","lat3"
										,"long4","lat4","long5","lat5"
										,"long6","lat6","long7","lat7"
										,"long8","lat8","long9","lat9"
										,"advid"};
			return getNoPageObject(sql, param, str/*,"查询符合优先投放的广告及其条件"*/);
		}

	
}
