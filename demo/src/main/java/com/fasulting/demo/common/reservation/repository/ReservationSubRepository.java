package com.fasulting.demo.common.reservation.repository;

import com.fasulting.demo.entity.ReservationSubEntity;
import com.fasulting.demo.entity.compositeId.ReservationSubId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationSubRepository extends JpaRepository<ReservationSubEntity, ReservationSubId> {

    @Query("SELECT rs.subCategory.name " + "FROM ReservationSubEntity rs " + "WHERE rs.reservation.seq = :reservationSeq")
    List<String> getSubCategoryNameByReservationSeq(@Param("reservationSeq") Long reservationSeq);

}