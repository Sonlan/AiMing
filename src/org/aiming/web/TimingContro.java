package org.aiming.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.aiming.utils.JsonUtil;
import org.aiming.utils.TimerUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/timing")
public class TimingContro {
	/**
	 * 开启定时器
	 * @param response
	 */
	@RequestMapping(value = "/start") 
	public  void   start(HttpServletResponse response) throws IOException{
		try {
			TimerUtil.start();
			if(null != TimerUtil.timer){
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
			TimerUtil.stop();
			if(null != TimerUtil.timer){
				response.getWriter().write(JsonUtil.statusResponse(0, "关闭失败", ""));
			}else
				response.getWriter().write(JsonUtil.statusResponse(0, "关闭成功", ""));
		} catch (Exception e) {
			response.getWriter().write(JsonUtil.statusResponse(0, "关闭失败,后台错误", ""));
		}
		
	}
}
