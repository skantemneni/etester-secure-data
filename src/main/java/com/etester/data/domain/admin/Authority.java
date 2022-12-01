package com.etester.data.domain.admin;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
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
