package com.inntri.support.dto.request.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@ToString
public class AuthenticateResponse {

  @JsonProperty("access_token")
  private String access_token;
  @JsonProperty("refresh_token")
  private String refresh_token;

  //private List<RoleSuggestionResponse> roles;

  private String username;

  private Long userId;

  private String companyCode;

  private List<AuthoritySuggestionResponse>  authorities;

  private Boolean mfaEnabled;

  private String secretImageUri;

  private String message;

  private int mode;

}
