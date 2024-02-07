package com.inntri.support.dto.request.company;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
public class CompanyCreateRequest {

  @NotBlank(message = "Company code is required")
  @Length(max = 10, message = "Company code length exceeds.")
  private String code;

  @NotBlank(message = "Company name is required")
  @Length(max = 100, message = "Company name length exceeds.")
  private String name;

  //@NotEmpty(message = "Email is required.")
  //@NotNull(message = "Email is required.")
  private String email;

  private String companyType;

  private String companyCategory;

  private List<UserData> users;

  private Long preferredLanguageId;

  private Long preferredCurrencyId;

  private Long countryId;

  private AddressData address;

  /*initial admin user credentials*/
  @Data
  public static class UserData {

    private String username;

    //private String password;

  }


    @Data
    public static class AddressData  {
      private String address;
      private String area;
      private String city;
      private String code;
      private String addressType;
    }


}
