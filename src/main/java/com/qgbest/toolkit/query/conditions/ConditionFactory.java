package com.qgbest.toolkit.query.conditions;

import com.qgbest.toolkit.query.config.Config;
import com.qgbest.toolkit.query.pojo.Condition;

import java.util.HashMap;
import java.util.Map;

public class ConditionFactory {
    static Map<String, CommonCondition> factory = new HashMap<String, CommonCondition>();

    static {
        factory.put("eq", NormalCondition.EQ);
        factory.put("neq", NormalCondition.NEQ);
        factory.put("gt", NormalCondition.GT);
        factory.put("gte", NormalCondition.GTE);
        factory.put("lt", NormalCondition.LT);
        factory.put("lte", NormalCondition.LTE);
        factory.put("like", new LikeCondition());
        factory.put("null", new IsNotNull());
        factory.put("notnull", new IsNotNull(true));
        factory.put("in", new InCondition());
    }

    public static String resolve(Object value, Condition condition) {
        CommonCondition commonCondition = factory.get(condition.getType());
        return commonCondition.resolve(value, condition, Config.getConfig());
    }

}
