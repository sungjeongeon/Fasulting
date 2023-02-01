package com.fasulting.demo.common.psOperating.repository;

import com.fasulting.demo.entity.PsEntity;
import com.fasulting.demo.entity.PsOperatingEntity;
import com.fasulting.demo.entity.compositeId.PsOperatingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PsOperatingRepository extends JpaRepository<PsOperatingEntity, PsOperatingId> {
    List<PsOperatingEntity> findAllByPs(PsEntity ps);

    void deleteAllByPs(PsEntity ps);

    @Query("select r from ReservationEntity r where r.ps.seq = :psSeq " +
            "and CONCAT(DATE_FORMAT(r.reservationCal.date, '%Y-%m-%d'), ' ', lpad(r.time.startHour, '2', '0'), ':', lpad(r.time.startMin, '2', '0')) >= " +
            "DATE_FORMAT(:current, '%Y-%m-%d %H:%i')")
    List<PsOperatingEntity> find2WeeksLaterByPsSeq(@Param("psSeq") Long psSeq, @Param("current") LocalDateTime current);
}