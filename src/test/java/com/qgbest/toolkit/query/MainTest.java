package com.qgbest.toolkit.query;

import com.qgbest.toolkit.query.conditions.ConditionFactory;
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
        ConditionFactory.init();
        Map map = new HashMap();
        map.put("projName", "人类补全计划");
        map.put("statusCode", "05");
        map.put("buyerName", "崔伟");
        map.put("fillDate1", "2018-09-01 12:02:11");
        map.put("fillDate2", "2018-09-10 23`:02:11");
        map.put("cntNum", "320121");
        map.put("cntAdmdivCode", "32121");
        map.put("regCode", "32012");
        map.put("sellerName", "南京");
        map.put("cntTypeCode", "0201");
        map.put("bitStatusCode", "456");
        map.put("adddedStatusCode", "123");
        List cntId = new ArrayList();
        cntId.add("fdfgdfdfd");
        cntId.add("fdgfrghtrfd");
        cntId.add("gtry567656y");
        cntId.add("dderfert5yuht");
        map.put("cntId", cntId);
        ConditionSchema schema = new ConditionSchema(testJson);
        System.out.println(schema.resolveMap(map));
    }

    public static final String testJson = "{\n" +
            "  \"rules\": [\n" +
            "    {\n" +
            "      \"cname\": \"projName\",\n" +
            "      \"type\": \"like\",\n" +
            "      \"prefix\": \"a\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"cname\": \"cntNum\",\n" +
            "      \"prefix\": \"a\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"cname\": \"sellerName\",\n" +
            "      \"type\": \"like\",\n" +
            "      \"prefix\": \"a\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"cname\": \"buyerName\",\n" +
            "      \"type\": \"like\",\n" +
            "      \"prefix\": \"a\",\n" +
            "      \"useOr\": true,\n" +
            "      \"group\": \"aa\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"cname\": \"buyerName\",\n" +
            "      \"type\": \"null\",\n" +
            "      \"prefix\": \"a\",\n" +
            "      \"useOr\": true,\n" +
            "      \"group\": \"aa\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"cname\": \"submitDate1\",\n" +
            "      \"sname\": \"SUBMIT_DATE\",\n" +
            "      \"type\": \"gte\",\n" +
            "      \"dataType\": \"dt\",\n" +
            "      \"prefix\": \"a\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"cname\": \"submitDate2\",\n" +
            "      \"sname\": \"SUBMIT_DATE\",\n" +
            "      \"type\": \"lte\",\n" +
            "      \"dataType\": \"dt\",\n" +
            "      \"prefix\": \"a\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"cname\": \"statusCode\",\n" +
            "      \"prefix\": \"a\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"cname\": \"statusCode\",\n" +
            "      \"prefix\": \"a\",\n" +
            "      \"type\": \"notnull\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"cname\": \"vatStatusCode\",\n" +
            "      \"prefix\": \"a\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"cname\": \"bitStatusCode\",\n" +
            "      \"prefix\": \"a\",\n" +
            "      \"group\": \"c2\",\n" +
            "      \"groupUseOr\": true\n" +
            "    },\n" +
            "    {\n" +
            "      \"cname\": \"adddedStatusCode\",\n" +
            "      \"prefix\": \"b\",\n" +
            "      \"group\": \"c2\",\n" +
            "      \"groupUseOr\": true\n" +
            "    },\n" +
            "    {\n" +
            "      \"cname\": \"regCode\",\n" +
            "      \"prefix\": \"b\",\n" +
            "      \"group\": \"c1\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"cname\": \"cntAdmdivCode\",\n" +
            "      \"prefix\": \"b\",\n" +
            "      \"group\": \"c1\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"cname\": \"fillDate1\",\n" +
            "      \"sname\": \"SUBMIT_DATE\",\n" +
            "      \"type\": \"gte\",\n" +
            "      \"dataType\": \"dt\",\n" +
            "      \"prefix\": \"a\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"cname\": \"fillDate2\",\n" +
            "      \"sname\": \"SUBMIT_DATE\",\n" +
            "      \"type\": \"lte\",\n" +
            "      \"dataType\": \"dt\",\n" +
            "      \"prefix\": \"a\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"cname\": \"regDate1\",\n" +
            "      \"sname\": \"SUBMIT_DATE\",\n" +
            "      \"type\": \"gte\",\n" +
            "      \"dataType\": \"dt\",\n" +
            "      \"prefix\": \"a\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"cname\": \"cntId\",\n" +
            "        \"type\": \"in\",\n" +
            "        \"prefix\": \"a\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"cname\": \"regDate2\",\n" +
            "      \"sname\": \"SUBMIT_DATE\",\n" +
            "      \"type\": \"lte\",\n" +
            "      \"dataType\": \"dt\",\n" +
            "      \"prefix\": \"a\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"cname\": \"cntTypeCode\",\n" +
            "      \"prefix\": \"a\"\n" +
            "    }\n" +
            "  ]\n" +
            "}\n";
}
