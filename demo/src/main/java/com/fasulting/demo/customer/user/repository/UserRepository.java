package com.fasulting.demo.customer.user.repository;

import com.fasulting.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // 1. email로 User 찾기
    // jpql
    // CRUD
    Optional<UserEntity> findUserByEmail(@Param("email") String email);


    @Query("SELECT u from UserEntity u WHERE u.role.authority = 'admin' and u.email = :email and u.password = :password")
    Optional<UserEntity> findAdminByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    @Query("SELECT u from UserEntity u WHERE u.role.authority = 'user' and u.email = :email and u.password = :password")
    Optional<UserEntity> findUserByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}
