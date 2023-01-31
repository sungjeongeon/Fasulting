package com.fasulting.demo.ps.ps.repository;

import com.fasulting.demo.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<DoctorEntity, Long>, DoctorRepositoryCustom {

    List<DoctorEntity> findAllByPsSeq(Long psSeq);
}
