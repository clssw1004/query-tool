package com.qgbest.toolkit.query.conditions;

import com.qgbest.toolkit.query.config.Config;

import java.util.Map;

public interface CommonCondition {

    String resolve(Object value, Map condition, Config config);

}
