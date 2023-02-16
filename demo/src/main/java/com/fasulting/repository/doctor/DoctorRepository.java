package com.fasulting.repository.doctor;

import com.fasulting.entity.doctor.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<DoctorEntity, Long>, DoctorRepositoryCustom {

    List<DoctorEntity> findAllByPsSeq(Long psSeq);
}
