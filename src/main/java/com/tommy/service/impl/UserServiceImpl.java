package com.tommy.service.impl;

import com.tommy.dao.UserDao;
import com.tommy.domain.*;
import com.tommy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * coding and debug by tommy
 */

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
    public void addUser(Map<String, Object> map) {
        userDao.addUser(map);
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
    public List<University> selectAllCitiesUniversity(String condition) {
        return userDao.selectAllCitiesUniversity(condition);
    }

    @Override
    public List<University> selectCitiesUniversity(String condition) {
        return userDao.selectCitiesUniversity(condition);
    }

    @Override
    public List<University> selectUniversityByFuzzyName(String Name) {
        return userDao.selectUniversityByFuzzyName(Name);
    }

    @Override
    public List<Major> selectMajorByType(String condition) {
        return userDao.selectMajorByType(condition);
    }

    @Override
    public List<String> selectTypeDetails(String condition) {
        return userDao.selectTypeDetails(condition);
    }

    @Override
    public List<Major> getMajorByFuzzySelect(String condition) {
        return userDao.getMajorByFuzzySelect(condition);
    }

    @Override
    public List<Major> getMajorJustLimitLevel(String level) {
        return userDao.getMajorJustLimitLevel(level);
    }

    @Override
    public List<Major> getMajorByNeeds(String sqlstring) {
        return userDao.getMajorByNeeds(sqlstring);
    }

    @Override
    public Integer getStudentIdByUsername(String username) {
        return userDao.getStudentIdByUsername(username);
    }

    @Override
    public void insertIntoStudentUniversity(Integer student_id, Integer university_id) {
        userDao.insertIntoStudentUniversity(student_id, university_id);
    }

    @Override
    public int quaryUniversityIfExist(Integer student_id, Integer university_id) {
        return userDao.quaryUniversityIfExist(student_id, university_id);
    }

    @Override
    public List<Integer> getUniversityIdByStudentId(Integer student_id) {
        return userDao.getUniversityIdByStudentId(student_id);
    }

    @Override
    public List<University> getUniversityByUniversityList(String SQLString) {
        return userDao.getUniversityByUniversityList(SQLString);
    }

    @Override
    public void deleteUniversityIdByStudentId(Integer student_id, Integer university_id) {
        userDao.deleteUniversityIdByStudentId(student_id, university_id);
    }

    @Override
    public Integer getUniversityNums(Integer student_id) {
        return userDao.getUniversityNums(student_id);
    }

    @Override
    public List<T_Major> getMajorByUniversitySelected(String SQLString) {
        return userDao.getMajorByUniversitySelected(SQLString);
    }

    @Override
    public List<T_Major> getMajorWithoutUniversity(String SQLString) {
        return userDao.getMajorWithoutUniversity(SQLString);
    }

    @Override
    public void insertIntoStudentMajor(Integer student_id, Integer major_id) {
        userDao.insertIntoStudentMajor(student_id, major_id);
    }

    @Override
    public List<Integer> getMajorId(Integer student_id) {
        return userDao.getMajorId(student_id);
    }

    @Override
    public List<T_Major> getMajorByMajorId(String SQLString) {
        return userDao.getMajorByMajorId(SQLString);
    }

    @Override
    public int quaryMajorIfExist(Integer student_id, Integer major_id) {
        return userDao.quaryMajorIfExist(student_id, major_id);
    }

    @Override
    public void deleteMajorByStudent(Integer student_id, Integer major_id) {
        userDao.deleteMajorByStudent(student_id, major_id);
    }

    @Override
    public List<UserInformationShow> getUserInformation(Integer student_id) {
        return userDao.getUserInformation(student_id);
    }

    @Override
    public int countStudentUniversity(Integer student_id) {
        return userDao.countStudentUniversity(student_id);
    }

    @Override
    public List<Integer> getScoreLevel(Integer score) {
        return userDao.getScoreLevel(score);
    }

    @Override
    public List<T_Major> getAutoMajor(String SQLString) {
        return userDao.getAutoMajor(SQLString);
    }

    @Override
    public void insertAutoMajorToDatabase(String SQLString) {
        userDao.insertAutoMajorToDatabase(SQLString);
    }

    @Override
    public void deleteAutoMajor(Integer student_id, Integer major_id) {
        userDao.deleteAutoMajor(student_id, major_id);
    }

    @Override
    public List<Integer> getAutoMajorId(Integer student_id) {
        return userDao.getAutoMajorId(student_id);
    }

    @Override
    public void saveSelectedMajorOrder(String SQLString) {
        userDao.saveSelectedMajorOrder(SQLString);
    }

    @Override
    public void saveAutoMajorOrder(String SQLString) {
        userDao.saveAutoMajorOrder(SQLString);
    }


}
