package com.fasulting.demo.common.psOperating.repository;

import com.fasulting.demo.entity.OperatingCalEntity;
import com.fasulting.demo.entity.PsEntity;
import com.fasulting.demo.entity.PsOperatingEntity;
import com.fasulting.demo.entity.compositeId.PsOperatingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PsOperatingRepository extends JpaRepository<PsOperatingEntity, PsOperatingId> {
    List<PsOperatingEntity> findAllByPs(PsEntity ps);

    @Query("SELECT po FROM PsOperatingEntity po WHERE po.ps.seq = :psSeq " +
            "AND DATE_FORMAT(po.operatingCal.date, '%Y-%m-%d')" +
            "BETWEEN DATE_FORMAT(:current, '%Y-%m-%d') AND " +
            "DATE_FORMAT(:post, '%Y-%m-%d')"
            )
    List<PsOperatingEntity> getByPsSeqAndCurrent(@Param("psSeq") Long psSeq, @Param("current") LocalDateTime current, @Param("post") LocalDateTime post);


    void deleteAllByPs(PsEntity ps);
}