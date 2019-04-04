package com.hik.framework.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static final String defaultFormat="yyyy-MM-dd HH:mm:ss";
	/**
	 * 
	 * @return Ĭ�ϻ�ȡ��ǰʱ��
	 */
	public static String getCurrentDate(){
		return getCurrentDate(defaultFormat);
	}
	/**
	 * 
	 * @param format ʱ���ʽ(yyyy-MM-dd HH:mm:ss)
	 * @return ����ʱ���ʽ�����ص�ǰʱ��
	 */
	public static String getCurrentDate(String format){
		SimpleDateFormat df = new SimpleDateFormat(format);//�������ڸ�ʽ
		return df.format(new Date());
	}
	/**
	 * ����һ�������
	 * @param date
	 * @return
	 */
	public static Date getAddaDay(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR,1);
		Date date2 = calendar.getTime();
		return date2;
	}
	
	/**
	 * ����һ���µ�����
	 * @param date
	 * @return
	 */
	public static Date getAddaMonth(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR,30);
		Date date2 = calendar.getTime();
		return date2;
	}
	/**
	 * ����ת�ַ���(�Զ���ʱ���ʽ)
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getDateToStr(Date date,String format){
		SimpleDateFormat df = new SimpleDateFormat(format);//�������ڸ�ʽ
		String result=df.format(date);
		return result;
	}
	/**
	 * ����ת�ַ���(Ĭ�ϸ�ʽ)
	 * @param date
	 * @return
	 */
	public static String getDateToStr(Date date){
		return getDateToStr(date,defaultFormat);
	}
	/**
	 * �ַ���ת����
	 * @param date
	 * @return
	 */
	public static Date getStrToDate(String date){
		SimpleDateFormat df = new SimpleDateFormat(defaultFormat);//�������ڸ�ʽ
		Date result=null;
		try {
			result = df.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * �ַ���ת����
	 * @param date
	 * @return
	 */
	public static Date getStrToDate(String date,String format){
		SimpleDateFormat df = new SimpleDateFormat(format);//�������ڸ�ʽ
		Date result=null;
		try {
			result = df.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * ʱ���תΪʱ��
	 * @param s
	 * @return
	 */
	public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}
