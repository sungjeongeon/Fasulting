package com.fasulting.repository.ps;

import com.fasulting.entity.category.MainCategoryEntity;
import com.fasulting.entity.ps.PsMainSubEntity;
import com.fasulting.entity.category.SubCategoryEntity;
import com.fasulting.entity.compositeId.PsMainId;
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

    @Query("SELECT p.subCategory " + "FROM PsMainSubEntity p " +
            "WHERE p.mainCategory.seq = :main")
    List<SubCategoryEntity> findByMainCategory(@Param("main") Long main);

}
