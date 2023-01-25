package com.fasulting.demo.customer.user.repository;

import com.fasulting.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // 1. Email로 user_id 찾기
    // jpql
    // CRUD
    Optional<UserEntity> findUserByEmail(@Param("email") String email);


}
