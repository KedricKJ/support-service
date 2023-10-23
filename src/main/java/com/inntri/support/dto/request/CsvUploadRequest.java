package com.inntri.support.dto.request;

import lombok.Data;


public abstract class CsvUploadRequest {

    protected String base64FileString;

    protected String originalFileName;

    private String storeId;

    private String storeAddressId;

    private String customerId;

    private String customerAddressId;

    protected String remark;

    public String getBase64FileString() {
        return base64FileString;
    }

    public void setBase64FileString(String base64FileString) {
        this.base64FileString = base64FileString;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreAddressId() {
        return storeAddressId;
    }

    public void setStoreAddressId(String storeAddressId) {
        this.storeAddressId = storeAddressId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerAddressId() {
        return customerAddressId;
    }

    public void setCustomerAddressId(String customerAddressId) {
        this.customerAddressId = customerAddressId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
