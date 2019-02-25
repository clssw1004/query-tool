package com.qgbest.toolkit.query.conditions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qgbest.toolkit.query.pojo.Condition;

import java.io.IOException;
import java.util.*;

public class ConditionSchema {

    private Map<String, List<Condition>> conditions;

    public ConditionSchema(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map jsonSchema = objectMapper.readValue(json, Map.class);
        List list = (List) jsonSchema.get("rules");
        conditions = new HashMap<>();
        for (Object o : list) {
            Condition c = Condition.generateCondition((Map) o);
            if (!conditions.containsKey(c.getCname())) {
                conditions.put(c.getCname(), new ArrayList<>());
            }
            conditions.get(c.getCname()).add(c);
        }
    }

    public ConditionSchema(Map jsonSchema) throws IOException {
        List list = (List) jsonSchema.get("rules");
        conditions = new HashMap<>();
        for (Object o : list) {
            Condition c = Condition.generateCondition((Map) o);
            if (!conditions.containsKey(c.getCname())) {
                conditions.put(c.getCname(), new ArrayList<>());
            }
            conditions.get(c.getCname()).add(c);
        }
    }

    public String resolveMap(Map values) {
        StringBuilder sql = new StringBuilder();
        Map<String, List<String>> groupMap = new HashMap<>();
        Map<String, String> groupRelative = new HashMap<>();
        Iterator<Map.Entry<String, Object>> iterator = values.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            List<Condition> ccnf = conditions.get(entry.getKey());
            if (ccnf != null && ccnf.size() > 0) {
                for (Condition c : ccnf) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        String whereItem = ConditionFactory.resolve(entry.getValue(), c);
                        if (c.getGroup() == null) {
                            sql.append(whereItem).append("\n");
                        } else {
                            if (!groupMap.containsKey(c.getGroup())) {
                                groupMap.put(c.getGroup(), new ArrayList<String>());
                                groupRelative.put(c.getGroup(), "and");
                            }
                            if (c.getGroupUseOr()) {
                                groupRelative.put(c.getGroup(), "or");
                            }
                            groupMap.get(c.getGroup()).add(whereItem);
                        }
                    }
                }
            }
        }
        Iterator<Map.Entry<String, List<String>>> groupIter = groupMap.entrySet().iterator();
        while (groupIter.hasNext()) {
            Map.Entry<String, List<String>> entry = groupIter.next();
            String tmp = String.format(" %s (", groupRelative.get(entry.getKey()));
            for (String s : entry.getValue()) {
                tmp += s;
            }
            tmp += " ) ";
            tmp = tmp.replaceAll("\\(\\s+(and|or)\\s+", "(");
            sql.append(tmp).append("\n");
        }
        return sql.toString();
    }



    public String resolveSingle(Object value, String cname) {
        if (value != null && !"".equals(value)) {
            StringBuilder sb = new StringBuilder();
            List<Condition> ccnf = conditions.get(cname);
            if (ccnf != null && ccnf.size() > 0) {
                if (ccnf == null || ccnf.size() == 0) {
                    return "";
                }
                for (Condition cnf : ccnf) {
                    sb.append(ConditionFactory.resolve(value, cnf));
                    sb.append("\n");
                }
                return sb.toString();
            }
        }
        return "";
    }
}
