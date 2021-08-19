package com.tommy.common.jwt;

import com.tommy.domain.JWT;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.util.Base64;
import java.util.Date;

public class JJwtUtils {

    /**
     * 生成jwt
     * code and debug by tommy
     * @param username
     * @return
     */


    final String SECRET = "dG9tbXlhc2Rhc2Rhc2Rhc2RnZmdkZmdnaHRyZ2dkZmI=";
    byte[] secret = Base64.getDecoder().decode(SECRET);

    public String CreateJwt(String username, String identity) {

        long now = System.currentTimeMillis();  // 当前时间
        long exp = now + 1000 * 60 * 60;    // 设置过期时间为1 Hours

        String jwt = Jwts.builder()
                .setSubject("Tommy")
                .setAudience("WebSite")
                .signWith(Keys.hmacShaKeyFor(secret))
                .claim("username", username)  // 用户名
                .claim("identity", identity) // 登录身份
                .setIssuedAt(new Date())       // 当前时间   long
                .setExpiration(new Date(exp))   // 过期时间  long
                .compact();

        return jwt;
    }


    /**
     * jwt的解密，解析出来json的形式
     * @param jwt
     * @return
     */
    public Jws<Claims> readingJwt(String jwt) {
        Jws<Claims> result = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secret))
                .build()
                .parseClaimsJws(jwt);

        return result;
    }



    /**
     * jwt验证, 已封装好结果类
     * @param jwt
     * @param username code by tommy
     * @return
     */
    public JWT checkJWT(String jwt, String username, String identity) {

        Jws<Claims> claimsJws = null;
        JWT msg = new JWT();

        // 判断token是否过期的：


        try {
            claimsJws = readingJwt(jwt);

//            Date date = claimsJws.getBody().get("iat");

            /**
             * 判断秘钥是否到期的逻辑代码
             * code by tommy
             */
//            Integer date_dead_time = (Integer) claimsJws.getBody().get("exp");
//            long now_time = System.currentTimeMillis();
//            long time = now_time - date_dead_time;


            if (claimsJws == null) {
                msg.setStatueCode(4000);
                msg.setStatueMessage("This user does not exist.");
            } else if (!claimsJws.getBody().get("aud").equals("WebSite")) {
                msg.setStatueCode(4001);
                msg.setStatueMessage("aud verification failed.");
            } else if (!claimsJws.getBody().get("sub").equals("Tommy")) {
                msg.setStatueCode(4002);
                msg.setStatueMessage("sub verification failed.");
            } else if (!claimsJws.getBody().get("username").equals(username)) { // 判断这个秘钥是不是专属于本用户 username验证
                msg.setStatueCode(4003);
                msg.setStatueMessage("User information verification failed.");
            } else if (!claimsJws.getBody().get("identity").equals(identity)) {
                msg.setStatueCode(4004);
                msg.setStatueMessage("Permission verification failed.");
            } else {
                msg.setStatueMessage("jwt验证成功");
            }
        } catch (ExpiredJwtException e) {
            msg.setStatueCode(4005);
            msg.setStatueMessage("Token expired, please re-acquire.");
        } catch (Exception e) {  // 其他异常统一抛出 Token Error
            msg.setStatueCode(4006);
            msg.setStatueMessage("Token error.");
        }
        return msg;
    }
}
