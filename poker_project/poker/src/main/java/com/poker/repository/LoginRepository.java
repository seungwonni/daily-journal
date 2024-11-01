package com.poker.repository;


import com.poker.entity.LoginEntity;
import com.poker.entity.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, String> {

}
