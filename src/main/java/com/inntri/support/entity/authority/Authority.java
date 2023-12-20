package com.inntri.support.entity.authority;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

/*@Getter
@Setter*/
@Entity
@Table(name = "authority")
public class Authority implements GrantedAuthority {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String code;

    private Boolean visibleInRoleCreation;

    private String path;

    private String method;

    private String basePath;

    public Authority(String code) {
        this.code = code;
    }

    public Authority(Long id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public Authority() {

    }

    @Override
    public String getAuthority() {
        return code;
    }

    @Override
    public String toString() {
        return code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getVisibleInRoleCreation() {
        return visibleInRoleCreation;
    }

    public void setVisibleInRoleCreation(Boolean visibleInRoleCreation) {
        this.visibleInRoleCreation = visibleInRoleCreation;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }
}
