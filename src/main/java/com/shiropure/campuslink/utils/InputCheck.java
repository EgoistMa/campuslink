package com.shiropure.campuslink.utils;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class InputCheck {
    private static final String EMAIL_PATTERN =
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public static boolean isNull(String input){
        return input == null || input.trim().isEmpty();
    }
    public static boolean isEmail(String input){
        return  pattern.matcher(input).matches();
    }
}
