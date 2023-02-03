package com.fasulting.repository.doctor;

import com.fasulting.entity.doctor.DoctorMainEntity;
import com.fasulting.entity.compositeId.DoctorMainId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DoctorMainRepository extends JpaRepository<DoctorMainEntity, DoctorMainId>, DoctorMainRepositoryCustom {

    @Query("SELECT dm.mainCategory.name " + "FROM DoctorMainEntity dm " + "WHERE dm.doctor.seq = :docSeq")
    List<String> getMainCategoryByDoctorSeq(@Param("docSeq") Long docSeq);
}
