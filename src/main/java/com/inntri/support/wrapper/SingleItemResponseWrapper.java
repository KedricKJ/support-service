package com.inntri.support.wrapper;

import com.inntri.support.enums.RestApiResponseStatus;
import lombok.Data;

/**
 * @author Nuwan
 */

public class SingleItemResponseWrapper<T> extends BaseResponseWrapper {

    private T content;

    //private HttpStatus status;

    public SingleItemResponseWrapper(T object, String status) {
        super(RestApiResponseStatus.OK);
        this.content = object;
        this.status = status;
    }

    public SingleItemResponseWrapper(T object) {
        super(RestApiResponseStatus.OK);
        this.content = object;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }


}
