package com.inntri.support.dto.request.auth;

import lombok.Data;

@Data
public class AuthoritySuggestionResponse {

    private String id;

    private String name;

    private String code;

    public AuthoritySuggestionResponse(String authority) {
        this.code = authority;
    }
    public AuthoritySuggestionResponse() {
    }

}
