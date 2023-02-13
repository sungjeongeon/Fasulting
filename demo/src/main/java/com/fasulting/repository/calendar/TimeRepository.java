package com.fasulting.repository.calendar;

import com.fasulting.entity.calendar.TimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TimeRepository extends JpaRepository<TimeEntity, Long> {
    Optional<TimeEntity> findByNum(Integer num);

    Optional<TimeEntity> findByStartHourAndStartMin(Integer startHour, Integer startMin);
}
