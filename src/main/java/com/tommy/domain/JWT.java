package com.tommy.domain;

/**
 * coding and debug by tommy
 */


public class JWT {
    // 默认是200，代表访问正确  success
    private Integer StatueCode = 200;
    /**
     * 4000 does not exist 不存在
     * 4001 aud failure  aud 失败
     * 4002 sub failure  sub 失败
     * 4003 username failure  用户信息验证失败
     * 4004 Permission verification failed 权限验证失败
     * 4005 Token expired  令牌过期
     * 4006 Token Error   令牌异常
     *
     */

    private String StatueMessage = "jwt验证成功";

    public Integer getStatueCode() {
        return StatueCode;
    }

    public void setStatueCode(Integer statueCode) {
        StatueCode = statueCode;
    }

    public String getStatueMessage() {
        return StatueMessage;
    }

    public void setStatueMessage(String statueMessage) {
        StatueMessage = statueMessage;
    }

    @Override
    public String toString() {
        return "JWT{" +
                "StatueCode=" + StatueCode +
                ", StatueMessage='" + StatueMessage + '\'' +
                '}';
    }
}
