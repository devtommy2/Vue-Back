package com.tommy.service.impl;

import com.tommy.dao.UserDao;
import com.tommy.domain.Major;
import com.tommy.domain.Teacher;
import com.tommy.domain.University;
import com.tommy.domain.University_cache;
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
        return userDao.showUniversity();
    }

    @Override
    public List<University_cache> showUniversity_cache() {
        return userDao.showUniversity_cache();
    }

    @Override
    public void addUser(Map<String, Object> map) {
        userDao.addUser(map);
    }

    @Override
    public List<Major> showMajor() {
        return userDao.showMajor();
    }

    @Override
    public List<Teacher> showTeacher() {
        return userDao.showTeacher();
    }

    @Override
    public void insertTeacherId(Map<String, Object> map) {
        userDao.insertTeacherId(map);
    }

    @Override
    public List<University_cache> selectUniversityByCity(String city) {
        return userDao.selectUniversityByCity(city);
    }

    @Override
    public void insertListToCache(List<University> universityList) {
        userDao.insertListToCache(universityList);
    }

//    @Override
//    public int clearCache() {
//        return userDao.clearCache();
//    }
//
//    @Override
//    public List<University> selectCache() {
//        return userDao.selectCache();
//    }
//
//    @Override
//    public List<University> selectCacheByLevelBaseOnCity(String level) {
//        return userDao.selectCacheByLevelBaseOnCity(level);
//    }
//
//    @Override
//    public void insertListToCache_test(University_cache university_cache) {
//        userDao.insertListToCache_test(university_cache);
//    }
//
//    @Override
//    public void deleteCacheBaseOnJYB(Integer student_id) {
//        userDao.deleteCacheBaseOnJYB(student_id);
//    }
//
//    @Override
//    public void deleteCacheNameJYB(Integer student_id) {
//        userDao.deleteCacheNameJYB(student_id);
//    }
//
//    @Override
//    public void deleteCacheNot985(Integer student_id) {
//        userDao.deleteCacheNot985(student_id);
//    }
//
//    @Override
//    public void deleteCacheNot211(Integer student_id) {
//        userDao.deleteCacheNot211(student_id);
//    }
//
//    @Override
//    public void deleteCacheNotBK(Integer student_id) {
//        userDao.deleteCacheNotBK(student_id);
//    }
//
//    @Override
//    public void deleteCacheNotZK(Integer student_id) {
//        userDao.deleteCacheNotZK(student_id);
//    }

    @Override
    public Integer selectStudentIdByUsername(String username) {
        return userDao.selectStudentIdByUsername(username);
    }

//    @Override
//    public void deleteCacheNotFirstRateUniversity(Integer student_id) {
//        userDao.deleteCacheNotFirstRateUniversity(student_id);
//    }
//
//    @Override
//    public void deleteCacheNotFirstClassDisciplineUniversity(Integer student_id) {
//        userDao.deleteCacheNotFirstClassDisciplineUniversity(student_id);
//    }
//
//    @Override
//    public List<University> selectCache_finally(Integer student_id) {
//        return userDao.selectCache_finally(student_id);
//    }
//
//    @Override
//    public void deleteCacheNotPostgraduate() {
//        userDao.deleteCacheNotPostgraduate();
//    }
//
//    @Override
//    public void deleteCacheHavePostgraduate() {
//        userDao.deleteCacheHavePostgraduate();
//    }

    @Override
    public List<University_cache> selectInCities(String cities) {
        return userDao.selectInCities(cities);
    }


}
