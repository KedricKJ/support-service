package com.inntri.support.enums;

public enum Status {

    ACTIVE("ACTIVE","A"),DELETED("DELETED","D");

    private String label;

    private String value;

    Status(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public static Status getEnum(String s) {
        for(Status item:Status.values()) {
            if(item.value.equals(s)) {
                return item;
            }
        }

        return null;

    }

    public String getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }
}
