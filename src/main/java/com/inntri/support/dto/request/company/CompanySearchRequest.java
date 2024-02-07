package com.inntri.support.dto.request.company;


import com.inntri.support.dto.request.BaseSearchRequest;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CompanySearchRequest extends BaseSearchRequest {

  private String id;

  private String status;

  private String name;

  private String companyType;

  private String companyCategory;

  private String sortProperty = "name";

  @Length(max = 100, message = "companySearchRequest.keyword.lengthExceeds")
  private String keyword;

  @Pattern(regexp = "name", flags = Pattern.Flag.CASE_INSENSITIVE,
    message = "{CustomerSearchRequest.sortProperty.invalid}")
  @Override
  public String getSortProperty() {
    return sortProperty;
  }
}
