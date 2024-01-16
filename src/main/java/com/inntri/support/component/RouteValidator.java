package com.inntri.support.component;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

  public static final List<String> openApiEndpoints= List.of("/eureka","/identity/api/v1/auth/authenticate",
          "/identity/api/v1/auth/refreshToken","/identity/api/v1/auth/validate","/identity/api/companies/register",
          "/transport/api/versions","/transport/swagger-ui","/transport/v3",
          "/pricing/api/versions","/pricing/api/swagger-ui","/pricing/api/v3",
          "/hrms/api/swagger-ui","/hrms/api/v3","/identity/api/swagger-ui","/identity/api/v3");
  public Predicate<String> isSecured = serverHttpRequest ->
    openApiEndpoints.stream().noneMatch(uri-> serverHttpRequest.startsWith(uri));

}
