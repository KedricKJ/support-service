package com.inntri.support.wrapper;

import com.inntri.support.enums.RestApiResponseStatus;

import java.util.List;

/**
 * @author Nuwan
 */
public class ListResponseWrapper<T> extends BaseResponseWrapper {

    private List<T> content;

    public ListResponseWrapper(List<T> content) {
        super(RestApiResponseStatus.OK);
        this.content = content;
    }

    public List<T> getContent() {
        return this.content;
    }

    public void setContent(final List<T> content) {
        this.content = content;
    }





}
