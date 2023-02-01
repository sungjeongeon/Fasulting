package com.fasulting.demo.ps.ps.repository;

import com.fasulting.demo.entity.PsMainSubEntity;
import com.fasulting.demo.entity.SubCategoryEntity;
import com.fasulting.demo.entity.compositeId.PsMainId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PsMainSubRepository extends JpaRepository<PsMainSubEntity, PsMainId>, PsMainSubRepositoryCustom {

    @Query("SELECT p.subCategory.name " + "FROM PsMainSubEntity p " +
            "WHERE p.ps.seq = :psSeq")
    List<String> getSubNameByPsSeq(@Param("psSeq") Long psSeq);

    @Query("SELECT  p.subCategory " + "FROM PsMainSubEntity p " + "WHERE p.ps.seq = :psSeq" )
    List<SubCategoryEntity> getSubByPsSeq(@Param("psSeq") Long psSeq);

    List<PsMainSubEntity> findAllByPsSeq(Long psSeq);

}
