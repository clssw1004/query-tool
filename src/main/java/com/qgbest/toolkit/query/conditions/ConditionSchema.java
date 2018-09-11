package com.qgbest.toolkit.query.conditions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qgbest.toolkit.query.config.ConditionStatic;
import com.qgbest.toolkit.query.utils.MapUtil;

import java.io.IOException;
import java.util.*;

public class ConditionSchema {

    private static Map<String, List<Map>> conditions;

    public ConditionSchema(String jsonSchema) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List list = objectMapper.readValue(jsonSchema, List.class);
        conditions = new HashMap<>();
        for (Object o : list) {
            Map conditionCnf = (Map) o;
            String cName = MapUtil.getStringFromMap(ConditionStatic.CONDITION_NAME, conditionCnf);
            if (!conditions.containsKey(cName)) {
                conditions.put(cName, new ArrayList<>());
            }
            conditions.get(cName).add(conditionCnf);

        }
    }

    public String resolveMap(Map values) {
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<String, Object>> iterator = values.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            sb.append(resolveSingle(entry.getValue(), entry.getKey()));

        }
        return sb.toString();
    }

    public String resolveSingle(Object value, String cname) {
        StringBuilder sb = new StringBuilder();
        List<Map> ccnf = conditions.get(cname);
        if (ccnf == null || ccnf.size() == 0) {
            return "";
        }
        for (Map cnf : ccnf) {
            sb.append(ConditionFactory.resolve(value, cnf));
            sb.append("\n");
        }
        return sb.toString();

    }
}
