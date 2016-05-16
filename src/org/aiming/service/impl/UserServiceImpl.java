package org.aiming.service.impl;

import java.util.HashMap;
import java.util.List;
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
	public User logon(String username, String password) {
		try {
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("username", username);
			map.put("password", MD5Util.MD5(password));
			return userDao.userExist(map);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
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
	public List<User> query(String username,int page) {
		try {
			Map<Object, Object> map = new HashMap<>();
			map.put("username", username);
			map.put("pageStart", page*9);
			map.put("pageEnd", page*9+9);
			List<User> users = userDao.getUserByName(map);
			return users;
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
	@Override
	public int queryForSize(String username) {
		try {
			Map<Object, Object> map = new HashMap<>();
			map.put("username", username);
			List<User> users = userDao.getUserByName1(map);
			return users.size();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
