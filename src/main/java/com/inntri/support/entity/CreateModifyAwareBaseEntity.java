package com.inntri.support.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.TenantId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Nuwan
 */
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class CreateModifyAwareBaseEntity {

  @Column(name = "created_by")
  private Long createdBy = null;

  @LastModifiedBy
  @Column(name = "last_modified_by")
  private Long lastModifiedBy = null;

  private LocalDate createdDate = LocalDate.now();

  @Column(name = "created_at")
  @CreatedDate
  private LocalDateTime createdAt;

  @Column(name = "last_modified_at")
  @LastModifiedDate
  private LocalDateTime lastModifiedAt;

  @TenantId private String tenant;

  public Long getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Long createdBy) {
    this.createdBy = createdBy;
  }

  public Long getLastModifiedBy() {
    return lastModifiedBy;
  }

  public void setLastModifiedBy(Long lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }

  public String getTenant() {
    return tenant;
  }

  public void setTenant(String tenant) {
    this.tenant = tenant;
  }
}
