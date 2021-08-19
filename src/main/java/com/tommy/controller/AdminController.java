package com.tommy.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tommy.common.AssembleResponseMsg;
import com.tommy.domain.ResponseBody;
import com.tommy.domain.User;
import com.tommy.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * code and debug by tommy
     *
     * 在这里面进行的是admin的业务管理
     * 主要包括的业务逻辑有：
     * 1. 分页查询出所有用户(老师，学生)，接收前端发来的数据：identity进行查询具体的身份信息数据
     * 2. 修改用户信息
     * 3. 删除用户信息
     * 4. 根据username查询固定的用户信息
     */

//    按身份查找所有想看的用户信息
    @RequestMapping(value = "/showUserExceptAdmin", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public PageInfo<User> showUserExceptAdmin(HttpServletRequest request, @RequestBody Map<String, Object> map) {
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        String identity = (String) map.get("identity");

        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = adminService.showUserExceptAdmin(identity);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }

    @RequestMapping(value = "/showSingleUserExceptAdmin", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public User showSingleUserExceptAdmin(String username) {
        return adminService.showSingleUserExceptAdmin(username);
    }

    @RequestMapping(value = "/adminUpdateUserInformation", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public ResponseBody adminUpdateUserInformation(@RequestBody Map<String, Object> map) {
        String name = (String) map.get("st_name");
        Integer mark = (Integer) map.get("st_mark");
        String telephone = (String) map.get("st_mobile");
        String username = (String) map.get("username");

        for(int i = 0; i < 100; i ++ ) {
            System.out.println(map);
        }



        adminService.adminUpdateUserInformation(name, mark, telephone, username);
        return new AssembleResponseMsg().success("update success!");     // change user's information
    }


//    teacher_signUp
    @RequestMapping(value = "/adminTeacherSignUp", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public ResponseBody adminTeacherSignUp(@RequestBody Map<String, Object> map) {
        String username = (String) map.get("username");
        int flag = adminService.quaryUsernameIfExist(username);
        if (flag == 1) {
            return new AssembleResponseMsg().failure(200, 400, "The username already exists.");
        } else {
            adminService.adminTeacherSignUp(map);  // insert into table t_student_information
//            注册完之后取到老师的id
            return new AssembleResponseMsg().success("Teacher account registration is successful.");
        }
    }

    /**
     * 前端以POST的形式传过来要删除的username
     * @param map
     * @return
     */
    @RequestMapping(value = "/dropUserAccount", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public ResponseBody dropUserAccount(@RequestBody Map<String, Object> map) {

        String username = (String) map.get("username");
        int flag = adminService.quaryUsernameIfExist(username);

        if (flag == 1) {
            //存在用户，执行操作
            adminService.deleteUser(username);
            return new AssembleResponseMsg().success("删除成功~~~");
        }
        return new AssembleResponseMsg().failure(200, 400, "删除失败~~~");
    }
}
