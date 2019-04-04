package com.hik.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SMSUtils {
	public static String getSMSCode(){
		 Random rd = new Random();
		 int i=rd.nextInt(899999)+100000;
		 return i+"";
	}
	
	public static Map st=new HashMap();
}
