package com.tommy.dao;

import java.util.Map;

public interface UserDao {

    // 这个dao接口负责了用户和超管的登陆基本查询，存在返回1，反之返回2，具体的判断逻辑在Controller中
    int queryUser(Map<String,Object> map);

//    查询该用户名是否存在，要保证用户名的唯一性
    int queryUserbyUsername(String username);

//     新增用户 其中的必填数据为username和password
    void addUser(Map<String, Object> map);

//    更新用户，不是改密码，这里指的是更新普通信息，按照用户名进行修改
    void updateUser(Map<String, Object> map);

//    修改密码的实现：
    void changePassword(Map<String, Object> map);


}
