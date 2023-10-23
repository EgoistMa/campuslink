package com.shiropure.campuslink.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTool {
    public static Date nextDay(Date date) {
        return new Date(date.getTime() + 24 * 60 * 60 * 1000);
    }
    public static Date nextThreeHours(Date date) {
        return new Date(date.getTime() + 3 * 60 * 60 * 1000);
    }
    public static String getNowInString(SimpleDateFormat simpleDateFormat){
        return simpleDateFormat.format(new Date());
    }
}
