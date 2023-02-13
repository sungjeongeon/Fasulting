package com.fasulting.repository.reservation;

import com.fasulting.entity.category.SubCategoryEntity;
import com.fasulting.entity.reservation.ReservationEntity;
import com.fasulting.entity.reservation.ReservationSubEntity;
import com.fasulting.entity.compositeId.ReservationSubId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationSubRepository extends JpaRepository<ReservationSubEntity, ReservationSubId> {

    @Query("SELECT rs.subCategory.name " + "FROM ReservationSubEntity rs " + "WHERE rs.reservation.seq = :reservationSeq")
    List<String> getSubCategoryNameByReservationSeq(@Param("reservationSeq") Long reservationSeq);

//    List<ReservationSubEntity> findAllByReservation(ReservationEntity reservation);

    @Query("SELECT rs.subCategory " + "FROM ReservationSubEntity rs " + "WHERE rs.reservation.seq = :reservationSeq")
    List<SubCategoryEntity> getAllByReservation(@Param("reservationSeq") Long reservationSeq);
}