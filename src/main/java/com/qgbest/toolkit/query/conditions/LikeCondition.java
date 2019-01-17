package com.qgbest.toolkit.query.conditions;

import com.qgbest.toolkit.query.config.Config;
import com.qgbest.toolkit.query.pojo.Condition;

public class LikeCondition implements CommonCondition {
    public String resolve(Object value, Condition condition, Config config) {
        String dataExtend = condition.getPattern();
        if (dataExtend != null) {
            dataExtend = dataExtend.replace("$", value.toString());
        } else {
            dataExtend = "%" + value.toString() + "%";
        }
        if (condition.getPrefix() != null) {
            return " and " + condition.getPrefix() + "." + condition.getSname() + " like '" + dataExtend + "' ";
        } else {
            return " and " + condition.getSname() + " like '" + dataExtend + "' ";
        }

    }

}
