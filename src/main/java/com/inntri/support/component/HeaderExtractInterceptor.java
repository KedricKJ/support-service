package com.inntri.support.component;

import com.inntri.support.config.TenantIdentifierResolver;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class HeaderExtractInterceptor implements HandlerInterceptor {

  private Long userId = 0L;
  private Long companyId = 0L;
  private String loggedInUser = "inntri@xxxxx.com";

  @Autowired private TenantIdentifierResolver tenantIdentifierResolver;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    System.out.println("request.getPathInfo : {}"+ request.getHeader("secure"));

    //if (request.getHeader("secure") != null && request.getHeader("secure").equals("t")) {
    //if (validator.isSecured.test(request.getHeader("path"))) {
      if (request.getHeader("userId") != null) {
        userId = Long.valueOf(request.getHeader("userId"));
      }
      if (request.getHeader("companyId") != null) {
        companyId = Long.valueOf(request.getHeader("companyId"));
      }
      loggedInUser = request.getHeader("loggedInUser");
      if (request.getHeader("companyId") != null) {
      tenantIdentifierResolver.setCurrentTenant(request.getHeader("companyId"));
      } /*else {
        tenantIdentifierResolver.setCurrentTenant(String.valueOf(16));
      }*/
    return true;
  }

  public void setCurrentTenant(String tenant) {
    tenantIdentifierResolver.setCurrentTenant(tenant);
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getCompanyId() {
    return companyId;
  }

  public void setCompanyId(Long companyId) {
    this.companyId = companyId;
  }

  public String getLoggedInUser() {
    return loggedInUser;
  }

  public void setLoggedInUser(String loggedInUser) {
    this.loggedInUser = loggedInUser;
  }

  public TenantIdentifierResolver getTenantIdentifierResolver() {
    return tenantIdentifierResolver;
  }

  public void setTenantIdentifierResolver(TenantIdentifierResolver tenantIdentifierResolver) {
    this.tenantIdentifierResolver = tenantIdentifierResolver;
  }

}
