package com.tommy.dao;

import com.tommy.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * code and debug by tommy
 */


public interface AdminDao {

    //1. 查询所有的用户，去除管理员 ========  需要修改
    List<User> showUserExceptAdmin(String identity);
//======================================================
//    2. 在搜索框内通过username进行查询用户
    User showSingleUserExceptAdmin(String username);

//    3. 修改用户信息：传来数据: username, 和各种修改后的数据：所有数据，可修改，也可以不该，提交方式为POST
    void adminUpdateUserInformation(@Param("st_name") String st_name, @Param("st_mark") Integer st_mark, @Param("st_mobile") String st_mobile, @Param("username") String username);

//    4. 注册老丝儿：
    void adminTeacherSignUp(Map<String, Object> map);

//    5. In order to register teacher's account and ensure the username only appeared once;
    int quaryUsernameIfExist(String username);

    void deleteStuInfoByUsername(String username);

    int quaryStuIdByUsername(String username);

//   找到该学生的老师的id
    Integer quaryTeacherIdByStuUsername(String username);

    int quaryIfExistStudent(Map<String, Object> map);

    int selectTeacherIdByUsername(String username);

    void deleteLineFormTeacherStudent(Integer student_id);

    void deleteUserAutoMajor(Integer student_id);

    void deleteUserSelectedMajor(Integer student_id);

    void deleteUserSelectedUniversity(Integer student_id);

    int quaryUserIdIfExist(Integer student_id);

    void deleteUserByUserId(Integer id);
}
