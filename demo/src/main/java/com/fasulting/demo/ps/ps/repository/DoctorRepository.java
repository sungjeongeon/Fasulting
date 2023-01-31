package com.fasulting.demo.ps.ps.repository;

import com.fasulting.demo.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<DoctorEntity, Long>, DoctorRepositoryCustom {
}
