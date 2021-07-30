package com.tommy.controller;

import com.tommy.common.AssembleResponseMsg;
import com.tommy.domain.ResponseBody;
import com.tommy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author tommy
 *   code by tommy
 */

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private String LoginError = "登录失败，请检查您的用户名或密码重新登陆";
    private String RegisterError_1 = "注册失败，该用户名已被其他用户注册";

    // 登录的Controller(超管和普通用户的登陆鉴权)    # code by tommy
    @CrossOrigin("http://127.0.0.1:8081/#/Login")
    @RequestMapping(value = "/Login",produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public ResponseBody queryUser(@RequestBody Map<String,Object> map){
        int flag = userService.queryUser(map);
        String teacher_01 = "3";
        String name = "zza";
        Map<String,String> all = new HashMap<>();
        if(flag==1){//说明存在账号和密码，即登录账号和密码输入正确
            for (Map.Entry<String,Object> entry:map.entrySet()){
                if(entry.getValue().equals(name)){
                    all.put("token","admin");
                    break;
                }else{
                    all.put("token","student");
                }
            }
            all.put("status","OK"); // OK代表的是请求成功
            return new AssembleResponseMsg().success(all);
        }else{
            return new AssembleResponseMsg().failure(200,400, LoginError);
        }
    }


    // 实现用户的注册功能代码：
    @RequestMapping(value = "/SignUp", produces = "application/json;charset=utf-8")
    public ResponseBody addUser(@RequestBody Map<String, Object> map) {

        String username = (String) map.get("username");

        int flag = userService.queryUserbyUsername(username);

        if (flag == 1) {
            return new AssembleResponseMsg().failure(200, 400, RegisterError_1);
        } else {
            userService.addUser(map);
            return new AssembleResponseMsg().success("Register success");
        }
    }

//    更新用户信息
    @RequestMapping(value = "/updateUser", produces = "application/json;charset=utf-8")
    public ResponseBody updateUser(@RequestBody Map<String, Object> map) {
        userService.updateUser(map);
        return new AssembleResponseMsg().success("Update success");
    }

//    修改密码：
}
