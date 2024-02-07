package com.inntri.support.dto.request.company;

import lombok.Data;


@Data
public class CompanyCreateUserRequest {

  private UserData user;

  /*initial admin user credentials*/
  @Data
  public static class UserData {

    private String username;

    private String password;

  }
}
