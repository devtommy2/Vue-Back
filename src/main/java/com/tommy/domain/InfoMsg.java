package com.tommy.domain;

import java.io.Serializable;

/**
 * 功能描述：错误信息消息体
 * coding and debug by tommy
 */

public class InfoMsg implements Serializable {

    //自定义错误码    默认200表示正常执行, 400 means failed
    private Integer code = 200;
    //错误信息
    private String message="操作成功";

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
