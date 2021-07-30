package com.tommy.service;

import java.util.Map;

public interface UserService {

    int queryUser(Map<String,Object> map);

    int queryUserbyUsername(String username);

    void addUser(Map<String, Object> map);

    void updateUser(Map<String, Object> map);
}
