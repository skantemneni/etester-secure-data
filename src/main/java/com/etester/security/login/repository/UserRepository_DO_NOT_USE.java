package com.etester.security.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etester.security.login.models.LoginUser;

@Repository
public interface UserRepository_DO_NOT_USE extends JpaRepository<LoginUser, Long> {
  Optional<LoginUser> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmailAddress(String email_address);
}