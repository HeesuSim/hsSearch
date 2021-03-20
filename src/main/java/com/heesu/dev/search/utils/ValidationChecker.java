package com.heesu.dev.search.utils;

import java.util.regex.Pattern;

public class ValidationChecker {

    public static boolean isIpAddress(String value) {
        String pattern = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        return check(pattern, value);
    }

    public static boolean isPort(int port) {
        String pattern = "^([0-9]{1,4}|[1-5][0-9]{4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5])$";
        return check(pattern, port);
    }


    private static boolean check(String pattern, Object value) {
        String val = "";
        if(value instanceof Integer) {
            val = String.valueOf(value);
        } else if(value instanceof String) {
            val = (String) value;
        } else {
            return false;
        }

        return Pattern.matches(pattern, val);
    }

}
