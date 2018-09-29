package com.qgbest.toolkit.query.conditions;

import com.qgbest.toolkit.query.config.ConditionStatic;
import com.qgbest.toolkit.query.config.Config;
import com.qgbest.toolkit.query.enumeration.EnumConditionType;
import com.qgbest.toolkit.query.pojo.Condition;
import com.qgbest.toolkit.query.utils.MapUtil;
import com.qgbest.toolkit.query.utils.NameTransfer;
import com.qgbest.toolkit.query.utils.SQLUtils;

import java.util.Map;

public class NormalCondition implements CommonCondition {
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

    public NormalCondition(String operator) {
        this.operator = operator;
    }

    public String resolve(Object value, Condition condition, Config config) {

        StringBuilder sb = new StringBuilder();

        if (condition.getPrefix() != null) {
            sb.append(String.format(" %s %s.%s", condition.getRelative(), condition.getPrefix(), condition.getSname()));
        } else {
            sb.append(String.format(" %s %s", condition.getRelative(), condition.getSname()));
        }
        EnumConditionType dataType = EnumConditionType.getType(condition.getDataType());
        switch (dataType) {
            case NUMBER:
                sb.append(String.format("%s%s", operator, value.toString()));
                break;
            case DATE:
                String format = condition.getFormat();
                if (format == null) {
                    format = config.datebase.defaultFormat;
                }
                sb.append(String.format("%s%s", operator, SQLUtils.getToDataSql(value.toString(), format, config.datebase)));
                break;
            default:
                sb.append(String.format("%s'%s'", operator, value.toString()));
        }
        sb.append(" ");
        return sb.toString();
    }
}
