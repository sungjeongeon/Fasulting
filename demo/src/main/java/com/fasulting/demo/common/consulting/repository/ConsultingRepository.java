package com.fasulting.demo.common.consulting.repository;

import com.fasulting.demo.entity.ConsultingEntity;
import com.fasulting.demo.entity.PsEntity;
import com.fasulting.demo.entity.ReservationEntity;
import com.fasulting.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ConsultingRepository extends JpaRepository<ConsultingEntity, Long> {
    @Query("select c from ConsultingEntity c where c.reservation.seq = :reservationSeq")
    ConsultingEntity findByResrvationSeq(@Param("reservationSeq") Long reservationSeq);

    List<ConsultingEntity> findAllByUser(UserEntity userSeq);

    Optional<ConsultingEntity> findByReservation(ReservationEntity reservation);

    List<ConsultingEntity> findAllByPs(PsEntity psEntity);
}
