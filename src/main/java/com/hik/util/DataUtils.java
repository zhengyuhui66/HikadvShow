package com.hik.util;

public class DataUtils {
	public static double pi = 3.1415926535897932384626;  
	public static double a = 6378245.0;  
    public static double ee = 0.00669342162296594323;  
	
	public static String getMACStr(String mac){
		char[] byte0=mac.toCharArray();
		String result="";
		for(int i=0;i<byte0.length;i++){
			result+=byte0[i];
			if(i%2==1&&i!=byte0.length-1){
				result+="-";
			}
		}
		return result;
	}
	
	public static Area bd09_To_Gcj02(double bd_lat, double bd_lon) {  
        double x = bd_lon - 0.0065, y = bd_lat - 0.006;  
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * pi);  
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * pi);  
        double gg_lon = z * Math.cos(theta);  
        double gg_lat = z * Math.sin(theta);  
        return new Area(gg_lat,gg_lon);  
    }
	
	public static Area gps84_To_Gcj02(double lat, double lon) {  
        if (outOfChina(lat, lon)) {  
            return null;  
        }  
        double dLat = transformLat(lon - 105.0, lat - 35.0);  
        double dLon = transformLon(lon - 105.0, lat - 35.0);  
        double radLat = lat / 180.0 * pi;  
        double magic = Math.sin(radLat);  
        magic = 1 - ee * magic * magic;  
        double sqrtMagic = Math.sqrt(magic);  
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);  
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);  
        double mgLat = lat + dLat;  
        double mgLon = lon + dLon;  
        return new Area(mgLat,mgLon);  
    }  
	  public static boolean outOfChina(double lat, double lon) {  
	        if (lon < 72.004 || lon > 137.8347)  
	            return true;  
	        if (lat < 0.8293 || lat > 55.8271)  
	            return true;  
	        return false;  
	    }  
	  
	  public static double transformLat(double x, double y) {  
	        double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y  
	                + 0.2 * Math.sqrt(Math.abs(x));  
	        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;  
	        ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;  
	        ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;  
	        return ret;  
	    }  
	  
	    public static double transformLon(double x, double y) {  
	        double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1  
	                * Math.sqrt(Math.abs(x));  
	        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;  
	        ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;  
	        ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0  
	                * pi)) * 2.0 / 3.0;  
	        return ret;  
	    }  
	    
	    public static void main(String[] args) {
	    	 Area area=DataUtils.gps84_To_Gcj02(30.12323,120.10222);
	    	 System.out.println(area.getPx()+"==="+area.getPy());
		}
}
