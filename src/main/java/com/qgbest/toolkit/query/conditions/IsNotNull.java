package com.qgbest.toolkit.query.conditions;

import com.qgbest.toolkit.query.config.Config;
import com.qgbest.toolkit.query.pojo.Condition;

public class IsNotNull implements CommonCondition {
    boolean notNull = false;

    public IsNotNull() {
        this.notNull = notNull;
    }

    public IsNotNull(boolean notNull) {
        this.notNull = notNull;
    }

    @Override
    public String resolve(Object value, Condition condition, Config config) {
        return String.format(" %s.%s is %s null", condition.getPrefix(), condition.getSname(), this.notNull ? "not" : "");
    }
}
