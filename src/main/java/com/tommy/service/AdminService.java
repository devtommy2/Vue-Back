package com.tommy.service;

import com.tommy.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * coding and debug by tommy
 */

public interface AdminService {

    List<User> showUserExceptAdmin(String identity);

    User showSingleUserExceptAdmin(String username);

    void adminTeacherSignUp(Map<String, Object> map);

    int quaryUsernameIfExist(String username);

    void deleteUser(String username);

    int selectTeacherIdByUsername(String username);

    int quaryUserIdIfExist(Integer student_id);

    void deleteUserByUserId(Integer id);

    void adminUpdateUserInformation(String st_name, Integer st_mark, String st_mobile, String username);

}
