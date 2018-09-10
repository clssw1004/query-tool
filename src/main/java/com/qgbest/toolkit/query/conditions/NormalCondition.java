package com.qgbest.toolkit.query.conditions;

import com.qgbest.toolkit.query.config.ConditionStatic;
import com.qgbest.toolkit.query.config.Config;
import com.qgbest.toolkit.query.enumeration.EnumConditionType;
import com.qgbest.toolkit.query.utils.MapUtil;
import com.qgbest.toolkit.query.utils.NameTransfer;
import com.qgbest.toolkit.query.utils.SqlUtils;

import java.util.Map;

public class NormalCondition implements QueryCondition {
    private String operator;

    /**
     * 等于 equals
     */
    public static NormalCondition EQ = new NormalCondition("=");
    /**
     * 大于 greater than
     */
    public static NormalCondition GT = new NormalCondition(">");
    /**
     * 大于等于 greater equal
     */
    public static NormalCondition GTE = new NormalCondition(">=");
    /**
     * 小于 less than
     */
    public static NormalCondition LT = new NormalCondition("<");
    /**
     * 小于 less equal
     */
    public static NormalCondition LTE = new NormalCondition("<=");
    /**
     * 模糊查询
     */
    public static NormalCondition LIKE = new NormalCondition(" like ");

    public NormalCondition(String operator) {
        this.operator = operator;
    }

    public String resolve(Map condition, Config config) {
        String conditionName = MapUtil.getStringFromMap(ConditionStatic.CONDITION_NAME, condition);
        String schemaName = MapUtil.getStringFromMap(ConditionStatic.SCHEMA_NAME, condition);
        if (schemaName == null) {
            schemaName = NameTransfer.toHungaryName(conditionName);
        }
        String dataTypeStr = MapUtil.getStringFromMap(ConditionStatic.DATA_TYPE, condition);
        EnumConditionType dataType = EnumConditionType.getType(dataTypeStr);
        Object value = MapUtil.getObjectFromMap(ConditionStatic.VALUE, condition);
        StringBuilder sb = new StringBuilder(String.format(" and %s", schemaName));
        switch (dataType) {
            case NUMBER:
                sb.append(String.format("%s%s", operator, value.toString()));
                break;
            case DATE:
                sb.append(String.format("%s%s", operator, SqlUtils.getFormatByDatabase(value.toString(), config.datebase)));
                break;
            default:
                sb.append(String.format("%s'%s'", operator, value.toString()));
        }
        sb.append(" ");
        return sb.toString();
    }
}
