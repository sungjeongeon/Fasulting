package com.fasulting.repository.calendar;

import com.fasulting.entity.calendar.OperatingCalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OperatingCalRepository extends JpaRepository<OperatingCalEntity, Long> {
    List<OperatingCalEntity> findAllByDayOfWeek(Integer dayOfWeek);

    Optional<OperatingCalEntity> findByYearAndMonthAndDay(int year, int month, int day);

}
