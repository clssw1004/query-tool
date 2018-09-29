package com.qgbest.toolkit.query.pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qgbest.toolkit.query.utils.NameTransfer;

import java.io.IOException;
import java.util.Map;

public class Condition {

    String cname;
    String sname;

    String prefix;
    String type;
    String group;
    Boolean groupUseOr;
    Boolean useOr;
    String dataType;
    String format;
    String pattern;

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getSname() {
        if (this.sname == null) {
            this.sname = NameTransfer.toHungaryName(this.cname).toUpperCase();
        }
        return sname;
    }


    public String getPrefix() {
        return prefix;
    }


    public String getType() {
        if (this.type == null) {
            this.type = "eq";
        }
        return type;
    }

    public String getGroup() {
        return group;
    }


    public Boolean getGroupUseOr() {
        if (groupUseOr == null) {
            groupUseOr = false;
        }
        return groupUseOr;
    }


    public Boolean getUseOr() {
        if (useOr == null) {
            useOr = false;
        }
        return useOr;
    }

    public String getDataType() {
        if (dataType == null) {
            dataType = "str";
        }
        return dataType;
    }


    public String getRelative() {
        return getUseOr() ? "or" : "and";
    }

    public String getGroupRelative() {
        return getGroupUseOr() ? "or" : "and";
    }

    public String getFormat() {
        return format;
    }

    public String getPattern() {
        return pattern;
    }

    public static Condition generateCondition(Map map) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(map);
        return objectMapper.readValue(json, Condition.class);
    }
}
