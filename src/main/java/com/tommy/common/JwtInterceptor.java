package com.tommy.common;

import com.tommy.common.jwt.JJwtUtils;
import com.tommy.domain.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * code and debug by tommy
 */

public class JwtInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        JJwtUtils jwtUtils = new JJwtUtils();  // 获取到jwt对象

        String tokenStr = request.getHeader("token");
        String username = request.getHeader("username");
        String identity = request.getHeader("identity");


        String url = request.getServletPath();


        if (tokenStr == null || tokenStr.equals("")) {  // 没有jwt, 获取不到jwt
            response.setHeader("error_code", String.valueOf(4000));
            response.setHeader("error_message", "No Token!");
            return false;
        } else {

            JWT jwt = jwtUtils.checkJWT(tokenStr, username, identity);  // 进行结果校验
            if (jwt.getStatueCode() == 200 && jwt.getStatueMessage().equals("jwt验证成功")) {
                Jws<Claims> jws = jwtUtils.readingJwt(tokenStr);
                if (url.contains("/Admin")) { // /Admin权限验证，非管理员无法进入管理员页面
                    if (!jws.getBody().get("identity").equals("admin")) {
                        response.setHeader("error_code", String.valueOf(4004));
                        response.setHeader("error_message", "Permission verification failed");
                        return false;
                    } else {
                        response.setHeader("error_code", String.valueOf(200));
                        response.setHeader("error_message", jwt.getStatueMessage());
                        return true;
                    }
                }
                response.setHeader("error_code", String.valueOf(200));
                response.setHeader("error_message", jwt.getStatueMessage());

                return true;
            } else { // 存在token但是校验失败的情况：
                response.setHeader("error_code", String.valueOf(jwt.getStatueCode()));
                response.setHeader("error_message", jwt.getStatueMessage());
                return false;
            }
        }

    }
}
