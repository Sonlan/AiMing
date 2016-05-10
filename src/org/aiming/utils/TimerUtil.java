package org.aiming.utils;

import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;


public class TimerUtil {
	public static Timer timer = null;
	private static long rate = 5*60*1000; 
	
	public static void start(){
		if(null != timer){
			stop();
		}
		try {
			Properties prop=new Properties();
			prop.load(new InputStreamReader(TimerUtil.class.getClassLoader().getResourceAsStream("workConig.properties"), "UTF-8"));
			rate = Long.parseLong(prop.getProperty("rate"))*60*1000;
			rate = 5000;
		} catch (Exception e) {
			e.printStackTrace();
			rate = 5*60*1000;
		}
		timer = new Timer(true);
		timer.scheduleAtFixedRate(new TimerTask() {
				
			@Override
			public void run() {
				Date date = new Date();
				System.out.println("timer: "+date.getTime());
			}
		}, 0, rate);
	}
	public static void stop(){
		if(null != timer){
			timer.cancel();
			timer = null;
		}
	}
}
