package com.qgbest.toolkit.query;

import com.qgbest.toolkit.query.conditions.ConditionSchema;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainTest {


    @Test
    public void testCondition() throws IOException {

        Map map = new HashMap();
        map.put("projName", "人类补全计划");
        List list = new ArrayList();
        list.add("01");
        list.add("05");
        list.add("10");
        map.put("statusCode", list);
        map.put("fillDate", "2018-09-10 23:59:59");
        map.put("cntNum", "320121");
        map.put("id", 1286);
        map.put("sellerName", "南京");
        ConditionSchema schema = new ConditionSchema(testJson);
        System.out.println(schema.resolveMap(map));
        Assert.assertTrue(true);
    }

    public static final String testJson = "[" +
            "  {" +
            "    \"cname\": \"projName\"" +
            "  }," +
            "  {" +
            "    \"cname\": \"fillDate\"," +
            "    \"dataType\": \"dt\"," +
            "    \"type\": \"gte\"" +
            "  }," +
            "  {" +
            "    \"cname\": \"fillDate\"," +
            "    \"dataType\": \"dt\"," +
            "    \"type\": \"lte\"" +
            "  }," +
            "  {" +
            "    \"cname\": \"statusCode\"," +
            "    \"type\": \"in\"" +
            "  }," +
            "  {" +
            "    \"cname\": \"cntNum\"," +
            "    \"sname\": \"CNT_NAME\"," +
            "    \"pattern\": \"%$%\"," +
            "    \"type\": \"%\"" +
            "  }," +
            "  {" +
            "    \"cname\": \"cntNum\"," +
            "    \"sname\": \"CNT_NAME\"," +
            "    \"pattern\": \"320%$\"," +
            "    \"type\": \"%\"" +
            "  }," +
            "  {" +
            "    \"cname\": \"sellerName\"," +
            "    \"type\": \"%\"" +
            "  }," +
            "  {" +
            "    \"cname\": \"id\"," +
            "    \"sname\": \"CNT_ID\"," +
            "    \"dataType\": \"num\"," +
            "    \"type\": \"eq\"" +
            "  }" +
            "]";
}
