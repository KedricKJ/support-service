package com.inntri.support.component;

import com.inntri.support.config.TenantIdentifierResolver;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class HeaderExtractInterceptor implements HandlerInterceptor {

  private Long userId = 0L;
  private String companyCode;
  private String loggedInUser = "inntri@xxxxx.com";

  @Autowired private TenantIdentifierResolver currentTenantIdentifierResolverImpl;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
      if (request.getHeader("userId") != null) {
        userId = Long.valueOf(request.getHeader("userId"));
        System.out.println("userId : {}"+ request.getHeader("userId"));
      }

      if (request.getHeader("companyCode") != null) {
        companyCode = request.getHeader("companyCode");
      }
    System.out.println("companyCode : {}"+ companyCode);

      loggedInUser = request.getHeader("loggedInUser");
    System.out.println("loggedInUser : {}"+ loggedInUser);

      if (companyCode != null) {
      currentTenantIdentifierResolverImpl.setCurrentTenant(companyCode);
      }

    String authoritiesStr = request.getHeader("authorities");
    System.out.println("authoritiesStr : {}"+ authoritiesStr);

    System.out.println("current tenant  : {}"+ currentTenantIdentifierResolverImpl.getCurrentTenant());
      if (currentTenantIdentifierResolverImpl.getCurrentTenant() == null) {
        currentTenantIdentifierResolverImpl.setCurrentTenant("unknown");
      }
    /*if (Objects.equals(currentTenantIdentifierResolverImpl.getCurrentTenant(), "unknown")) {
      throw new ComplexValidationException("company.id","Current company selection : "+ currentTenantIdentifierResolverImpl.getCurrentTenant()+ " is invalid. Please contact system admin.");
    }*/
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

  public String getLoggedInUser() {
    return loggedInUser;
  }

  public void setLoggedInUser(String loggedInUser) {
    this.loggedInUser = loggedInUser;
  }

  public String getCompanyCode() {
    return companyCode;
  }

  public void setCompanyCode(String companyCode) {
    this.companyCode = companyCode;
  }

  public TenantIdentifierResolver getCurrentTenantIdentifierResolverImpl() {
    return currentTenantIdentifierResolverImpl;
  }

  public void setCurrentTenantIdentifierResolverImpl(TenantIdentifierResolver currentTenantIdentifierResolverImpl) {
    this.currentTenantIdentifierResolverImpl = currentTenantIdentifierResolverImpl;
  }
}
