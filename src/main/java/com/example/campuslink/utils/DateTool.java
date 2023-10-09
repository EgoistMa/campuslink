package com.example.campuslink.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTool {
    public static Date nextDay(Date date) {
        return new Date(date.getTime() + 24 * 60 * 60 * 1000);
    }
}
