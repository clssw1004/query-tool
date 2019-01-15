package com.qgbest.toolkit.query.utils;


import com.qgbest.toolkit.query.enumeration.EnumDatabase;

public class SQLUtils {

    public static String getToDataSql(String value, String format, EnumDatabase database) {
        String sql = "";
        switch (database) {
            case MYSQL:
                sql = "str_to_date('" + value + "','" + format + "')";
                break;
            case ORACLE11g:
                sql = String.format("to_date('" + value + "','" + format + "')");
                break;
            default:
                throw new RuntimeException("Unknown database type：" + database);
        }
        return sql;
    }

    public static String getToDataSql(String value, EnumDatabase database) {
        String sql = "";
        switch (database) {
            case MYSQL:
                sql = "str_to_date('" + value + "','" + database.defaultFormat + "')";
                break;
            case ORACLE11g:
                sql = String.format("to_date('" + value + "','" + database.defaultFormat + "')");
                break;
            default:
                throw new RuntimeException("Unknown database type：" + database);
        }
        return sql;
    }


    public static String getToDataSqlTemplate(String dataName, String format, EnumDatabase database) {
        String sql = "";
        switch (database) {
            case MYSQL:
                sql = "str_to_date(" + dataName + ",'" + format + "')";
                break;
            case ORACLE11g:
                sql = String.format("to_date(" + dataName + ",'" + format + "')");
                break;
            default:
                throw new RuntimeException("Unknown database type：" + database);
        }
        return sql;
    }

    public static String getToDataSqlTemplate(String dataName, EnumDatabase database) {
        String sql = "";
        switch (database) {
            case MYSQL:
                sql = "str_to_date(" + dataName + ",'" + database.defaultFormat + "')";
                break;
            case ORACLE11g:
                sql = String.format("to_date(" + dataName + ",'" + database.defaultFormat + "')");
                break;
            default:
                throw new RuntimeException("Unknown database type：" + database);
        }
        return sql;
    }

    public static String genToDateTimeSql(String dataName, EnumDatabase database) {
        String sql = "";
        switch (database) {
            case MYSQL:
                sql = "str_to_date('" + dataName + "','%Y-%m-%d %H:%i:%s')";
                break;
            case ORACLE11g:
                sql = String.format("to_date('" + dataName + "','yyyy-mm-dd hh24:mi:ss')", dataName);
                break;
            default:
                throw new RuntimeException("Unknown database type：" + database);
        }
        return sql;
    }

    public static String genToDateSql(String dataName, EnumDatabase database) {
        String sql = "";
        switch (database) {
            case MYSQL:
                sql = "str_to_date('" + dataName + "','%Y-%m-%d')";
                break;
            case ORACLE11g:
                sql = String.format("to_date('" + dataName + "','yyyy-mm-dd')", dataName);
                break;
            default:
                throw new RuntimeException("Unknown database type：" + database);
        }
        return sql;
    }


    public static String genTimeToCharSql(String dataName, EnumDatabase database) {
        String sql = "";
        switch (database) {
            case MYSQL:
                sql = "date_format(" + dataName + ",'%Y-%m-%d %H:%i:%s')";
                break;
            case ORACLE11g:
                sql = String.format("to_char(" + dataName + ",'yyyy-mm-dd hh24:mi:ss')", dataName);
                break;
            default:
                throw new RuntimeException("Unknown database type：" + database);
        }
        return sql;
    }

    public static String genDateToCharSql(String dataName, EnumDatabase database) {
        String sql = "";
        switch (database) {
            case MYSQL:
                sql = "date_format(" + dataName + ",'%Y-%m-%d')";
                break;
            case ORACLE11g:
                sql = String.format("to_char(" + dataName + ",'yyyy-mm-dd')", dataName);
                break;
            default:
                throw new RuntimeException("Unknown database type：" + database);
        }
        return sql;
    }
}
