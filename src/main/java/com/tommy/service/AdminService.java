package com.tommy.service;

import com.tommy.domain.User;

import java.util.List;
import java.util.Map;

public interface AdminService {

//    =============================    bug   ======
    List<User> showUserExceptAdmin(String identity);
//=================================================
    User showSingleUserExceptAdmin(String username);

    void adminUpdateUserInformation(Map<String, Object> map);

    void adminTeacherSignUp(Map<String, Object> map);

    int quaryUsernameIfExist(String username);

    void deleteUser(String username);

    int selectTeacherIdByUsername(String username);

}
