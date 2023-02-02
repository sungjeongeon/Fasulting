package com.fasulting.demo.common.reservation.repository;

import com.fasulting.demo.entity.PsEntity;
import com.fasulting.demo.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    @Query("select r from ReservationEntity r where r.ps.seq = :psSeq " +
            "and CONCAT(DATE_FORMAT(r.reservationCal.date, '%Y-%m-%d'), ' ', lpad(r.time.startHour, '2', '0'), ':', lpad(r.time.startMin, '2', '0')) >= " +
            "DATE_FORMAT(:current, '%Y-%m-%d %H:%i') and ( r.delYn LIKE 'N' OR r.delYn IS NULL )")
    List<ReservationEntity> getPostByPs(@Param("psSeq") Long psSeq, @Param("current") LocalDateTime current);


    @Query("select r from ReservationEntity r where r.ps.seq = :psSeq " +
            "and CONCAT(DATE_FORMAT(r.reservationCal.date, '%Y-%m-%d'), ' ', lpad(r.time.startHour, '2', '0'), ':', lpad(r.time.startMin, '2', '0')) < " +
            "DATE_FORMAT(:current, '%Y-%m-%d %H:%i') and ( r.delYn LIKE 'N' OR r.delYn IS NULL )")
    List<ReservationEntity> getPreByPs(@Param("psSeq") Long psSeq, @Param("current") LocalDateTime current);

    @Query("select r from ReservationEntity r where r.user.seq = :userSeq " +
            "and ( r.delYn LIKE 'N' OR r.delYn IS NULL )")
    List<ReservationEntity> findAllByUserSeq(@Param("userSeq") Long userSeq);


    List<ReservationEntity> findAllByPs(PsEntity psEntity);
}
