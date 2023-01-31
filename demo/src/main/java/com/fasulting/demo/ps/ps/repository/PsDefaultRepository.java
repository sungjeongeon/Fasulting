package com.fasulting.demo.ps.ps.repository;

import com.fasulting.demo.entity.PsDefaultEntity;
import com.fasulting.demo.entity.compositeId.PsDefaultId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PsDefaultRepository extends JpaRepository<PsDefaultEntity, PsDefaultId> {

//    @Query("SELECT pd.defaultCal " + "FROM PsDefaultEntity pd " + "WHERE pd.ps.seq = :psSeq")
//    List<DefaultCalEntity> getDefaultCalByPsSeq (@Param("psSeq")Long psSeq);

    List<PsDefaultEntity> findAllByPsSeq(Long psSeq);
}
