package com.qgbest.toolkit.query.conditions;

import com.qgbest.toolkit.query.config.Config;
import com.qgbest.toolkit.query.pojo.Condition;

import java.util.Map;

public interface CommonCondition {

    String resolve(Object value, Condition condition, Config config);

}
