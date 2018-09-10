package com.qgbest.toolkit.query.conditions;

import com.qgbest.toolkit.query.config.ConditionStatic;
import com.qgbest.toolkit.query.config.Config;
import com.qgbest.toolkit.query.utils.MapUtil;
import com.qgbest.toolkit.query.utils.NameTransfer;

import java.util.Map;

public class LikeCondition implements QueryCondition {
    public String resolve(Object value, Map condition, Config config) {
        String conditionName = MapUtil.getStringFromMap(ConditionStatic.CONDITION_NAME, condition);
        String schemaName = MapUtil.getStringFromMap(ConditionStatic.SCHEMA_NAME, condition);
        if (schemaName == null) {
            schemaName = NameTransfer.toHungaryName(conditionName);
        }
        String dataExtend = MapUtil.getStringFromMap(ConditionStatic.PATTERN, condition);
        if (dataExtend != null) {
            dataExtend = dataExtend.replace("$", value.toString());
            return " and " + schemaName + " like '" + dataExtend + "' ";
        } else {
            return " and " + schemaName + " like '%" + value.toString() + "%' ";
        }

    }
}
