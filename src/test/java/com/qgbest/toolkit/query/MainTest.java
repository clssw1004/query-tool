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

    public static final String testJson = "{" +
            "  \"rules\": [" +
            "    {" +
            "      \"cname\": \"projName\"," +
            "      \"type\": \"like\"," +
            "      \"prefix\": \"a\"" +
            "    }," +
            "    {" +
            "      \"cname\": \"cntNum\"," +
            "      \"prefix\": \"a\"" +
            "    }," +
            "    {" +
            "      \"cname\": \"sellerName\"," +
            "      \"type\": \"like\"," +
            "      \"prefix\": \"a\"" +
            "    }," +
            "    {" +
            "      \"cname\": \"buyerName\"," +
            "      \"type\": \"like\"," +
            "      \"prefix\": \"a\"" +
            "    }," +
            "    {" +
            "      \"cname\": \"submitDate1\"," +
            "      \"sname\": \"SUBMIT_DATE\"," +
            "      \"type\": \"gte\"," +
            "      \"dataType\": \"dt\"," +
            "      \"prefix\": \"a\"" +
            "    }," +
            "    {" +
            "      \"cname\": \"submitDate2\"," +
            "      \"sname\": \"SUBMIT_DATE\"," +
            "      \"type\": \"lte\"," +
            "      \"dataType\": \"dt\"," +
            "      \"prefix\": \"a\"" +
            "    }," +
            "    {" +
            "      \"cname\": \"statusCode\"," +
            "      \"prefix\": \"a\"" +
            "    }," +
            "    {" +
            "      \"cname\": \"vatStatusCode\"," +
            "      \"prefix\": \"a\"" +
            "    }," +
            "    {" +
            "      \"cname\": \"bitStatusCode\"," +
            "      \"prefix\": \"a\"" +
            "    }," +
            "    {" +
            "      \"cname\": \"adddedStatusCode\"," +
            "      \"prefix\": \"b\"" +
            "    }," +
            "    {" +
            "      \"cname\": \"regCode\"," +
            "      \"prefix\": \"b\"" +
            "    }," +
            "    {" +
            "      \"cname\": \"cntAdmdivCode\"," +
            "      \"prefix\": \"b\"" +
            "    }," +
            "    {" +
            "      \"cname\": \"fillDate1\"," +
            "      \"sname\": \"SUBMIT_DATE\"," +
            "      \"type\": \"gte\"," +
            "      \"dataType\": \"dt\"," +
            "      \"prefix\": \"a\"" +
            "    }," +
            "    {" +
            "      \"cname\": \"fillDate2\"," +
            "      \"sname\": \"SUBMIT_DATE\"," +
            "      \"type\": \"lte\"," +
            "      \"dataType\": \"dt\"," +
            "      \"prefix\": \"a\"" +
            "    }," +
            "    {" +
            "      \"cname\": \"regDate1\"," +
            "      \"sname\": \"SUBMIT_DATE\"," +
            "      \"type\": \"gte\"," +
            "      \"dataType\": \"dt\"," +
            "      \"prefix\": \"a\"" +
            "    }," +
            "    {" +
            "      \"cname\": \"regDate2\"," +
            "      \"sname\": \"SUBMIT_DATE\"," +
            "      \"type\": \"lte\"," +
            "      \"dataType\": \"dt\"," +
            "      \"prefix\": \"a\"" +
            "    }," +
            "    {" +
            "      \"cname\": \"cntTypeCode\"," +
            "      \"prefix\": \"a\"" +
            "    }" +
            "  ]" +
            "}";
}
