package org.jymf.service.impl;

import org.jymf.dao.UserMapper;
import org.jymf.entity.User;
import org.jymf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("userSer")
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper mapper;
    public User getUserByID(String id) {
        return mapper.getUserByid(id);
    }


}
