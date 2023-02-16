package com.fasulting.repository.calendar;

import com.fasulting.entity.calendar.ReservationCalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationCalRepository extends JpaRepository<ReservationCalEntity, Long> {

    Optional<ReservationCalEntity> findByYearAndMonthAndDay(Integer year, Integer month, Integer day);
}
