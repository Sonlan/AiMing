package org.aiming.dao;

import java.util.HashMap;
import java.util.Map;

import org.aiming.entity.User;

/**
 * 用户基本信息
 * @author Administrator
 *
 */
public interface UserMapper {
    User getUserByid(String id);
    
    /**
     * 根据用户名及密码判断是否存在此用户
     * @param map
     * @return
     */
    User userExist(Map<Object, Object> map);
}
