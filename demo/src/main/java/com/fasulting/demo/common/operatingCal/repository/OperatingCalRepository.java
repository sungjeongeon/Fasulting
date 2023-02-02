package com.fasulting.demo.common.operatingCal.repository;

import com.fasulting.demo.entity.OperatingCalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperatingCalRepository extends JpaRepository<OperatingCalEntity, Long> {
    List<OperatingCalEntity> findAllByDayOfWeek(Integer dayOfWeek);

    OperatingCalEntity findByYearAndMonthAndDay(int year, int month, int day);
}
