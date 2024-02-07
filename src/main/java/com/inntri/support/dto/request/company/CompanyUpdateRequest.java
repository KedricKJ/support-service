package com.inntri.support.dto.request.company;

import lombok.Data;

@Data
public class CompanyUpdateRequest {

  private String code;

  private String name;

  private String companyType;

  private String emails;

  private Long preferredLanguageId;

  private Long preferredCurrencyId;

  private Long countryId;

  private boolean companyOnboarded;

  private boolean usersOnboarded;
}
