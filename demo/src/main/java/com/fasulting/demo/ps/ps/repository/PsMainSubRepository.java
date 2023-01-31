package com.fasulting.demo.ps.ps.repository;

import com.fasulting.demo.entity.PsMainSubEntity;
import com.fasulting.demo.entity.compositeId.PsMainSubId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PsMainSubRepository extends JpaRepository<PsMainSubEntity, Long> {

    @Query("SELECT p.subCategory.name " + "FROM PsMainSubEntity p " +
            "WHERE p.ps.seq = :psSeq")
    List<String> getSubNameByPsSeq(Long psSeq);

    List<PsMainSubEntity> findAllByPsSeq(Long psSeq);
}
