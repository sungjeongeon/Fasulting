package com.fasulting.demo.common.reservation.repository;

import com.fasulting.demo.entity.ReservationCalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationCalRepository extends JpaRepository<ReservationCalEntity, Long> {

    Optional<ReservationCalEntity> findByYearAndMonthAndDay(Integer year, Integer month, Integer day);
}
