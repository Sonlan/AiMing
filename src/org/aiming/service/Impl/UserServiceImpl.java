package org.aiming.service.Impl;

import java.util.HashMap;
import java.util.Map;

import org.aiming.dao.UserMapper;
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

}
