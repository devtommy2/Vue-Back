package com.tommy.common;

import com.tommy.domain.InfoMsg;
import com.tommy.domain.ResponseBody;

public class AssembleResponseMsg {

    /**
     * 登陆成功返回内容
     * @return ResponseBody
     * code and debug by tommy
     **/
    public <T> ResponseBody success(T data){
        ResponseBody<T> resp = new ResponseBody<T>();
        resp.setData(data);
        InfoMsg info = new InfoMsg();
        resp.setInfo(info);
        return resp;
    }

    /**
     * 失败/异常返回内容
     * @return ResponseBody
     **/
    public <T>ResponseBody failure(int status,Integer errorCode,String message){
        ResponseBody<T> resp = new ResponseBody<T>();
        resp.setStatus(status);
        InfoMsg info = new InfoMsg();
        info.setCode(errorCode);
        info.setMessage(message);
        resp.setInfo(info);
        return resp;
    }
}
