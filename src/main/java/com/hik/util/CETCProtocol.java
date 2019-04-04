package com.hik.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class CETCProtocol {
	public static class HostPacketTypes { 
		public static final int HOST_PACKET_TYPE_REGISTER_REP_COMMAND = ((short)0x0001) & 0x0FFFF;
		public static final int HOST_PACKET_TYPE_ALIVE_REP_COMMAND = ((short)0x0003) & 0x0FFFF;
		public static final int SJ_PACKET_TYPE_MOBILE_DEV_GPS_COMMAND=((short)0x0002)& 0x0FFFF;
		public static final int HOST_PACKET_TYPE_ALIVE_REQ_COMMAND = ((short)0x0003) & 0x0FFFF;
		public static final int HOST_PACKET_TYPE_MAC_DATA_COMMAND = ((short)0x1001) & 0x0FFFF;
		public static final int HOST_PACKET_TYPE_ID_DATA_COMMAND = ((short)0x1002) & 0x0FFFF;
		public static final int HOST_PACKET_TYPE_AP_DATA_COMMAND = ((short)0x1003) & 0x0FFFF;
		public static final int HOST_PACKET_TYPE_GPS_DATA_COMMAND = ((short)0x1005) & 0x0FFFF;
		public static final int HOST_PACKET_TYPE_GPS_INTERVAL_COMMAND=((short)0x1001) & 0x0FFFF;
	 }
	
	public static class ClientPacketTypes { 
		public static final int CLINET_PACKET_TYPE_REGISTER_COMMAND = ((short)0x0001) & 0x0FFFF;
		public static final int CLINET_PACKET_TYPE_ALIVE_COMMAND = ((short)0x0002) & 0x0FFFF;
	}
	
	public static String Escape(String src) {
		if (!(src == null || src == "")) {
            return src.replace("%09", "\t").replace("%0A", "\n").replace("%0D", "\r").replace("%7C", "|");
        }
        else {
            return src;
        }
	}
	
	public static Integer IntParse(String src) {
		try {
			if (!(src == null || src == "")) {
				return Integer.parseInt(src);
			}
			else {
				return null;
			}
		}catch (Exception e) {
			return null;
		} 
	}
	
	public static Date GetBaseDate(Integer seconds) {
		if (seconds == null) {
			return null;
		}
		
		try {
			GregorianCalendar gc =new GregorianCalendar();
			gc.setTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("1970-01-01 08:00:00"));
			gc.add(Calendar.SECOND, seconds);
			return gc.getTime();
		}catch (Exception e) {
			return null;
		} 
	}
	
	/**
	 * 解析接收到的数据包
	 * @param buf 数据载荷
	 * @return 解包后的数据
	 */
	public static final byte[] DismantlingPackage(byte[] buf) {
		if (buf == null || (buf[0] != 0x5A && buf[1] != 0x55)) {
            return null;
        }
		
		// 进行CRC校验，由于使用通讯框架，所以校验位位于数据帧最末尾
        byte checksum = 0;
        for (int i = 0; i < buf.length - 1; i++) {
            checksum += buf[i];
        }

        if (checksum != buf[buf.length - 1]) {
            return null;
        }
        
        return Del0x99(buf);
	}
	
	/**
	 * 组建要发送数据包
	 * @param buf 数据载荷
	 * @param packetType 数据包类型
	 * @return 组包后的数据
	 */
    public static final byte[] MakePacket(byte[] buf, int packetType) {
        int bufLength = 0;
        if (buf != null) {
            bufLength = buf.length;
        }
        //加入帧头长度2+端口长2+
        byte[] newPack = new byte[bufLength + 10];
        newPack[0] = 0x5A;
        newPack[1] = 0x55;
        ByteUtil.putReverseBytesShort(newPack, (short)(bufLength + 6), 2);
	    newPack[4] = 0x0A;
	    ByteUtil.putReverseBytesShort(newPack, (short)packetType, 5);
        newPack[newPack.length - 2] = 0x6A;
        newPack[newPack.length - 1] = 0x69;

        if (buf != null) {
        	System.arraycopy(buf, 0, newPack, 7, buf.length);
        }
        
        newPack = Add0x99(newPack);

        byte CRC32 = 0;
        for (int i = 0; i < newPack.length - 3; i++) {
                CRC32 += newPack[i];
        }
        
        newPack[newPack.length - 3] = CRC32;
        return newPack;
    }
	
	/**
	 * 转换特定字符信息
	 * @param buf 待转换的字节数组
	 * @return 转换后的字节数组
	 */
    private static final byte[] Add0x99(byte[] buf) {
    	byte[] byTmp = new byte[buf.length * 2];
        int count = 0;
        int lastIndex = 0;// 标志上一次出现0x99的位置，也就是已经拷贝数据的位置
        int CRCindex = buf.length - 3; // 校验和的下标(下标从0开始)
        
        for (int i = 2; i < CRCindex; i++) {
            if ((buf[i]& 0x00FF) == 0x5A || (buf[i]& 0x00FF) == 0x6A || (buf[i]& 0x00FF) == 0x99) {
            	// 拷贝之前的数据
            	System.arraycopy(buf, lastIndex, byTmp, lastIndex + count, i - lastIndex);
                lastIndex = i + 1;
                byTmp[i + count] = (byte)0x99;
                byTmp[i + count + 1] = (byte)(0xFF - buf[i]);// 反码
                count++;
            }
        }

        // 拷贝最后的数据
        System.arraycopy(buf, lastIndex, byTmp, lastIndex + count, buf.length - 1 - lastIndex + 1);
        int newLength = count + buf.length;
        byte[] newBuf = new byte[newLength];
        System.arraycopy(byTmp, 0, newBuf, 0, newLength);

        return newBuf;
    }

    /**
	 * 还原特定字符信息
	 * @param buf 待还原的字节数组
	 * @return 还原后的字节数组
	 */
    private static final byte[] Del0x99(byte[] buf) {
        byte[] byTmp = new byte[buf.length];
        int count = 0;
        int lastIndex = 0;///标志上一次出现0x99的位置，也就是已经拷贝数据的位置
        int CRCindex = buf.length - 1;  ///校验和的下标(下标从0开始)
        
        // 从长度下标开始，进行DEL0x99处理
        for (int i = 2; i < CRCindex; i++) {
            if ((buf[i] & 0x00FF) == 0x99) {
                if (((buf[i + 1] & 0x00FF) != 0xFF - 0x99) &&
                     ((buf[i + 1] & 0x00FF) != 0xFF - 0x5A) &&
                     ((buf[i + 1] & 0x00FF) != 0xFF - 0x6A) &&
                     ((buf[i + 1] & 0x00FF) != 0x6A)) {
                    return null;
                }
                // 拷贝之前的数据
                System.arraycopy(buf, lastIndex, byTmp, lastIndex - count, i - lastIndex);
                lastIndex = i + 2;
                byTmp[i - count] = (byte)(0xFF - (buf[i + 1] & 0x00FF));
                count++;
            }
        }

        // 拷贝最后的数据
        System.arraycopy(buf, lastIndex, byTmp, lastIndex - count, buf.length - 1 - lastIndex + 1);
        int newLength = buf.length - count;
        byte[] newBuf = new byte[newLength];
        System.arraycopy(byTmp, 0, newBuf, 0, newLength);
        
        if (newLength - 2 != ByteUtil.getReverseBytesShort(newBuf, 2)) {
            return null;
        }

        return newBuf;
    }
}
