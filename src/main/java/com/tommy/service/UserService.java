package com.tommy.service;

import com.tommy.domain.University;

import java.util.List;
import java.util.Map;

public interface UserService {

    int queryUser(Map<String,Object> map);

    int queryUserbyUsername(String username);

    void addUser(Map<String, Object> map);

    void updateUser(Map<String, Object> map);

    String getIdentity(String username);

    void changePassword(Map<String, Object> map);

    List<University> showUniversity();
}
