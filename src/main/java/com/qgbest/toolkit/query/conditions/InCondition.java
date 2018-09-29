package com.qgbest.toolkit.query.conditions;

import com.qgbest.toolkit.query.config.ConditionStatic;
import com.qgbest.toolkit.query.config.Config;
import com.qgbest.toolkit.query.enumeration.EnumConditionType;
import com.qgbest.toolkit.query.pojo.Condition;
import com.qgbest.toolkit.query.utils.MapUtil;
import com.qgbest.toolkit.query.utils.NameTransfer;
import com.qgbest.toolkit.query.utils.SQLUtils;

import java.util.List;
import java.util.Map;

public class InCondition implements CommonCondition {
    public String resolve(Object value, Condition condition, Config config) {
        List values = (List) value;
        if (values.size() == 1) {
            /**
             * 当条件in查询长度为1时，自动优化为等于处理
             */
            return NormalCondition.EQ.resolve(value, condition, config);
        }
        StringBuilder sb = new StringBuilder();


        if (condition.getPrefix() != null) {
            sb.append(String.format(" %s %s.%s in (", condition.getRelative(), condition.getPrefix(), condition.getSname()));
        } else {
            sb.append(String.format(" %s %s in (", condition.getRelative(), condition.getSname()));
        }
        EnumConditionType dataType = EnumConditionType.getType(condition.getDataType());
        for (int i = 0; i != values.size(); ++i) {
            Object o = values.get(i);
            switch (dataType) {
                case NUMBER:
                    sb.append(String.format("%s", o.toString()));
                    break;
                case DATE:
                    String format = condition.getFormat();
                    if (format == null) {
                        format = config.datebase.defaultFormat;
                    }
                    sb.append(String.format("%s", SQLUtils.getToDataSql(value.toString(), format, config.datebase)));
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
