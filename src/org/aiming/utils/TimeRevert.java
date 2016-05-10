package org.aiming.utils;

/**
 * hhh:mm:ss类型与long的相互转化
 * @author Administrator
 *
 */
public class TimeRevert {
	public static Long toLong(String time){
		String [] res = time.split(":");
		try {
			return (long)(Long.parseLong(res[res.length-1])+Long.parseLong(res[res.length-2])*60+Long.parseLong(res[res.length-3])*3600)*1000;
		} catch (Exception e) {
			e.printStackTrace();
			return -1l;
		}
	}
	public static String toString(long time){
		try {
			return time/3600000+":"+(time-time/3600000*3600000)/60000+":"+(time-time/60000*60000)/1000+"";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
