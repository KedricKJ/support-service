package com.inntri.support.dto.response.company;

import lombok.Data;

@Data
public class CompanySuggestionResponse {

    private String id;

    private String code;

    private String name;

    private String companyType;

    private String companyCategory;
}
