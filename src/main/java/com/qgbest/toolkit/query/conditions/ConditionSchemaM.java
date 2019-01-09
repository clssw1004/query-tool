package com.qgbest.toolkit.query.conditions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qgbest.toolkit.query.pojo.Condition;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.io.IOException;
import java.util.*;

public class ConditionSchemaM {

    private static Map<String, List<Condition>> conditions;

    public ConditionSchemaM(String json) throws IOException {
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

    public ConditionSchemaM(Map jsonSchema) throws IOException {
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

    public Query resolveMMap(Map values) {
        Query query = new Query();
        Iterator<Map.Entry<String, Object>> iterator = values.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            List<Condition> ccnf = conditions.get(entry.getKey());
            if (ccnf != null && ccnf.size() > 0) {
                for (Condition condition : ccnf) {
                    if (entry.getValue() != null && !"".equals(entry.getValue())) {
                        switch (condition.getType()){
                            case "eq":
                                query.addCriteria(Criteria.where(condition.getSname()).is(entry.getValue()));
                                break;
                            case "gt":
                                query.addCriteria(Criteria.where(condition.getSname()).gt(entry.getValue()));
                                break;
                            case "gte":
                                query.addCriteria(Criteria.where(condition.getSname()).gte(entry.getValue()));
                                break;
                            case "lt":
                                query.addCriteria(Criteria.where(condition.getSname()).lt(entry.getValue()));
                                break;
                            case "lte":
                                query.addCriteria(Criteria.where(condition.getSname()).lte(entry.getValue()));
                                break;
                            case "%":
                                query.addCriteria(Criteria.where(condition.getSname()).regex(".*?" +entry.getValue()+ ".*"));
                                break;
                            case "in":
                                query.addCriteria(Criteria.where(condition.getSname()).in(entry.getValue()));
                                break;
                            default:
                                query.addCriteria(Criteria.where(condition.getSname()).is(entry.getValue()));
                                break;
                        }
                    }
                }
            }
        }
        return query;
    }

}
