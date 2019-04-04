package com.hik.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import com.hik.app.entity.TIMESETTING;
import com.hik.dao.BaseHIKDao;


@Repository
public class ITimeSetDaoImpl extends BaseHIKDao implements ITimeSetDao{
	private final Log log = LogFactory.getLog(ITimeSetDaoImpl.class);

	@Override
	public List getTimeSet() {
		// TODO Auto-generated method stub
		String sql="select id,name,starttime,endtime,descr from timesetting";
		String[] str = new String[]{"id","name","starttime","endtime","descr"};
		return getNoPageObject(sql, str,"查询时间设置");
	}

	@Override
	public int editTimeSet(TIMESETTING user) {
		// TODO Auto-generated method stub
		List param = new ArrayList();
		param.add(user.getNAME());
		param.add(user.getSTARTTIME());
		param.add(user.getENDTIME());
		param.add(user.getDESCR());
		param.add(user.getID());
		String sql="update timesetting set name=?,starttime=?,endtime=?,descr=? where id=?";
		return executeUpdateSQL(sql, param, "更新时间设置");
	}

	@Override
	public int addTimeSet(TIMESETTING user) {
		// TODO Auto-generated method stub
		List param = new ArrayList();
		param.add(user.getNAME());
		param.add(user.getSTARTTIME());
		param.add(user.getENDTIME());
		param.add(user.getDESCR());
		String sql="insert into timesetting(id,name,starttime,endtime,descr) values(TIMESETTING_SEQ.nextval,?,?,?,?)";
		return executeUpdateSQL(sql, param, "新增时间设置");
	}

	@Override
	public int deleteTimeSet(String[] id) {
		// TODO Auto-generated method stub
		List param = Arrays.asList(id);
		String sql="delete from timesetting where id in (";
		if(!param.isEmpty()&&param.size()>0){
			for(int i=0;i<param.size();i++){
				sql+="?,";
			}
			sql=sql.substring(0, sql.length()-1);
			sql+=")";
			return executeUpdateSQL(sql, param, "删除时间设置");
		}else{
			return 0;			
		}
	}

	@Override
	public int udpateSMS(String smscontent) {
		// TODO Auto-generated method stub
		List param = new ArrayList();
		param.add(smscontent);
		param.add(smscontent);
		String sql="MERGE INTO sms_content sm "+
				"USING (select 1 AS ID FROM dual) np "+
				"ON (sm.id=np.id AND sm.id=1) "+
				"WHEN MATCHED THEN "+
				  "UPDATE SET sm.sms_content=? WHERE sm.id=1 "+
				 "WHEN NOT MATCHED THEN "+
				   "INSERT VALUES(1,?) ";
		return executeUpdateSQL(sql, param,"新增或者更新新的SMS");
	}

	@Override
	public List querySMS() {
		// TODO Auto-generated method stub
		String sql="select sms_content from sms_content where id=1";
		return getNoPageObject(sql);
	}

	@Override
	public List querySKIN() {
		// TODO Auto-generated method stub
		String sql="select id,skinname,name,descr,createtime from SKIN_MANGER";
		String[] str = new String[]{"id","skinname","name","descr","createtime"};
		return getNoPageObject(sql,str);
	}

	@Override
	public int insertSKIN(String skinname,String name,String descr) {
		// TODO Auto-generated method stub
		List param= new ArrayList();
		param.add(skinname);
		param.add(name);
		param.add(descr);
		String sql="insert into SKIN_MANGER(id,skinname,name,descr,createtime) values(SKIN_MANGER_SEQ.nextval,?,?,?,to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'))";
		return executeUpdateSQL(sql, param, "新增皮肤");
	}
	

	@Override
	public int updateSKIN(String id, String skinname, String name, String descr) {
		// TODO Auto-generated method stub
		List param= new ArrayList();
		param.add(skinname);
		param.add(name);
		param.add(descr);
		param.add(id);
		String sql="update SKIN_MANGER set skinname=?,name=?,descr=? where id=?";//       (id,skinname,name,descr,createtime) values(SKIN_MANGER_SEQ.nextval,?,?,?,to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'))";
		return executeUpdateSQL(sql, param, "更新皮肤");
	}

	@Override
	public int delelte(String[] id) {
		// TODO Auto-generated method stub
		List param=Arrays.asList(id);
		if(id.length>0){
			String sql="delete from SKIN_MANGER where id in (";
			for(int i=0;i<id.length;i++){
				sql+="?,";
			}
			sql=sql.substring(0, sql.length()-1);
			sql+=")";
			return executeUpdateSQL(sql, param, "删除皮肤操作");			
		}else{
			return 0;
		}

	}

	@Override
	public List getSKINById(String id) {
		// TODO Auto-generated method stub
		List param= new ArrayList();
		param.add(id);
		String sql="select skinname,descr,createtime,name from SKIN_MANGER where id=?";
		String[] str = new String[]{"skinname","descr","createtime","name"};
		return getNoPageObject(sql,param,str);
	}

	@Override
	public List queryModelPro() {
		// TODO Auto-generated method stub
		String sql="select id,name,cid,creator,creattime,modifier,modifytime,descr from AUTHCYC";
		String[] str = new String[]{"id","name","cid","creator","creatime","modifier","modifytime","descr"};
		return getNoPageObject(sql, str);
	}

	@Override
	public int deleteModelPro(String[] id) {
		// TODO Auto-generated method stub
//		List param= new ArrayList();
//		param.add(id);
//		String sql="delete from AUTHCYC where id=?";
//		return executeUpdateSQL(sql, param);	
		List param=Arrays.asList(id);
		if(id.length>0){
			String sql="delete from AUTHCYC where id in (";
			for(int i=0;i<id.length;i++){
				sql+="?,";
			}
			sql=sql.substring(0, sql.length()-1);
			sql+=")";
			return executeUpdateSQL(sql, param, "删除模版属性操作");			
		}else{
			return 0;
		}
	}

	@Override
	public List queryAdvPro(String pid,String leve) {
		// TODO Auto-generated method stub
		List<Object> param = new ArrayList<Object>();
		param.add(pid);
		param.add(leve);
		String sql="select id,name,pid,creator,creatime,modifier,modifytime,descr,leve from ADVPROPERTY where pid=? and leve=?";
		String[] str= new String[]{"id","name","pid","creator","creatime","modifier","modifytime","descr","leve"};
		return getNoPageObject(sql, param,str);
	}

	@Override
	public int deleteAdvpro(String[] id) {
		// TODO Auto-generated method stub
		List param=Arrays.asList(id);
		if(id.length>0){
			String sql="delete from ADVPROPERTY where id in (";
			for(int i=0;i<id.length;i++){
				sql+="?,";
			}
			sql=sql.substring(0, sql.length()-1);
			sql+=")";
			return executeUpdateSQL(sql, param, "删除广告属性操作");			
		}else{
			return 0;
		}
//		List param= new ArrayList();
//		param.add(id);
//		String sql="delete from ADVPROPERTY where id=?";
//		return executeUpdateSQL(sql, param);
	}

	@Override
	public int saveModelPro(String cid, String name, String descr, String userid) {
		// TODO Auto-generated method stub
		List param = new ArrayList();
		param.add(cid);
		param.add(name);
		param.add(descr);
		param.add(userid);
		String sql="insert into AUTHCYC(id,cid,name,descr,creator,creattime) values(AUTHCYC_SEQ.nextval,?,?,?,?,to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'))";
		return executeUpdateSQL(sql, param);
	}

	@Override
	public int saveadvPro(String pid, String name,String userid,String leve) {
		// TODO Auto-generated method stub
		List param = new ArrayList();
		param.add(pid);
		param.add(name);
		param.add(userid);
		param.add(leve);
		String sql="insert into ADVPROPERTY(id,pid,name,descr,creator,creatime,leve) values(advproperty_seq.nextval,?,?,'暂无',?,to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'),?)";
		return executeUpdateSQL(sql, param,"新增广告模版模型");
	}

	@Override
	public int updateadvPro(String id,String name, String userid) {
		// TODO Auto-generated method stub
		List param = new ArrayList();
		param.add(name);
		param.add(userid);
		param.add(id);
		String sql="update ADVPROPERTY set name=?,creator=?,creatime=to_char(sysdate,'yyyy-MM-dd hh24:mi:ss') where id=?";
		return executeUpdateSQL(sql, param,"更新广告模版模型");
	}
	@Override
	public int updateModelPro(String id, String cid, String name, String descr, String userid) {
		// TODO Auto-generated method stub
		List param = new ArrayList();
		param.add(cid);
		param.add(name);
		param.add(descr);
		param.add(userid);
		param.add(id);
		String sql="update AUTHCYC set cid=?,name=?,descr=?,creator=? where id=?";
		return executeUpdateSQL(sql, param);
	}

	@Override
	public int savePutStragerPro(String cid, String name, String interval, String descr, String userid) {
		// TODO Auto-generated method stub
		List param = new ArrayList();
		param.add(cid);
		param.add(name);
		param.add(interval);
		param.add(descr);
		param.add(userid);
		String sql="insert into PLAY_FUN(ID,CID,NAME,INTERVAL,DESCR,CREATOR,CREATIME) VALUES(PLAY_FUN_SEQ.NEXTVAL,?,?,?,?,?,to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'))";
		return executeUpdateSQL(sql, param);
	}

	@Override
	public int updatePutStragerPro(String id, String cid, String name, String interval, String descr, String userid) {
		// TODO Auto-generated method stub
		List param = new ArrayList();
		param.add(cid);
		param.add(name);
		param.add(interval);
		param.add(descr);
		param.add(userid);
		param.add(id);
		String sql="update PLAY_FUN set CID=?,NAME=?,INTERVAL=?,DESCR=?,CREATOR=? WHERE ID=?";
		return executeUpdateSQL(sql,param);
	}

	@Override
	public int deletePutStragerPro(String id){
		// TODO Auto-generated method stub
		List param = new ArrayList();
		param.add(id);
		String sql="delete from PLAY_FUN where id=?";
		return executeUpdateSQL(sql, param);
	}

	@Override
	public List queryPutStragerPro(){
		// TODO Auto-generated method stub
		String sql="select id,cid,name,interval,creator,creatime,descr from play_fun";
		String[] str = new String[]{"id","cid","name","interval","creator","creatime","descr"};
		return getNoPageObject(sql, str);
	}
}
