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
        Map<String, List> mapGroup = new HashMap<>();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            List<Condition> ccnf = conditions.get(entry.getKey());
            if (ccnf == null || ccnf.size() == 0)
                continue;
            for (Condition condition : ccnf) {
                if (entry.getValue() == null || "".equals(entry.getValue()))
                    continue;
                if (condition.getGroup() != null) {
                    List groupF = new ArrayList<>();
                    if (mapGroup.get(condition.getSname()) == null) {
                        groupF.add(condition);
                        groupF.add(entry.getValue());
                        mapGroup.put(condition.getSname(), groupF);
                    } else {
                        groupF = mapGroup.get(condition.getSname());
                        groupF.add(condition);
                        groupF.add(entry.getValue());
                        mapGroup.put(condition.getSname(), groupF);
                    }
                } else {
                    switch (condition.getType()) {
                        case "eq":
                            query.addCriteria(Criteria.where(condition.getCname()).is(entry.getValue()));
                            break;
                        case "gt":
                            query.addCriteria(Criteria.where(condition.getSname()).gt(entry.getValue()));
                            break;
                        case "gte":
                            query.addCriteria(Criteria.where(condition.getSname()).exists(false).andOperator(Criteria.where(condition.getSname()).gte(entry.getValue())));
                            break;
                        case "lt":
                            query.addCriteria(Criteria.where(condition.getSname()).exists(false).andOperator(Criteria.where(condition.getSname()).lt(entry.getValue())));
                            break;
                        case "lte":
                            query.addCriteria(Criteria.where(condition.getSname()).exists(false).andOperator(Criteria.where(condition.getSname()).lte(entry.getValue())));
                            break;
                        case "like":
                            query.addCriteria(Criteria.where(condition.getSname()).exists(false).andOperator(Criteria.where(condition.getCname()).regex(".*?" + entry.getValue() + ".*")));
                            break;
                        case "in":
                            query.addCriteria(Criteria.where(condition.getCname()).in(entry.getValue()));
                            break;
                        default:
                            query.addCriteria(Criteria.where(condition.getCname()).is(entry.getValue()));
                            break;
                    }
                }
            }
        }
        Iterator<Map.Entry<String, List>> entries = mapGroup.entrySet().iterator();
        while (entries.hasNext())
        {
            Map.Entry<String, List> entry = entries.next();
            List list1 = entry.getValue();
            if (list1.size()==2) {
                Condition condition = (Condition)list1.get(0);
                switch (condition.getType()) {
                    case "gt":
                        query.addCriteria(Criteria.where(condition.getSname()).gt(list1.get(1)));
                        break;
                    case "gte":
                        query.addCriteria(Criteria.where(condition.getSname()).gte(list1.get(1)));
                        break;
                    case "lt":
                        query.addCriteria(Criteria.where(condition.getSname()).lt(list1.get(1)));
                        break;
                    case "lte":
                        query.addCriteria(Criteria.where(condition.getSname()).lte(list1.get(1)));
                        break;
                }
            }else if(list1.size()==4){
                Condition condition = (Condition)list1.get(0);
                Condition condition1 = (Condition)list1.get(2);
                switch (condition.getType()) {
                    case "gt":
                        if("lt".equals(condition1.getType())) {
                            query.addCriteria(Criteria.where(condition.getSname()).gt(list1.get(1)).lt(list1.get(3)));
                        }else if("lte".equals(condition1.getType())){
                            query.addCriteria(Criteria.where(condition.getSname()).gt(list1.get(1)).lte(list1.get(3)));
                        }
                        break;
                    case "gte":
                        if("lt".equals(condition1.getType())) {
                            query.addCriteria(Criteria.where(condition.getSname()).gte(list1.get(1)).lt(list1.get(3)));
                        }else if("lte".equals(condition1.getType())){
                            query.addCriteria(Criteria.where(condition.getSname()).gte(list1.get(1)).lte(list1.get(3)));
                        }
                        break;
                    case "lt":
                        if("gt".equals(condition1.getType())) {
                            query.addCriteria(Criteria.where(condition.getSname()).lt(list1.get(1)).gt(list1.get(3)));
                        }else if("gte".equals(condition1.getType())){
                            query.addCriteria(Criteria.where(condition.getSname()).lt(list1.get(1)).gte(list1.get(3)));
                        }
                        break;
                    case "lte":
                        if("gt".equals(condition1.getType())) {
                            query.addCriteria(Criteria.where(condition.getSname()).lte(list1.get(1)).gt(list1.get(3)));
                        }else if("gte".equals(condition1.getType())){
                            query.addCriteria(Criteria.where(condition.getSname()).lte(list1.get(1)).gte(list1.get(3)));
                        }
                        break;
                }
            }
        }
        return query;
    }

}
