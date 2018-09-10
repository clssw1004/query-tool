package com.qgbest.toolkit.query;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qgbest.toolkit.query.conditions.ConditionFactory;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MainTest {

    public static final String testJson = "[" +
            "  {" +
            "    \"cname\": \"projName\"," +
            "    \"value\": \"人类补全计划\"" +
            "  }," +
            "  {" +
            "    \"cname\": \"beginDate\"," +
            "    \"value\": \"2018-09-01 01:01:12\"," +
            "    \"dataType\": \"dt\"," +
            "    \"type\": \"gte\"" +
            "  }," +
            "  {" +
            "    \"cname\": \"beginDate\"," +
            "    \"value\": \"2018-09-10 23:59:59\"," +
            "    \"dataType\": \"dt\"," +
            "    \"type\": \"lte\"" +
            "  }," +
            "  {" +
            "    \"cname\": \"statusCode\"," +
            "    \"value\": [\"01\", \"05\", \"10\"]," +
            "    \"type\": \"in\"" +
            "  }" +
            "]";

    @Test
    public void testCondition() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List list = objectMapper.readValue(testJson, List.class);
        for (Object o : list) {
            String config = ConditionFactory.resolve((Map) o);
            System.out.println(config);
        }
        Assert.assertTrue(true);
    }
}
