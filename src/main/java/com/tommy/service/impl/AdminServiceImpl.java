package com.tommy.service.impl;

import com.tommy.dao.AdminDao;
import com.tommy.domain.User;
import com.tommy.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * coding and debug by tommy
 */

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

//    ===============================================
    @Override
    public List<User> showUserExceptAdmin(String identity) {
        return adminDao.showUserExceptAdmin(identity);
    }
//    =================================================

    @Override
    public User showSingleUserExceptAdmin(String username) {
        return adminDao.showSingleUserExceptAdmin(username);
    }

    @Override
    public void adminTeacherSignUp(Map<String, Object> map) {
        adminDao.adminTeacherSignUp(map);
    }

    @Override
    public int quaryUsernameIfExist(String username) {
        return adminDao.quaryUsernameIfExist(username);
    }

    @Override
    public void deleteUser(String username) {
        int student_id = adminDao.quaryStuIdByUsername(username);
        adminDao.deleteStuInfoByUsername(username);   // 成功删除用户在用户表中的信息
        adminDao.deleteLineFormTeacherStudent(student_id);  // 移除teacher_student关系
        adminDao.deleteUserSelectedMajor(student_id);  // 清空专业，学校....
        adminDao.deleteUserAutoMajor(student_id);
        adminDao.deleteUserSelectedUniversity(student_id);
    }

    @Override
    public int selectTeacherIdByUsername(String username) {
        return adminDao.selectTeacherIdByUsername(username);
    }

    @Override
    public int quaryUserIdIfExist(Integer student_id) {
        return adminDao.quaryUserIdIfExist(student_id);
    }

    @Override
    public void deleteUserByUserId(Integer id) {
        adminDao.deleteUserByUserId(id);
    }

    @Override
    public void adminUpdateUserInformation(String st_name, Integer st_mark, String st_mobile, String username) {
        adminDao.adminUpdateUserInformation(st_name, st_mark, st_mobile, username);
    }




}
