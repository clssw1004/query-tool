package com.qgbest.toolkit.query.utils;

public class SqlUtils {

    public static String getFormatByDatabase(String condition, String database) {
        return "to_date(" + condition + ")";
    }
}
