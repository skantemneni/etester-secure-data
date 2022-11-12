package com.etester.security.login.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {
  @Id	
  @Enumerated(EnumType.STRING)
  @Column(length = 50)
  private ERole authority;

  public Role() {

  }

  public Role(ERole authority) {
    this.authority = authority;
  }

  public ERole getAuthority() {
    return authority;
  }

  public void setAuthority(ERole authority) {
    this.authority = authority;
  }
}