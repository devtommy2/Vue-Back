package com.tommy.service.impl;

import com.tommy.dao.UserDao;
import com.tommy.domain.University;
import com.tommy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public String getIdentity(String username) {
        return userDao.getIdentity(username);
    }

    @Override
    public void changePassword(Map<String, Object> map) {
        userDao.changePassword(map);
    }

    @Override
    public List<University> showUniversity() {
        List<University> universityList = userDao.showUniversity();
        return universityList;
    }
}
