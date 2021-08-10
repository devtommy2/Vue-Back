package com.tommy.service.impl;

import com.tommy.dao.AdminDao;
import com.tommy.domain.Major;
import com.tommy.domain.User;
import com.tommy.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public void adminUpdateUserInformation(Map<String, Object> map) {
        adminDao.adminUpdateUserInformation(map);
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
        adminDao.deleteLineFormTeacherStudent(student_id);
    }

    @Override
    public int selectTeacherIdByUsername(String username) {
        return adminDao.selectTeacherIdByUsername(username);
    }


}
