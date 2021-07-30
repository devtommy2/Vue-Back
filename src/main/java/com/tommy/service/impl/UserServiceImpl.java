package com.tommy.service.impl;

import com.tommy.dao.UserDao;
import com.tommy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public int queryUser(Map<String, Object> map) {
        return userDao.queryUser(map);
    }

    @Override
    public int queryUserbyUsername(String username) {
        return userDao.queryUserbyUsername(username);
    }

    @Override
    public void addUser(Map<String, Object> map) {
        userDao.addUser(map);
    }

    @Override
    public void updateUser(Map<String, Object> map) {
        userDao.updateUser(map);
    }
}
