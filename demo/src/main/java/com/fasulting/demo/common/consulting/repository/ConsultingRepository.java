package com.fasulting.demo.common.consulting.repository;

import com.fasulting.demo.entity.ConsultingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConsultingRepository extends JpaRepository<ConsultingEntity, Long> {

    @Query("select c from ConsultingEntity c where c.reservation.seq = :reservationSeq")
    ConsultingEntity findByResrvationSeq(@Param("reservationSeq") Long reservationSeq);
}
