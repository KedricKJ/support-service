package com.inntri.support.wrapper;

import com.inntri.support.enums.RestApiResponseStatus;
import lombok.Data;

/**
 * @author Nuwan
 */

public class BaseResponseWrapper {

    public static BaseResponseWrapper OK = new BaseResponseWrapper(RestApiResponseStatus.OK);

    public BaseResponseWrapper(){}

    public BaseResponseWrapper(RestApiResponseStatus restApiResponseStatus) {
        this.status = restApiResponseStatus.getStatus();
        this.statusCode = restApiResponseStatus.getCode();
    }

    public String status;

    public Integer statusCode;

    public static BaseResponseWrapper getOK() {
        return OK;
    }

    public static void setOK(BaseResponseWrapper OK) {
        BaseResponseWrapper.OK = OK;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
