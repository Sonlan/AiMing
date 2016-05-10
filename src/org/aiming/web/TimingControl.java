package org.aiming.web;

import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletResponse;

import org.aiming.entity.Label;
import org.aiming.service.AirconditionService;
import org.aiming.service.LabelService;
import org.aiming.utils.JsonUtil;
import org.aiming.utils.TimeRevert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/timing")
public class TimingControl {
	
	private static Timer timer = null;
	private static long rate = 5*60*1000;
	private static long [] lastWorkTime = {0,0,0};
	private static int [] ac_id = {0,0,0}; 
	
	@Autowired
	private AirconditionService airService;
	@Autowired
	private LabelService labelService;
	/**
	 * 开启定时器
	 * @param response
	 */
	@RequestMapping(value = "/start") 
	public  void   start(HttpServletResponse response) throws IOException{
		try {
			this.start();
			if(null != timer){
				response.getWriter().write(JsonUtil.statusResponse(0, "开启成功", ""));
			}else
				response.getWriter().write(JsonUtil.statusResponse(0, "开启失败", ""));
		} catch (Exception e) {
			response.getWriter().write(JsonUtil.statusResponse(0, "开启失败,后台错误", ""));
		}
		
			
	}
	/**
	 * 关闭定时器
	 */
	@RequestMapping(value = "/stop") 
	public void  stop(HttpServletResponse response) throws IOException{
		try {
			this.stop();
			if(null != timer){
				response.getWriter().write(JsonUtil.statusResponse(0, "关闭失败", ""));
			}else
				response.getWriter().write(JsonUtil.statusResponse(0, "关闭成功", ""));
		} catch (Exception e) {
			response.getWriter().write(JsonUtil.statusResponse(0, "关闭失败,后台错误", ""));
		}
		
	}
	@PostConstruct
	public void start(){
		if(null != timer){
			stop();
		}
		try {
			Properties prop=new Properties();
			prop.load(new InputStreamReader(TimingControl.class.getClassLoader().getResourceAsStream("workConig.properties"), "UTF-8"));
			rate = Long.parseLong(prop.getProperty("rate"))*60*1000;
			ac_id[0] = Integer.parseInt(prop.getProperty("ac_id1"));
			ac_id[1] = Integer.parseInt(prop.getProperty("ac_id2"));
			ac_id[2] = Integer.parseInt(prop.getProperty("ac_id3"));
			rate = 5000;
		} catch (Exception e) {
			e.printStackTrace();
			rate = 5*60*1000;
		}
		timer = new Timer(true);
		timer.scheduleAtFixedRate(new TimerTask() {
				
			@Override
			public void run() {
				for(int i=0;i<3;i++){
					Boolean isWork = airService.isWork(i,new BigDecimal(0));
					if(isWork && 0==lastWorkTime[i]){
						lastWorkTime[i] = new Date().getTime();
					}else if(!isWork && 0!=lastWorkTime[i]){
						long sub = new Date().getTime()-lastWorkTime[i];
						List<Label> list = labelService.getLabelByAcId(ac_id[i]);
						for(int j=0;j<list.size();j++){
							String time = list.get(j).getCumulative_time();
							long t = TimeRevert.toLong(list.get(j).getCumulative_time());
							long t1 = TimeRevert.toLong(list.get(j).getCumulative_time())+sub;
							System.out.println("time"+time+" "+t+" "+TimeRevert.toString(t));
							labelService.updateTimeofLabel(list.get(j).getId(), TimeRevert.toString(t1));
						}
						lastWorkTime[i] = 0;
					}
				}
			}
		}, 0, rate);
	}
	public void stop(){
		if(null != timer){
			timer.cancel();
			timer = null;
		}
	}
}
