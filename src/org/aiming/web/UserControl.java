package org.aiming.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aiming.service.UserService;
import org.aiming.utils.JsonUtil;
import org.aiming.utils.TimerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
//		TimerUtil.start();
		if (!(null==username && "".equals(username) && null==password && "".equals(password))) {
			if(userService.logon(username, password)){
				request.getSession().setAttribute("_LOGIN", "OK");
				response.getWriter().write(JsonUtil.statusResponse(0, "登录成功", ""));
				return "/main/main";
			}
		}
		response.getWriter().write(JsonUtil.statusResponse(1, "登录失败", "")); 
		return "logon";
	}
	/**
	 * 登出
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/logout") 
	public String logout(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.getSession().invalidate();	
		return "logon";
	}
	/**
	 * 用户注册
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/login") 
	public String login(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String levelstr = request.getParameter("level");
		int level = Integer.parseInt(levelstr);
		if (!(null==username && "".equals(username) && null==password && "".equals(password) && null==levelstr && "".equals(levelstr) )) {
			if(userService.userRepeat(username)){
				response.getWriter().write(JsonUtil.statusResponse(1, "注册失败,用户名重复", "")); 
				return "/main/main";
			}
			if(userService.login(username, password,level)){
				response.getWriter().write(JsonUtil.statusResponse(0, "注册成功", ""));
				return "/main/main";
			}
		}
		response.getWriter().write(JsonUtil.statusResponse(1, "注册失败", "")); 
		return "/main/main";
	}
	@RequestMapping(value = "/start") 
	public  String   start(HttpServletResponse response) throws IOException{
//		TimerUtil.start();
		return "main";
	}
	@RequestMapping(value = "/stop") 
	public String  stop(){
//		TimerUtil.stop();
		return "/main/main";
	}
	/**
	 * 返回到登录页面
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toLogon") 
	public  String   toLogon(HttpServletResponse response) throws IOException{
		return "logon";
	}
	
}
