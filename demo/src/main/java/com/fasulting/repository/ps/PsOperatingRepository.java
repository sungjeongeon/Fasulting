package com.fasulting.repository.ps;

import com.fasulting.entity.calendar.OperatingCalEntity;
import com.fasulting.entity.ps.PsEntity;
import com.fasulting.entity.ps.PsOperatingEntity;
import com.fasulting.entity.compositeId.PsOperatingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

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

    List<PsOperatingEntity> findByPsAndOperatingCal(PsEntity ps, OperatingCalEntity operatingCal);

    @Modifying
    @Transactional
    @Query("DELETE FROM PsOperatingEntity po WHERE po.ps.seq = :psSeq " +
            "AND po.operatingCal.seq IN " +
            "(SELECT oc.seq FROM OperatingCalEntity oc WHERE " +
            "DATE_FORMAT(oc.date, '%Y-%m-%d')" +
            "BETWEEN DATE_FORMAT(:current, '%Y-%m-%d') AND " +
            "DATE_FORMAT(:post, '%Y-%m-%d'))"
            )
    void deleteAllByDateTime(@Param("psSeq") Long psSeq,  @Param("current") LocalDateTime current,  @Param("post") LocalDateTime post);
}