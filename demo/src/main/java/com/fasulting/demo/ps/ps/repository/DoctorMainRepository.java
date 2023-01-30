package com.fasulting.demo.ps.ps.repository;

import com.fasulting.demo.entity.DoctorMainEntity;
import com.fasulting.demo.entity.compositeId.DoctorMainId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorMainRepository extends JpaRepository<DoctorMainEntity, DoctorMainId> {
}
