package com.fasulting.demo.customer.user.db.repository;

import com.fasulting.demo.customer.user.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // 1. Email로 user_id 찾기
    Optional<User> findUserByUserEmail(@Param("userEmail") String userEmail);


}
