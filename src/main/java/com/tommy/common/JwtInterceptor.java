package com.tommy.common;

import com.tommy.common.jwt.JJwtUtils;
import com.tommy.domain.JWT;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {
    /**
     * 目前的问题在于当token发生错误时并没有将错误输出到header中，而是在网页报错500
     * 查找错误所在之处。
     * 初步定为错误位置：
     *
     * 问题描述：规范化拦截器内的代码
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        JJwtUtils jwtUtils = new JJwtUtils();  // 获取到jwt对象

        // Header获取到token, username, identity
        String tokenStr = request.getHeader("token");
        String username = request.getHeader("username");
        String identity = request.getHeader("identity");

        /**
         *
         * token可能存在的错误：
         * 1.token不存在
         * 2. token 通过校验发现不正确
         */
        String url = request.getServletPath();

        //第一个错误：
        if (tokenStr == null || tokenStr.equals("")) {  // 没有jwt, 获取不到jwt
            response.setHeader("error_code", String.valueOf(4000));
            response.setHeader("error_message", "No Token!");
            return false;
        } else {
            // 存在token的情况
            // 在这里进行token验证
            JWT jwt = jwtUtils.checkJWT(tokenStr, username, identity);  // 进行结果校验
            if (jwt.getStatueCode() == 200 && jwt.getStatueMessage().equals("jwt验证成功")) {
                response.setHeader("error_code", String.valueOf(200));
                response.setHeader("error_message", jwt.getStatueMessage());

                /**
                 * 在这里进行其他的业务判断，例如权限的验证
                 */


                return true;
            } else { // 存在token但是校验失败的情况：
                response.setHeader("error_code", String.valueOf(jwt.getStatueCode()));
                response.setHeader("error_message", jwt.getStatueMessage());
                return false;
            }
        }
        /**
         * 该拦截器拦截除了登陆和注册之外的其他功能。
         * 验证header中携带的token是否正确
         * 将验证结果放入到 response.Header  (error_code, error_message)，前端根据验证结果进行页面的跳转。
         *
         * 对于权限验证，应进行URL的解析，得到需要权限的没有权限的无法进入
         */

    }
}
