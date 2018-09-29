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
        map.put("siSeller", "人类补全计划");
        map.put("siBuyer", "05");
//        map.put("fillDate1", "2018-09-01 12:02:11");
//        map.put("fillDate2", "2018-09-10 23`:02:11");
//        map.put("cntNum", "320121");
//        map.put("sellerName", "南京");
//        map.put("cntTypeCode", "0201");
        ConditionSchema schema = new ConditionSchema(testJson);
        System.out.println(schema.resolveMap(map));
        Assert.assertTrue(true);
    }

    public static final String testJson = "{" +
            "  \"rules\": [" +
            "    {" +
            "      \"cname\": \"siSeller\"," +
            "      \"sname\": \"\\\"siSeller\\\"\"," +
            "      \"prefix\": \"a\"," +
            "      \"group\": \"s\"" +
            "    }," +
            "    {" +
            "      \"cname\": \"siBuyer\"," +
            "      \"sname\": \"\\\"siBuyer\\\"\"," +
            "      \"prefix\": \"a\"," +
            "      \"group\": \"s\"," +
            "      \"useOr\":true" +
            "    }," +
            "    {" +
            "      \"cname\": \"siAgency\"," +
            "      \"sname\": \"\\\"siAgency\\\"\"," +
            "      \"prefix\": \"a\"," +
            "      \"group\": \"t\"" +
            "    }," +
            "    {" +
            "      \"cname\": \"siTechManager\"," +
            "      \"sname\": \"\\\"siTechManager\\\"\"," +
            "      \"prefix\": \"a\"," +
            "      \"group\": \"t\"," +
            "      \"useOr\":true" +
            "    }," +
            "    {" +
            "      \"cname\": \"orgName\"," +
            "      \"sname\": \"\\\"orgName\\\"\"," +
            "      \"type\": \"like\"," +
            "      \"prefix\": \"a\"" +
            "    }," +
            "    {" +
            "      \"cname\": \"changeDate1\"," +
            "      \"sname\": \"CHANGE_DATE\"," +
            "      \"type\": \"gte\"," +
            "      \"dataType\": \"dt\"," +
            "      \"prefix\": \"a\"" +
            "    }," +
            "    {" +
            "      \"cname\": \"changeDate2\"," +
            "      \"sname\": \"CHANGE_DATE\"," +
            "      \"type\": \"lte\"," +
            "      \"dataType\": \"dt\"," +
            "      \"prefix\": \"a\"" +
            "    }," +
            "    {" +
            "      \"cname\": \"regorgCode\"," +
            "      \"sname\": \"\\\"regorgCode\\\"\"," +
            "      \"prefix\": \"a\"," +
            "      \"type\": \"like\"" +
            "    }" +
            "  ]" +
            "}";
}
