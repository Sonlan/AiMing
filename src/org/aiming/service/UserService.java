package org.aiming.service;
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
}
