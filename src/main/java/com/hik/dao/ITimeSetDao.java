package com.hik.dao;

import java.util.List;
import com.hik.app.entity.TIMESETTING;
public interface ITimeSetDao{
	/**
	 * @return
	 */
		public List getTimeSet();
		
	/**
	 * 
	 * @param user ��Ҫ�༭������
	 */
		public int editTimeSet(TIMESETTING times);
	/**
	 * 
	 * @param user ��ӵ�����
	 */
		int addTimeSet(TIMESETTING times);
	/**
	 * 
	 * @param usid ɾ������������
	 */
		int deleteTimeSet(String[] id);
		/**
		 * 
		 */
		int udpateSMS(String smscontent);
		/**
		 * ��ѯ��������
		 * @return
		 */
		List querySMS();
		/**
		 * ��ѯƤ������
		 * @return
		 */
		List querySKIN();
		/**
		 * ����Ƥ��
		 * @return
		 */
		int insertSKIN(String skinname,String name,String descr);
		
		int updateSKIN(String id,String skinname,String name, String descr);
		/**
		 * ɾ��Ƥ��
		 * @param id
		 * @return
		 */
		int delelte(String[] id);
		/**
		 * 
		 * @param id
		 * @return
		 */
		List getSKINById(String id);
		/**
		 * ��ѯģ��������Ϣ
		 * @return
		 */
		public List queryModelPro();
		/**
		 * ɾ��ģ������ֵ
		 * @param id
		 * @return
		 */
		public int deleteModelPro(String[] id);
		/**
		 * ��ѯ�������
		 * @return
		 */
		public List queryAdvPro(String pid,String leve);
		/**
		 * ɾ�������������
		 * @param id
		 * @return
		 */
		public int deleteAdvpro(String[] id);
		/**
		 * ����ģ������
		 * @param cid ID
		 * @param name ����
		 * @param descr ����
		 * @param userid ������
		 * @return
		 */
		public int saveModelPro(String cid,String name,String descr,String userid);
		/**
		 * 
		 * @param id ID
		 * @param cid 
		 * @param name
		 * @param descr
		 * @param userid
		 * @return
		 */
		public int updateModelPro(String id,String cid,String name,String descr,String userid);
		/**
		 * �������ģ������
		 * @param id ID
		 * @param pid ����ID
		 * @param name ����
		 * @param descr ����
		 * @param userid ������
		 * @return
		 */
		public int saveadvPro(String pid,String name,String userid,String leve);
		/**
		 * ���¹������
		 * @param id ID 
		 * @param pid ����
		 * @param name ����
		 * @param userid ������
		 * @return
		 */
		public int updateadvPro(String id,String name,String userid);
		
		public int savePutStragerPro(String cid,String name,String interval,String descr,String userid);
		
		public int updatePutStragerPro(String id,String cid,String name,String interval,String descr,String userid);
		
		public int deletePutStragerPro(String id);
		
		public List queryPutStragerPro();
}
