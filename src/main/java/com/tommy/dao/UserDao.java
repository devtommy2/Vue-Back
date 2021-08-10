package com.tommy.dao;

import com.tommy.domain.Major;
import com.tommy.domain.Teacher;
import com.tommy.domain.University;
import com.tommy.domain.University_cache;

import java.util.List;
import java.util.Map;

public interface UserDao {

    // 这个dao接口负责了用户和超管的登陆基本查询，存在返回1，反之返回2，具体的判断逻辑在Controller中
    int queryUser(Map<String,Object> map);

//    查询该用户名是否存在，要保证用户名的唯一性
    int queryUserbyUsername(String username);

//     新增用户数据到t_student_information 其中的必填数据为username和password
    int addUserToTable(Map<String, Object> map);    // 在service层进行操作

//    更新用户，不是改密码，这里指的是更新普通信息，按照用户名进行修改
    void updateUser(Map<String, Object> map);

//    修改密码的实现：
    void changePassword(Map<String, Object> map);

//    通过用户名获取用户身份
    String getIdentity(String username);

//    获取所有的学校
    List<University> showUniversity();
    List<University_cache> showUniversity_cache();

    void addUser(Map<String, Object> map);

    List<Major> showMajor();

    List<Teacher> showTeacher();

    void insertTeacherId(Map<String, Object> map);

    // 接口一：用来接受处理的城市
    List<University_cache> selectUniversityByCity(String city);

    void insertListToCache(List<University> universityList);

//    int clearCache();
//
//    List<University> selectCache();
//
//    List<University> selectCache_finally(Integer student_id);
//
//    List<University> selectCacheByLevelBaseOnCity(String level);

//    void insertListToCache_test(University_cache university_cache);
//
//    void deleteCacheBaseOnJYB(Integer student_id);
//
//    void deleteCacheNameJYB(Integer student_id);
//
//    void deleteCacheNot985(Integer student_id);
//
//    void deleteCacheNot211(Integer student_id);
//
//    void deleteCacheNotBK(Integer student_id);
//
//    void deleteCacheNotZK(Integer student_id);

    Integer selectStudentIdByUsername(String username);

//    void deleteCacheNotFirstRateUniversity(Integer student_id);
//
//    void deleteCacheNotFirstClassDisciplineUniversity(Integer student_id);
//
//    void deleteCacheNotPostgraduate();
//
//    void deleteCacheHavePostgraduate();

    List<University_cache> selectInCities(String cities);
}
