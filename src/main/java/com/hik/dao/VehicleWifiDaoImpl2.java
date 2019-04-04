package com.hik.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.hik.app.entity.CLICK_COUNT_LOG;
import com.hik.app.entity.Happen;
import com.hik.app.entity.PRICONDITION;
import com.hik.app.entity.SHOW_COUNT_LOG;
import com.hik.framework.utils.JSONUtils;

import net.sf.json.JSONObject;
import oracle.jdbc.driver.OracleTypes;

@Repository
public class VehicleWifiDaoImpl2 extends BaseHIKDao implements VehicleWifiDao2{
	protected Log log = LogFactory.getLog(this.getClass()); 
	
//	@Autowired
//	@Qualifier(value="smsDataSource")
//	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	@Qualifier(value="jdbcTemplate")
	private JdbcTemplate jdbc;

//	public void sendSMSCodeDao(String phone,String smsContent,String time,int type){
//		// TODO Auto-generated method stub
//		jdbcTemplate.update( "insert into sms_sxggjtjt_send(Col_receiver,Col_content,Col_sdate,Col_type) values(?,?,?,?)" ,
//                new Object[]{phone,smsContent,time,type},
//                new int[]{java.sql.Types.VARCHAR,java.sql.Types.VARCHAR,java.sql.Types.DATE,java.sql.Types.INTEGER});
//	}
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
		int t=executeUpdateSQL(sql, param/*, "记录点击日志"*/);
		return t;
	}

	public int saveShowCount(SHOW_COUNT_LOG showlogs) {
		// TODO Auto-generated method stub
		List param = new ArrayList();
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
		int t=executeUpdateSQL(sql, param/*,"记录查询次数"*/);
		return t;
	}



	public int saveRecordNet(String phone_mac, String phone,String apmac) {
		// TODO Auto-generated method stub
		List param = new ArrayList();
//		param.add(id);
		param.add(phone_mac);
		param.add(phone);
		param.add(apmac);
		String sql="INSERT INTO WIFIAUTH(ID,mac,phone,deviceid,authtime,createtime) values(wifiauth_seq.nextval,?,?,(select ID from device t where apmac=?),to_char(TO_CHAR(SYSDATE, 'yyyy-MM-dd hh24:mi:ss')), to_char(TO_CHAR(SYSDATE, 'yyyy-MM-dd hh24:mi:ss')))";
		return executeUpdateSQL(sql, param/*,"插入WIFIauth的语句是"*/);
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
		String sql="SELECT id,mac,phone,authtime,deviceid FROM (SELECT * FROM WIFIAUTH WHERE mac=? ORDER BY authtime DESC) WHERE ROWNUM=1";
		String[] str = new String[]{"id","mac","phone","authtime","deviceid"};
		return getNoPageObject(sql, param, str/*,"查询最后登陆时间"*/);
	}

	@Override
	public List getAlladvgroups(String mac,String[] cycid) {
		// TODO Auto-generated method stub
		List param = new ArrayList();

		String sql="";
			for(int i=1;i<=5;i++){
				param.add(mac);
				sql+="SELECT S.DATETIME, "+
			       "T.STARTTIME,  "+
			       "T.ENDTIME,  "+
			       "A.ID,  "+
			       "V.VEHICLEID,  "+
			       "VV.ROUTEID,  "+
			       "A.URL,  "+
			       "SK.SKINNAME,  "+
			       "A.ADVPOSID"+i+"  AS ADVPOSID,  "+
			       "P.ADVGROUP"+i+"  AS ADVGROUPID,  "+
			       "AG.PLAYSTRAGER,  "+
			       "PF.INTERVAL,  "+
			       "AG.PLAYTIMESSERAL,  "+
			       "AV1.ID            AS ADVERTID1,  "+
			       "AV1.MATER_ID      AS MATERID1,  "+
			       "AV1.ADVERT_URL    AS ADVERTURL1,  "+
			       "AV1.NAME          AS ADVERTNAME1,  "+
			       "M1.MATER_PATH     AS MATERPATH1,  "+
			       
			       "AV2.ID         AS ADVERTID2,  "+
			       "AV2.MATER_ID   AS MATERID2,  "+
			       "AV2.ADVERT_URL AS ADVERTURL2,  "+
			       "AV2.NAME       AS ADVERTNAME2,  "+
			       "M2.MATER_PATH  AS MATERPATH2,  "+
			       
			       "AV3.ID         AS ADVERTID3,  "+
			       "AV3.MATER_ID   AS MATERID3,  "+
			       "AV3.ADVERT_URL AS ADVERTURL3,  "+
			       "AV3.NAME       AS ADVERTNAME3,  "+
			       "M3.MATER_PATH  AS MATERPATH3,  "+
			       
			       "AV4.ID         AS ADVERTID4,  "+
			       "AV4.MATER_ID   AS MATERID4,  "+
			       "AV4.ADVERT_URL AS ADVERTURL4,  "+
			       "AV4.NAME       AS ADVERTNAME4,  "+
			       "M4.MATER_PATH  AS MATERPATH4,  "+
			       
			       "AV5.ID         AS ADVERTID5,  "+
			       "AV5.MATER_ID   AS MATERID5,  "+
			       "AV5.ADVERT_URL AS ADVERTURL5,  "+
			       "AV5.NAME       AS ADVERTNAME5,  "+
			       "M5.MATER_PATH  AS MATERPATH5,  "+
			       
			       "AV6.ID         AS ADVERTID6,  "+
			       "AV6.MATER_ID   AS MATERID6,  "+
			       "AV6.ADVERT_URL AS ADVERTURL6,  "+
			       "AV6.NAME       AS ADVERTNAME6,  "+
			       "M6.MATER_PATH  AS MATERPATH6,  "+
			       
			       "AV7.ID         AS ADVERTID7,  "+
			       "AV7.MATER_ID   AS MATERID7,  "+
			       "AV7.ADVERT_URL AS ADVERTURL7,  "+
			       "AV7.NAME       AS ADVERTNAME7,  "+
			       "M7.MATER_PATH  AS MATERPATH7,  "+
			       
			       "AV8.ID         AS ADVERTID8,  "+
			       "AV8.MATER_ID   AS MATERID8,  "+
			       "AV8.ADVERT_URL AS ADVERTURL8,  "+
			       "AV8.NAME       AS ADVERTNAME8,  "+
			       "M8.MATER_PATH  AS MATERPATH8,  "+
			       
			       "AV9.ID         AS ADVERTID9,  "+
			       "AV9.MATER_ID   AS MATERID9,  "+
			       "AV9.ADVERT_URL AS ADVERTURL9,  "+
			       "AV9.NAME       AS ADVERTNAME9,  "+
			       "M9.MATER_PATH  AS MATERPATH9,  "+
			       "S.AUTHORDER "+
			  "FROM SCHEDULE S "+
			  "LEFT JOIN TIMESETTING T "+
			    "ON (T.ID = S.INTERVAL) "+
			  "LEFT JOIN DEVICE V "+
			    "ON (S.APID = V.ID) "+
			  "LEFT JOIN PUTPRODUCT P "+
			    "ON (S.PRODUCTID = P.ID) "+
			  "LEFT JOIN ADVERTMODELMANGER A "+
			    "ON (A.ID = P.MODELID) "+
			  "LEFT JOIN SKIN_MANGER SK "+
			    "ON (SK.ID = A.MODELSKIN) "+
			  "LEFT JOIN VEHICLE VV "+
			    "ON (VV.ID = V.VEHICLEID) "+
			  "LEFT JOIN ADVGROUP AG "+
			    "ON (P.ADVGROUP"+i+" = AG.ID) "+
			  "LEFT JOIN PLAY_FUN PF "+
			    "ON (PF.CID = AG.PLAYSTRAGER) "+
			 "LEFT JOIN ADVERTMANGER AV1 "+
			    "ON (AG.ADVID1 = AV1.ID) "+
			 "LEFT JOIN ADVERTMANGER AV2 "+
			    "ON (AG.ADVID2 = AV2.ID) "+
			 "LEFT JOIN ADVERTMANGER AV3 "+
			    "ON (AG.ADVID3 = AV3.ID) "+
			 "LEFT JOIN ADVERTMANGER AV4 "+
			   "ON (AG.ADVID4 = AV4.ID) "+
			 "LEFT JOIN ADVERTMANGER AV5 "+
			    "ON (AG.ADVID5 = AV5.ID) "+
			 "LEFT JOIN ADVERTMANGER AV6 "+
			    "ON (AG.ADVID6 = AV6.ID) "+
			 "LEFT JOIN ADVERTMANGER AV7 "+
			    "ON (AG.ADVID7 = AV7.ID) "+
			 "LEFT JOIN ADVERTMANGER AV8 "+
			    "ON (AG.ADVID8 = AV8.ID) "+
			 "LEFT JOIN ADVERTMANGER AV9 "+
			    "ON (AG.ADVID9 = AV9.ID) "+
			  "LEFT JOIN MATERIEL_INFO M1 "+
			    "ON (AV1.MATER_ID = M1.MATER_ID) "+
			  "LEFT JOIN MATERIEL_INFO M2 "+
			    "ON (AV2.MATER_ID = M2.MATER_ID) "+
			  "LEFT JOIN MATERIEL_INFO M3 "+
			    "ON (AV3.MATER_ID = M3.MATER_ID) "+
			  "LEFT JOIN MATERIEL_INFO M4 "+
			    "ON (AV4.MATER_ID = M4.MATER_ID) "+
			  "LEFT JOIN MATERIEL_INFO M5 "+
			    "ON (AV5.MATER_ID = M5.MATER_ID) "+
			  "LEFT JOIN MATERIEL_INFO M6 "+
			    "ON (AV6.MATER_ID = M6.MATER_ID) "+
			  "LEFT JOIN MATERIEL_INFO M7 "+
			    "ON (AV7.MATER_ID = M7.MATER_ID) "+
			  "LEFT JOIN MATERIEL_INFO M8 "+
			    "ON (AV8.MATER_ID = M8.MATER_ID) "+
			  "LEFT JOIN MATERIEL_INFO M9 "+
			   "ON (AV9.MATER_ID = M9.MATER_ID) "+

			 "WHERE V.APMAC =? "+
			   "AND TO_CHAR(TO_CHAR(SYSDATE, 'yyyy-mm-dd')) = S.DATETIME "+
			   "AND TO_CHAR(TO_CHAR(SYSDATE, 'hh24:mi:ss')) > T.STARTTIME "+
			   "AND TO_CHAR(TO_CHAR(SYSDATE, 'hh24:mi:ss')) < T.ENDTIME ";
//			   "AND S.AUTHORDER IN ('2', '3') "+
			   
			sql+="AND s.authorder in (";
			for(int m=0;m<cycid.length;m++){
				sql+="?,";
				param.add(cycid[m]);
			}
			sql=sql.substring(0, sql.length()-1);
			sql+=") AND S.STATE = 1 "+
			   "AND P.ADVGROUP"+i+" IS NOT NULL union all ";
			   
//				for(int j=1;j<=9;j++){
//					sql+="SELECT S.DATETIME,T.STARTTIME, "+
//				      "T.ENDTIME,A.ID, V.VEHICLEID, "+
//				       "VV.ROUTEID,A.URL,SK.SKINNAME, "+
//				       "A.ADVPOSID"+i+" AS ADVPOSID, "+
//				       "P.ADVGROUP"+i+" AS ADVGROUPID, "+
//				       "AG.PLAYSTRAGER,PF.INTERVAL, "+
//				       "AG.PLAYTIMESSERAL,AV.ID as advertid,AV.MATER_ID, "+
//				       "AV.ADVERT_URL,AV.NAME, "+
//				       "M.MATER_PATH,s.authorder "+
//					  "FROM SCHEDULE S "+
//					  "LEFT JOIN TIMESETTING T "+
//					    "ON (T.ID = S.INTERVAL) "+
//					  "LEFT JOIN DEVICE V "+
//					    "ON (S.APID = V.ID) "+
//					  "LEFT JOIN PUTPRODUCT P "+
//					    "ON (S.PRODUCTID = P.ID) "+
//					  "LEFT JOIN ADVERTMODELMANGER A "+
//					    "ON (A.ID = P.MODELID) "+
//					  "LEFT JOIN SKIN_MANGER SK "+
//					    "ON (SK.ID = A.MODELSKIN) "+
//					  "LEFT JOIN VEHICLE VV "+
//					    "ON (VV.ID = V.VEHICLEID) "+
//					  "LEFT JOIN ADVGROUP AG "+
//					    "ON (P.ADVGROUP"+i+" = AG.ID) "+
//					    "LEFT JOIN PLAY_FUN PF "+
//					  "ON (PF.CID=AG.PLAYSTRAGER) "+
//					  "RIGHT JOIN ADVERTMANGER AV "+
//					    "ON (AG.ADVID"+j+" = AV.ID) "+
//					  "LEFT JOIN MATERIEL_INFO M "+
//					    "ON (AV.MATER_ID = M.MATER_ID) "+
//					    "WHERE v.apmac=?  "+
//						"AND to_char(TO_CHAR(SYSDATE, 'yyyy-mm-dd'))=s.datetime "+ 
//						"AND to_char(TO_CHAR(SYSDATE, 'hh24:mi:ss'))>t.starttime  "+
//						"AND to_char(TO_CHAR(SYSDATE, 'hh24:mi:ss'))<t.endtime ";
//						param.add(mac);
//						sql+="AND s.authorder in (";
//						for(int m=0;m<cycid.length;m++){
//							sql+="?,";
//							param.add(cycid[m]);
//						}
//						sql=sql.substring(0, sql.length()-1);
//						sql+=") AND s.state=1  UNION ALL ";
//					
//			}
		}
		sql=sql.substring(0, sql.length()-11);
	String[] str = new String[]{"date","stime","etime","modelid","busid","routeid","url","skinname",
			"advposid","advgroupid","playstrager","playintervalseral","playtimesseral",
			"advertid1","materid1","adverturl1","advertname1","materpath1",
			"advertid2","materid2","adverturl2","advertname2","materpath2",
			"advertid3","materid3","adverturl3","advertname3","materpath3",
			"advertid4","materid4","adverturl4","advertname4","materpath4",
			"advertid5","materid5","adverturl5","advertname5","materpath5",
			"advertid6","materid6","adverturl6","advertname6","materpath6",
			"advertid7","materid7","adverturl7","advertname7","materpath7",
			"advertid8","materid8","adverturl8","advertname8","materpath8",
			"advertid9","materid9","adverturl9","advertname9","materpath9",
			"authorder"};
		return getNoPageObject(sql, param, str/*,"查询所有的符合条件的广告产品"*/);
	}

	@Override
	public int updateAdvgrouptimes(String advgroupid, int times) {
		// TODO Auto-generated method stub
		List param = new ArrayList();
		param.add(times);
		param.add(advgroupid);
		String sql="update ADVGROUP set PLAYTIMESSERAL=? where id=?";
		return executeUpdateSQL(sql, param/*,"更新新增次数"*/);
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
//			  "left JOINDEVICETRACK Tr "+
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
	public List<JSONObject> getPriadvByCondit(List advids,Happen happen,String apmac) {
		// TODO Auto-generated method stub
		String time = happen.getNowTime();
		String phone = happen.getPhone();
		List param = new ArrayList();
		String sql="";
		log.info("  apmac:"+apmac+"    advids:"+advids.size()+"    time:"+time+"   phone:"+phone+"");
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
		sql=sql.substring(0, sql.length()-10);
		String[] str = new String[]{"timeid","timename","phoneid","phonename","eventid","eventname","adrid","adrname","long1","lat1"
									,"long2","lat2","long3","lat3"
									,"long4","lat4","long5","lat5"
									,"long6","lat6","long7","lat7"
									,"long8","lat8","long9","lat9"
									,"advid"};
		return getNoPageObject(sql, param, str,apmac+"+++查询符合优先投放的广告及其条件");
	}
}
