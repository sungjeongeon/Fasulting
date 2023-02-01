package com.fasulting.demo.common.defaultCal.repository;

import com.fasulting.demo.entity.DefaultCalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DefaultCalRepository extends JpaRepository<DefaultCalEntity, Long> {

    Optional<DefaultCalEntity> findByDayOfWeek(Integer dayOfWeek);

}
