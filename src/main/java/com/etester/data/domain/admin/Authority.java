package com.etester.data.domain.admin;

import javax.persistence.Column;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
//@Entity
@Table(name="authorities")
public class Authority implements GrantedAuthority {

    @Column(name = "username", length = 50, nullable = false)
    private String username;
    @Column(name = "authority", length = 50, nullable = false)
    private String authority;

}
