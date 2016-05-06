package org.jymf.dao;

import org.jymf.entity.User;

/**
 * 用户基本信息
 * @author Administrator
 *
 */
public interface UserMapper {
    User getUserByid(String id);
}
