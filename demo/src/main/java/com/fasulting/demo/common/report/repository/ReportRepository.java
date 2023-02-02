package com.fasulting.demo.common.report.repository;

import com.fasulting.demo.entity.ConsultingEntity;
import com.fasulting.demo.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<ReportEntity, Long> {

    Optional<ReportEntity> findByConsulting(ConsultingEntity consulting);
}
