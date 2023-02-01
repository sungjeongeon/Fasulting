package com.fasulting.demo.common.time.repository;

import com.fasulting.demo.entity.TimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeRepository extends JpaRepository<TimeEntity, Long> {
}
