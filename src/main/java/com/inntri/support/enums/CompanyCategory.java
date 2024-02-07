package com.inntri.support.enums;

public enum CompanyCategory {

    /*Customer == Consignee & Supplier == Shipper */
    CONSIGNEE("Consignee", "CONS"),
    RM_SUPPLIER("Material Supplier", "RMS") ,
    SHIPPER("Shipper", "SHI"),
    VEHICLE_SUPPLIER("Vehicle Supplier", "VES"),
    OTHER("Other", "OTH");

    private String label;
    private String value;

    CompanyCategory(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public static CompanyCategory getEnum(String s) {
        for (CompanyCategory value : CompanyCategory.values()) {
            if (value.value.equals(s)) {
                return value;
            }
        }
        return null;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
