package com.fasulting.demo.ps.ps.repository;

import com.fasulting.demo.entity.MainCategoryEntity;
import com.fasulting.demo.entity.PsEntity;
import com.fasulting.demo.entity.PsMainEntity;
import com.fasulting.demo.entity.compositeId.PsMainId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PsMainRepository extends JpaRepository<PsMainEntity, PsMainId>, PsMainRepositoryCustom {

    @Query("SELECT  pm.ps " + "FROM PsMainEntity pm " +
            "WHERE pm.mainCategory.seq = :mainSeq " +
            "AND ( pm.ps.delYn LIKE 'N' OR pm.ps.delYn IS NULL )" +
            "AND ( pm.ps.confirmYn LIKE 'N' OR pm.ps.confirmYn IS NULL )")
    List<PsEntity> findPsByMainSeq(@Param("mainSeq") Long mainSeq);

    @Query("SELECT  pm.mainCategory " + "FROM PsMainEntity pm " + "WHERE pm.ps.seq = :psSeq" )
    List<MainCategoryEntity> getMainByPsSeq(@Param("psSeq") Long psSeq);

    @Query("SELECT pm.mainCategory.name FROM PsMainEntity  pm WHERE pm.ps.seq = :psSeq")
    List<String> getMainNameByPsSeq(@Param("psSeq") Long psSeq);

}
