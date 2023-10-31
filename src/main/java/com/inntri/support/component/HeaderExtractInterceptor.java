package com.inntri.support.component;

import com.inntri.support.config.TenantIdentifierResolver;
import com.inntri.support.utils.exceptions.ComplexValidationException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Component
public class HeaderExtractInterceptor implements HandlerInterceptor {

  private Long userId = 0L;
  private Long companyId = 0L;
  private String loggedInUser = "inntri@xxxxx.com";

  @Autowired private TenantIdentifierResolver currentTenantIdentifierResolverImpl;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String secure = request.getHeader("secure");
    System.out.println("request.is secure  : {}"+ secure);

    //if (request.getHeader("secure") != null && request.getHeader("secure").equals("t")) {
    //if (validator.isSecured.test(request.getHeader("path"))) {
    System.out.println("userId : {}"+ request.getHeader("userId"));

      if (request.getHeader("userId") != null) {
        userId = Long.valueOf(request.getHeader("userId"));
      }
    System.out.println("companyId : {}"+ request.getHeader("companyId"));

      if (request.getHeader("companyId") != null) {
        companyId = Long.valueOf(request.getHeader("companyId"));
      }
      loggedInUser = request.getHeader("loggedInUser");
    System.out.println("loggedInUser : {}"+ loggedInUser);

      if (request.getHeader("companyId") != null) {
      currentTenantIdentifierResolverImpl.setCurrentTenant(request.getHeader("companyId"));
      }

    System.out.println("current tenant  : {}"+ currentTenantIdentifierResolverImpl.getCurrentTenant());
      if (currentTenantIdentifierResolverImpl.getCurrentTenant() == null) {
        currentTenantIdentifierResolverImpl.setCurrentTenant("unknown");
      }
    if (secure.equals("true") && Objects.equals(currentTenantIdentifierResolverImpl.getCurrentTenant(), "unknown")) {
      throw new ComplexValidationException("company.id","Current company selection : "+ currentTenantIdentifierResolverImpl.getCurrentTenant()+ " is invalid. Please contact system admin.");
    }
    return true;
  }


  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    currentTenantIdentifierResolverImpl.setCurrentTenant(null);
  }



  public void setCurrentTenant(String tenant) {
    currentTenantIdentifierResolverImpl.setCurrentTenant(tenant);
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



  public TenantIdentifierResolver getCurrentTenantIdentifierResolverImpl() {
    return currentTenantIdentifierResolverImpl;
  }

  public void setCurrentTenantIdentifierResolverImpl(TenantIdentifierResolver currentTenantIdentifierResolverImpl) {
    this.currentTenantIdentifierResolverImpl = currentTenantIdentifierResolverImpl;
  }
}
