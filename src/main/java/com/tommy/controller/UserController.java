package com.tommy.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tommy.common.AssembleResponseMsg;
import com.tommy.common.jwt.JJwtUtils;
import com.tommy.domain.JWT;
import com.tommy.domain.ResponseBody;
import com.tommy.domain.University;
import com.tommy.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
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

    // 登录的Controller(超管和普通用户的登陆鉴权)    # code by tommy 实现的是token鉴权机制
    @CrossOrigin("http://127.0.0.1:8081/#/Login")
    @RequestMapping(value = "/Login",produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public ResponseBody queryUser(HttpServletRequest request, @RequestBody Map<String, Object> map){

        Map<String,String> all = new HashMap<>();

        JJwtUtils jwtUtils = new JJwtUtils(); // 创建实例，可获取到token

        // 需要前端将username储存到请求头中
        String username_header = request.getHeader("username");

//        String username = (String) map.get("username");    // Map获取username
        String identity_header = request.getHeader("identity");  // header中获取 admin/ editor/ teacher

        String tokenStr = request.getHeader("token");   // 从头中得到的token

        if (tokenStr != null) {
//               验证后存在token，直接登陆，一定存在该用户
            JWT jwt = jwtUtils.checkJWT(tokenStr, username_header, identity_header);
            if (jwt.getStatueCode() == 200 && jwt.getStatueMessage().equals("jwt验证成功")) {
                // ***** debug by tommy
                all.put("identity", identity_header);
                all.put("token", tokenStr);
                return new AssembleResponseMsg().success(all);
            } else {
                return new AssembleResponseMsg().failure(200, jwt.getStatueCode(), jwt.getStatueMessage());
            }
        } else {  // 不存在token，新生成token
            int flag = userService.queryUser(map);
            if (flag == 1) {  // 数据库里存在该用户, 允许生成token
                String username_login = (String) map.get("username");
                String identity_login = userService.getIdentity(username_login);
                String token_new = jwtUtils.CreateJwt(username_login, identity_login);  // 初次登陆，如果没有token直接生成一个token
                all.put("identity", identity_login);
                all.put("token", token_new);   // 需要前端获取到数据后将token储存到请求头里！
                // 登陆成功后前端将username, identity, token储存到请求头中
                all.put("username", username_login);
                return new AssembleResponseMsg().success(all);
            } else {
                return new AssembleResponseMsg().failure(200, 400, LoginError);
            }
        }
    } // 登陆小问题bug解决完成  debug by tommy 2021.8/3 at 20:07


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
    public ResponseBody updateUser(HttpServletRequest request, @RequestBody Map<String, Object> map) {

        // Don't need user to enter String named 'username'

        // 有bug

        String token = request.getHeader("token"); // get token from request header
        JJwtUtils jwtUtils = new JJwtUtils(); // Create
        Jws<Claims> jws = jwtUtils.readingJwt(token);
        String username = (String) jws.getBody().get("username");
        map.put("username", username);

        /**
         *
         *  code by tommy at 2021/8/3  14:47
         * 在请求头中的username等身份信息是通过token得到的
         * 避免用户只要知道其他用户的用户名直接修改别人的信息，这里使用了二次校验：反向解析token 后获取到 username， 和输入的
         * 信息进行对比，正确允许修改，反之不允许。
         *
         * 在这里不需要用户输入username了，通过token校验身份。
         */

        userService.updateUser(map);
        return new AssembleResponseMsg().success("Update success");
    }

//    修改密码：
    @RequestMapping(value = "/changePassword", produces = "application/json;charset=utf-8")
    public ResponseBody changePassword(HttpServletRequest request, @RequestBody Map<String, Object> map) {

        Map<String, Object> username_password = new HashMap<>();
        Map<String, Object> updatePassword = new HashMap<>(); // 储存新密码和username的

        String token = request.getHeader("token");

        JJwtUtils jwtUtils = new JJwtUtils();
        Jws<Claims> jws = jwtUtils.readingJwt(token);

        String username = (String) jws.getBody().get("username");  // 当前登陆状态的用户的username
        String password = (String) map.get("password");

        username_password.put("username", username);
        username_password.put("password", map.get("old_password"));

        updatePassword.put("username", username);
        updatePassword.put("password", password);

        int flag = userService.queryUser(username_password);
        if (flag == 1) {  // 旧密码验证成功
            userService.changePassword(updatePassword);
            return new AssembleResponseMsg().success("Password reset complete.");
        } else {
            return new AssembleResponseMsg().failure(200, 400, "请输入正确的原密码!");
        }
    }


//    院校库的信息显示：
//    支持分页查询，需要前端发送参数：pageNum， pageSize，请求方式为GET请求
//    该信息展示功能为所有用户均能看到的信息界面，不做特殊化，若能登录就能看
    @RequestMapping(value = "/showUniversity", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public PageInfo<University> showUniversity(HttpServletRequest request, int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);

        List<University> universityList = userService.showUniversity();

        PageInfo<University> pageInfo = new PageInfo<>(universityList);

        return pageInfo;
    }

//    

//    专业库的信息显示：

//    支持多选


//    用户的信息储存在哪里？
//    储存在数据库？
//    先写好拦截器
}
