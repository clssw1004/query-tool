package com.qgbest.toolkit.query.conditions;

import com.qgbest.toolkit.query.config.ConditionStatic;
import com.qgbest.toolkit.query.config.Config;
import com.qgbest.toolkit.query.utils.MapUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConditionFactory {
    static Map<String, QueryCondition> factory = new HashMap<String, QueryCondition>();

    static {
        factory.put("eq", NormalCondition.EQ);
        factory.put("gt", NormalCondition.GT);
        factory.put("gte", NormalCondition.GTE);
        factory.put("lt", NormalCondition.LT);
        factory.put("lte", NormalCondition.LTE);
        factory.put("%", new LikeCondition());
        factory.put("in", new InCondition());
    }

    public static String resolve(Object value, Map condition) {
        String type = MapUtil.getStringFromMap(ConditionStatic.TYPE, condition);
        if (type == null) {
            type = "eq";
        }
        QueryCondition queryCondition = factory.get(type);
        return queryCondition.resolve(value, condition, Config.getConfig());
    }

}
