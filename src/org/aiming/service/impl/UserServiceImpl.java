package org.aiming.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.aiming.dao.UserMapper;
import org.aiming.entity.User;
import org.aiming.service.UserService;
import org.aiming.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userDao;
	/**
	 * 登录失败返回false
	 */
	@Override
	public boolean logon(String username, String password) {
		try {
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("username", username);
			map.put("password", MD5Util.MD5(password));
			if (null == userDao.userExist(map)) {
				return false;
			}else return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	/**
	 * 注册失败返回false
	 */
	@Override
	public boolean login(String username, String password, int level) {
		try {
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("username", username);
			map.put("password", MD5Util.MD5(password));
			map.put("level", level);
			if(null == userDao.userRepeat(map)){
				userDao.insert(map);
				return true;
			}else return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 用户名重复返回true
	 */
	@Override
	public boolean userRepeat(String username) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("username", username);
		if(null == userDao.userRepeat(map))
			return false;
		else return true;
	}
	@Override
	public User query(String username) {
		try {
			User user = userDao.getUserByName(username);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	@Override
	public boolean userDelete(String username) {
		try {
			userDao.deleteUserByName(username);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
