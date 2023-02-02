package com.fasulting.demo.common.consulting.repository;

import com.fasulting.demo.entity.ConsultingEntity;
import com.fasulting.demo.entity.ReservationEntity;
import com.fasulting.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConsultingRepository extends JpaRepository<ConsultingEntity, Long> {

    List<ConsultingEntity> findAllByUser(UserEntity userSeq);

    Optional<ConsultingEntity> findByReservation(ReservationEntity reservation);
}
