package com.inntri.support.wrapper;

import com.inntri.support.enums.RestApiResponseStatus;

public class ExceptionResponseWrapper extends BaseResponseWrapper {
    public ExceptionResponseWrapper(String message) {
        super(RestApiResponseStatus.NOT_FOUND);
        this.message=message;
    }
}
