package com.rate.common.login.repository;

import com.rate.common.login.entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Integer> {
    Boolean existsByEmail(String email);
    LoginEntity findByEmail(String email);
}