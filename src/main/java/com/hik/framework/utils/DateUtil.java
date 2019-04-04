package com.hik.framework.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static final String defaultFormat="yyyy-MM-dd HH:mm:ss";
	/**
	 * 
	 * @return 默认获取当前时间
	 */
	public static String getCurrentDate(){
		return getCurrentDate(defaultFormat);
	}
	/**
	 * 
	 * @param format 时间格式(yyyy-MM-dd HH:mm:ss)
	 * @return 依据时间格式，返回当前时间
	 */
	public static String getCurrentDate(String format){
		SimpleDateFormat df = new SimpleDateFormat(format);//设置日期格式
		return df.format(new Date());
	}
	/**
	 * 新增一天的数据
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
	 * 新增一个月的数据
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
	 * 日期转字符串(自定义时间格式)
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getDateToStr(Date date,String format){
		SimpleDateFormat df = new SimpleDateFormat(format);//设置日期格式
		String result=df.format(date);
		return result;
	}
	/**
	 * 日期转字符串(默认格式)
	 * @param date
	 * @return
	 */
	public static String getDateToStr(Date date){
		return getDateToStr(date,defaultFormat);
	}
	/**
	 * 字符串转日期
	 * @param date
	 * @return
	 */
	public static Date getStrToDate(String date){
		SimpleDateFormat df = new SimpleDateFormat(defaultFormat);//设置日期格式
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
	 * 字符串转日期
	 * @param date
	 * @return
	 */
	public static Date getStrToDate(String date,String format){
		SimpleDateFormat df = new SimpleDateFormat(format);//设置日期格式
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
	 * 时间戳转为时间
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
