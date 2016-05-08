package org.aiming.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.events.StartDocument;

import org.aiming.service.UserService;
import org.aiming.utils.JsonUtil;
import org.aiming.utils.TimerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 用户相关后台处理
 * @author Songsong
 *
 */
@Controller
@RequestMapping(value = "/user")
public class UserControl {
	@Autowired
	private UserService userService;
	/**
	 * 用户登录处理
	 * @throws IOException 
	 */
	@RequestMapping(value = "/logon") 
	public String logon(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		TimerUtil.start();
		if (!(null==username && "".equals(username) && null==password && "".equals(password))) {
			if(userService.logon(username, password)){
				return "main";
			}
		}
		response.getWriter().write(JsonUtil.statusResponse(1, "登录失败", "")); 
		return "logon";
	}
	@RequestMapping(value = "/start") 
	public  String   start(HttpServletResponse response) throws IOException{
		TimerUtil.start();
		return "main";
	}
	@RequestMapping(value = "/stop") 
	public String  stop(){
		TimerUtil.stop();
		return "main";
	}
}
