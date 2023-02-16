package com.fasulting.repository.ps;


import com.fasulting.entity.ps.PsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PsRepository extends JpaRepository<PsEntity, Long> {

    // 1. email로 Ps 찾기
    Optional<PsEntity> findPsByEmail(@Param("email") String email);


    List<PsEntity> findAllByConfirmYn(String confirmYn);

    Optional<PsEntity> findByEmailAndPassword(String email, String password);
}
