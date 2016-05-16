package org.aiming.service;

import java.util.List;

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
	User logon(String username,String password);
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
	
	List<User> query(String username, int page);
	
	int queryForSize(String username);
	boolean userDelete(String username);
}
