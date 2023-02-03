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
    ConsultingEntity findByResrvationSeq(@Param("reservationSeq") Long reservationSeq);

    List<ConsultingEntity> findAllByUser(UserEntity userSeq);

    Optional<ConsultingEntity> findByReservation(ReservationEntity reservation);

    List<ConsultingEntity> findAllByPs(PsEntity psEntity);
}
