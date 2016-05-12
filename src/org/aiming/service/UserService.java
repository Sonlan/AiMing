package org.aiming.service;

import org.aiming.entity.User;

/**
 * 用户相关服务
 * @author Songsong
 *
 */
public interface UserService {
	/**
	 * 登录判断，失败返回false
	 * @param username
	 * @param password
	 * @return
	 */
	boolean logon(String username,String password);
	/**
	 * 注册用户
	 * @param username
	 * @param password
	 * @param level
	 * @return
	 */
	boolean login(String username,String password,int level);
	/**
	 * 用户名重复返回false
	 * @param username
	 * @param password
	 * @param level
	 * @return
	 */
	boolean userRepeat(String username);
	
	User query(String username);
	
	boolean userDelete(String username);
}
