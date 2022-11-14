package com.etester.data.domain.user;

import javax.persistence.Column;
import javax.persistence.Table;

import lombok.Data;


@Data
//@Entity
@Table(name="permissions")
public class Permission {

    @Column(name = "username", length = 50, nullable = false)
    private String username;
    @Column(name = "privilege", length = 50, nullable = false)
    private String privilege;

}
