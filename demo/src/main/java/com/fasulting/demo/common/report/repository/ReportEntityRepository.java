package com.fasulting.demo.common.report.repository;

import com.fasulting.demo.entity.ConsultingEntity;
import com.fasulting.demo.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportEntityRepository extends JpaRepository<ReportEntity, Long> {
    ReportEntity findByConsulting(ConsultingEntity consulting);
}