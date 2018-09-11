package com.qgbest.toolkit.query.enumeration;

public enum EnumDatabase {

    ORACLE11g("ORACLE11G", "yyyy-mm-dd hh24:mi:ss"),
    MYSQL("MYSQL", "%Y-%m-%d %H:%i:%s");

    public String name;
    public String defaultFormat;

    EnumDatabase(String name, String defaultFormat) {
        this.name = name;
        this.defaultFormat = defaultFormat;
    }


    public static EnumDatabase getDatabaseByName(String databaseName) {
        for (EnumDatabase database : values()) {
            if (database.name.equals(databaseName.toUpperCase())) {
                return database;
            }
        }
        return null;
    }
}
