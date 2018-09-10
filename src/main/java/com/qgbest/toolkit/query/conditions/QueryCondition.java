package com.qgbest.toolkit.query.conditions;

import com.qgbest.toolkit.query.config.Config;

import java.util.Map;

public interface QueryCondition {

    String resolve(Object value, Map condition, Config config);
}
