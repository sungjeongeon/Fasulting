package com.fasulting.demo.ps.ps.repository;


import com.fasulting.demo.entity.PsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PsRepository extends JpaRepository<PsEntity, Long> {

    // 1. email로 Ps 찾기
    Optional<PsEntity> findPsByEmail(@Param("email") String email);


}
