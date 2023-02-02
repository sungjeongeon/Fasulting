package com.fasulting.demo.common.time.repository;

import com.fasulting.demo.entity.TimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TimeRepository extends JpaRepository<TimeEntity, Long> {
    Optional<TimeEntity> findByNum(Integer num);

}
