# 关于查询条件 SCHEME 配置

* cname：必须

   > 表示 condition name，是前台传来查询条件的 KEY 值得 key

* sname:非必需

   > 数据库中对应字段的名称，若为空则默认为 cname 值解析为匈牙利命名法（即：cname 为 createDate，若不填写 sname，sname 默认为 CREATE_DATE）

* dataType:非必须，默认值(str)

   > 需要查询的字段类型那个，可为：str（字符串）、num（数字）、dt（日期）

* type，查询类型：非必须，默认值（eq）

   > 查询类型，值可为：eq（等于）、gt（大于）、gte（大于等于）、lt（小于）、lte（小于等于）、%（like 查询）、in（范围查询）、neq(不等于)、null(is null)、notnull(is not null)

* pattern,某些特殊查询会用到。

   > 例如当 type 为 like ,sname 为 CNT_NUM pattern 为%\$,前台录入为 123，则查询条件会被组装为：and CNT_NUM like '%123'

* prefix,查询条件表前缀

* group,groupUseOr 分组生成(b=1 and/or a=1) 这种形式的条件（**注意:group 值为任意只要同一组值相等就行**）

* useOr 使用 or 拼接条件而不是 and（**注意:查询条件是按 JSON 定义顺序拼接的**）

# 示例：

- 如下 schema：

```json
{
  "rules": [
    {
      "cname": "projName",
      "type": "like",
      "prefix": "a"
    },
    {
      "cname": "cntNum",
      "prefix": "a"
    },
    {
      "cname": "sellerName",
      "type": "like",
      "prefix": "a"
    },
    {
      "cname": "buyerName",
      "type": "like",
      "prefix": "a",
      "useOr": true,
      "group": "aa"
    },
    {
      "cname": "buyerName",
      "type": "null",
      "prefix": "a",
      "useOr": true,
      "group": "aa"
    },
    {
      "cname": "submitDate1",
      "sname": "SUBMIT_DATE",
      "type": "gte",
      "dataType": "dt",
      "prefix": "a"
    },
    {
      "cname": "submitDate2",
      "sname": "SUBMIT_DATE",
      "type": "lte",
      "dataType": "dt",
      "prefix": "a"
    },
    {
      "cname": "statusCode",
      "prefix": "a"
    },
    {
      "cname": "statusCode",
      "prefix": "a",
      "type": "notnull"
    },
    {
      "cname": "vatStatusCode",
      "prefix": "a"
    },
    {
      "cname": "bitStatusCode",
      "prefix": "a",
      "group": "c2",
      "groupUseOr": true
    },
    {
      "cname": "adddedStatusCode",
      "prefix": "b",
      "group": "c2",
      "groupUseOr": true
    },
    {
      "cname": "regCode",
      "prefix": "b",
      "group": "c1"
    },
    {
      "cname": "cntAdmdivCode",
      "prefix": "b",
      "group": "c1"
    },
    {
      "cname": "fillDate1",
      "sname": "SUBMIT_DATE",
      "type": "gte",
      "dataType": "dt",
      "prefix": "a"
    },
    {
      "cname": "fillDate2",
      "sname": "SUBMIT_DATE",
      "type": "lte",
      "dataType": "dt",
      "prefix": "a"
    },
    {
      "cname": "regDate1",
      "sname": "SUBMIT_DATE",
      "type": "gte",
      "dataType": "dt",
      "prefix": "a"
    },
    {
        "cname": "cntId",
        "type": "in",
        "prefix": "a"
    },
    {
      "cname": "regDate2",
      "sname": "SUBMIT_DATE",
      "type": "lte",
      "dataType": "dt",
      "prefix": "a"
    },
    {
      "cname": "cntTypeCode",
      "prefix": "a"
    }
  ]
}

```

查询条件值为：

```java
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
    map.put("cntId",cntId);
    ConditionSchema schema = new ConditionSchema(testJson);
    System.out.println(schema.resolveMap(map));
```

- 输出为：

```SQL
 and a.CNT_ID in ('fdfgdfdfd','fdgfrghtrfd','gtry567656y','dderfert5yuht') 
 and a.PROJ_NAME like '%人类补全计划%' 
 and a.SELLER_NAME like '%南京%' 
 and a.CNT_NUM='320121' 
 and a.CNT_TYPE_CODE='0201' 
 and a.SUBMIT_DATE>=to_date('2018-09-01 12:02:11','yyyy-mm-dd hh24:mi:ss') 
 and a.SUBMIT_DATE<=to_date('2018-09-10 23`:02:11','yyyy-mm-dd hh24:mi:ss') 
 and a.STATUS_CODE='05' 
 and a.STATUS_CODE is not null
 and (a.BUYER_NAME like '%崔伟%'  or a.BUYER_NAME is  null ) 
 and (b.CNT_ADMDIV_CODE='32121'  and b.REG_CODE='32012'  ) 
 or (b.ADDDED_STATUS_CODE='123'  and a.BIT_STATUS_CODE='456'  ) 

```
