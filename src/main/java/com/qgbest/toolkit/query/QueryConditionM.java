package com.qgbest.toolkit.query;


import org.springframework.data.mongodb.core.query.Query;

import java.util.Map;

public class QueryConditionM {
    private Query query;
    private Map queryMap;

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public Map getQueryMap() {
        return queryMap;
    }

    public void setQueryMap(Map queryMap) {
        this.queryMap = queryMap;
    }
}
