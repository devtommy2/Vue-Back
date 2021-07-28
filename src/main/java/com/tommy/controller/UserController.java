package com.tommy.controller;

import com.tommy.common.AssembleResponseMsg;
import com.tommy.domain.ResponseBody;
import com.tommy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/queryUser",produces = "application/json;charset=utf-8")
    public ResponseBody queryUser(@RequestBody Map<String,Object> map){
        int flag = userService.queryUser(map);
        String name = "tommy";
        Map<String,String> all = new HashMap<>();
        if(flag==1){//说明存在账号和密码，即登录账号和密码输入正确
            for (Map.Entry<String,Object> entry:map.entrySet()){
                System.out.println("key:"+entry.getKey()+" value:"+entry.getValue());
                if(entry.getValue().equals(name)){
                    all.put("token","admin");
                    break;
                }else{
                    all.put("token","editor");
                }
            }

            all.put("status","OK");
            return new AssembleResponseMsg().success(all);
        }else{
            return new AssembleResponseMsg().failure(200,"error","用户名或密码错误");
        }
    }

}
