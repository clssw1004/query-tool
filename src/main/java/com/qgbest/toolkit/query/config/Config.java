package com.qgbest.toolkit.query.config;

import com.qgbest.toolkit.query.enumeration.EnumDatabase;

public class Config {
    private static Config globalConfig = new Config();

    public static Config getConfig() {
        return globalConfig;
    }

    public Config() {

    }

    public Config setDatebase(String datebase) {
        this.datebase = EnumDatabase.getDatabaseByName(datebase);
        return this;
    }

    public Config setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
        return this;
    }

    public Config setIgnoreNullCondition(boolean ignoreNullCondition) {
        this.ignoreNullCondition = ignoreNullCondition;
        return this;
    }

    public EnumDatabase datebase = EnumDatabase.ORACLE11g;
    public String dateFormat = "yyyy-mm-dd HH:mm:ss";
    public boolean ignoreNullCondition = false;
}
