package com.tommy.service;

import com.tommy.domain.Major;
import com.tommy.domain.Teacher;
import com.tommy.domain.University;
import com.tommy.domain.University_cache;

import java.util.List;
import java.util.Map;

public interface UserService {

    int queryUser(Map<String,Object> map);

    int queryUserbyUsername(String username);


    void updateUser(Map<String, Object> map);

    String getIdentity(String username);

    void changePassword(Map<String, Object> map);

    List<University> showUniversity();
    List<University_cache> showUniversity_cache();

    void addUser(Map<String, Object> map);

    List<Major> showMajor();

    List<Teacher> showTeacher();

    void insertTeacherId(Map<String, Object> map);

    List<University_cache> selectUniversityByCity(String city);

    void insertListToCache(List<University> universityList);

//    int clearCache();
//
//    List<University> selectCache();
//
//    List<University> selectCacheByLevelBaseOnCity(String level);
//
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
//    List<University> selectCache_finally(Integer student_id);
//
//    void deleteCacheNotPostgraduate();
//
//    void deleteCacheHavePostgraduate();

    List<University_cache> selectInCities(String cities);

}
