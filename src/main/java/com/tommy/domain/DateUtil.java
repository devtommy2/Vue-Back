package com.tommy.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    
    public static String getCurrentDateStr(String pattern){
        SimpleDateFormat format=new SimpleDateFormat(pattern);
        String currentDateStr = format.format(new Date());
        return currentDateStr;
    }
}
