package com.qgbest.toolkit.query.conditions;

import com.qgbest.toolkit.query.config.ConditionStatic;
import com.qgbest.toolkit.query.config.Config;
import com.qgbest.toolkit.query.enumeration.EnumConditionType;
import com.qgbest.toolkit.query.utils.MapUtil;
import com.qgbest.toolkit.query.utils.NameTransfer;
import com.qgbest.toolkit.query.utils.SqlUtils;

import java.util.List;
import java.util.Map;

public class InCondition implements QueryCondition {
    public String resolve(Map condition, Config config) {

        List values = (List) MapUtil.getObjectFromMap(ConditionStatic.VALUE, condition);
        if (values.size() == 1) {
            /**
             * 当条件in查询长度为1时，自动优化为等于处理
             */
            return NormalCondition.EQ.resolve(condition, config);
        }
        String conditionName = MapUtil.getStringFromMap(ConditionStatic.CONDITION_NAME, condition);
        String schemaName = MapUtil.getStringFromMap(ConditionStatic.SCHEMA_NAME, condition);
        if (schemaName == null) {
            schemaName = NameTransfer.toHungaryName(conditionName);
        }
        String dataTypeStr = MapUtil.getStringFromMap(ConditionStatic.DATA_TYPE, condition);
        EnumConditionType dataType = EnumConditionType.getType(dataTypeStr);
        StringBuilder sb = new StringBuilder(String.format(" and %s in (", schemaName));
        for (int i = 0; i != values.size(); ++i) {
            Object o = values.get(i);
            switch (dataType) {
                case NUMBER:
                    sb.append(String.format("%s", o.toString()));
                    break;
                case DATE:
                    sb.append(String.format("%s", SqlUtils.getFormatByDatabase(o.toString(), config.datebase)));
                    break;
                default:
                    sb.append(String.format("'%s'", o.toString()));
            }
            if (i != values.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(") ");
        return sb.toString();
    }
}
