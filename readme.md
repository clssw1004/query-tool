# 关于查询条件 SCHEME 配置

1. cname：必须

   > 表示 condition name，是前台传来查询条件的 KEY 值得 key

2. sname:非必需

   > 数据库中对应字段的名称，若为空则默认为 cname 值解析为匈牙利命名法（即：cname 为 createDate，若不填写 sname，sname 默认为 CREATE_DATE）

3. dataType:非必须，默认值(str)

   > 需要查询的字段类型那个，可为：str（字符串）、num（数字）、dt（日期）

4. type，查询类型：非必须，默认值（eq）

   > 查询类型，值可为：eq（等于）、gt（大于）、gte（大于等于）、lt（小于）、lte（小于等于）、%（like 查询）、in（范围查询）

5. pattern,某些特殊查询会用到。
   > 例如当 type 为 % ,sname 为 CNT_NUM pattern 为%$,前台录入为 123，则查询条件会被组装为：and CNT_NUM like '%123'

6. prefix,查询条件表前缀
   
# 示例：

- 如下 schema：

```json
[
  {
    "cname": "projName"
  },
  {
    "cname": "fillDate",
    "dataType": "dt",
    "type": "gte"
  },
  {
    "cname": "fillDate",
    "dataType": "dt",
    "type": "lte"
  },
  {
    "cname": "statusCode",
    "type": "in",
    "prefix" : "a"
  },
  {
    "cname": "cntNum",
    "sname": "CNT_NAME",
    "pattern": "%$%",
    "type": "%"
  },
  {
    "cname": "cntNum",
    "sname": "CNT_NAME",
    "pattern": "320%$",
    "type": "%"
  },
  {
    "cname": "sellerName",
    "type": "%",
    "prefix": "a"
  },
  {
    "cname": "id",
    "sname": "CNT_ID",
    "dataType": "num",
    "type": "eq"
  }
]
```

- 输出为：

```SQL
 and FILL_DATE>=to_date(2018-09-10 23:59:59)
 and FILL_DATE<=to_date(2018-09-10 23:59:59)
 and PROJ_NAME='人类补全计划'
 and SELLER_NAME like '%南京%'
 and CNT_ID=1286
 and CNT_NAME like '%320121%'
 and CNT_NAME like '320%320121'
 and STATUS_CODE in ('01','05','10')
```
