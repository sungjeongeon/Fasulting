package com.fasulting.demo.ps.ps.repository;

import com.fasulting.demo.entity.PsEntity;
import com.fasulting.demo.entity.PsMainEntity;
import com.fasulting.demo.entity.compositeId.PsMainId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PsMainRepository extends JpaRepository<PsMainEntity, PsMainId>, PsMainRepositoryCustom {

    @Query("SELECT  pm.ps " + "FROM PsMainEntity pm " + "WHERE pm.mainCategory.seq = :mainSeq" )
    List<PsEntity> findPsByMainSeq(@Param("mainSeq") Long mainSeq);

}
