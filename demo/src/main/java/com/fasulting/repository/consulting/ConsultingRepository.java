package com.fasulting.repository.consulting;

import com.fasulting.entity.consulting.ConsultingEntity;
import com.fasulting.entity.ps.PsEntity;
import com.fasulting.entity.reservation.ReservationEntity;
import com.fasulting.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ConsultingRepository extends JpaRepository<ConsultingEntity, Long> {
    @Query("select c from ConsultingEntity c where c.reservation.seq = :reservationSeq")
    ConsultingEntity findByReservationSeq(@Param("reservationSeq") Long reservationSeq);

    List<ConsultingEntity> findAllByUser(UserEntity userSeq);

    @Query("SELECT c " + "FROM ConsultingEntity c " + "WHERE c.reservation.user.seq = :userSeq " +
            "ORDER BY c.reservation.reservationCal.date, c.reservation.time.startHour, c.reservation.time.startMin DESC")
    List<ConsultingEntity> getAllByUserSeq(@Param("userSeq") Long userSeq);

    Optional<ConsultingEntity> findByReservation(ReservationEntity reservation);

    List<ConsultingEntity> findAllByPs(PsEntity psEntity);

    @Query("SELECT c " + "FROM ConsultingEntity c " + "WHERE c.reservation.ps.seq = :psSeq " +
            "ORDER BY c.reservation.reservationCal.date, c.reservation.time.startHour, c.reservation.time.startMin DESC")
    List<ConsultingEntity> getAllByPsSeq(@Param("psSeq") Long psSeq);
}
