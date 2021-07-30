package com.tommy.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 功能描述：日期工具类
 */
public class DateUtil {
    /**
     * 返回字符串形式的当前日期
     * @Author tommy
     **/
    public static String getCurrentDateStr(String pattern){
        SimpleDateFormat format=new SimpleDateFormat(pattern);
        String currentDateStr = format.format(new Date());
        return currentDateStr;
    }
}
