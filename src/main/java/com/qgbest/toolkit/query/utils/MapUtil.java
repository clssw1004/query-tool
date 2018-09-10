package com.qgbest.toolkit.query.utils;

import java.util.Map;

public class MapUtil {

    public static String getStringFromMap(String key, Map map) {
        if (map.containsKey(key)) {
            return map.get(key) + "";
        }
        return null;
    }

    public static Object getObjectFromMap(String key, Map map) {
        return map.get(key);
    }
}
