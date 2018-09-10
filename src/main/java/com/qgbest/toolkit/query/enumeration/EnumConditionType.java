package com.qgbest.toolkit.query.enumeration;

public enum EnumConditionType {
    STRING("str"),
    NUMBER("num"),
    DATE("dt");

    private String type;

    EnumConditionType(String type) {
        this.type = type;
    }

    public static EnumConditionType getType(String type) {
        for (EnumConditionType t : values()) {
            if (t.type.equals(type)) {
                return t;
            }
        }
        return STRING;
    }
}
