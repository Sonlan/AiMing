package org.aiming.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;

import org.aiming.entity.User;
import org.aiming.service.UserService;
import org.aiming.utils.JsonUtil;

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
	public  void logon(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (!(null==username && "".equals(username) && null==password && "".equals(password))) {
			if(userService.logon(username, password)){
				request.getSession().setAttribute("_LOGIN", "OK");
				response.setContentType("application/json;charset=utf-8");
				response.getWriter().write(JsonUtil.statusResponse(0, "登录成功", "user/toIndex"));
			}else response.getWriter().write(JsonUtil.statusResponse(1, "用户名或密码错误", ""));
		}else
		response.getWriter().write(JsonUtil.statusResponse(1, "请检查输入", "")); 
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
	public void login(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String levelstr = request.getParameter("level");
		int level = Integer.parseInt(levelstr);
		if (!(null==username && "".equals(username) && null==password && "".equals(password) && null==levelstr && "".equals(levelstr) )) {
			if(userService.userRepeat(username)){
				response.getWriter().write(JsonUtil.statusResponse(1, "注册失败,用户名重复", "")); 
			}
			else if(userService.login(username, password,level)){
				response.getWriter().write(JsonUtil.statusResponse(0, "注册成功", ""));
			}
		}
		else response.getWriter().write(JsonUtil.statusResponse(1, "注册失败,请检查输入", "")); 

	}
	/**
	 * 用户注销
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value = "/delete")
	public void delete(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String username = request.getParameter("username");
		if(null == username) response.getWriter().write(JsonUtil.statusResponse(0, "用户注销失败", ""));
		else{
			
		}
	}
	@RequestMapping(value = "/query") 
	public  void   query(HttpServletResponse response,HttpServletRequest request) throws IOException{
		String username = request.getParameter("username");
		User user = userService.query(username);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(JsonUtil.statusResponse(0, "查询成功", user));
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
	/**
	 * 跳转到主页面
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toIndex") 
	public  String   toMain(HttpServletResponse response) throws IOException{
		return "/main/index";
	}
	
}
