package com.fasulting.repository.doctor;

import com.fasulting.entity.compositeId.DoctorMainId;
import com.fasulting.entity.doctor.DoctorMainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DoctorMainRepository extends JpaRepository<DoctorMainEntity, DoctorMainId>, DoctorMainRepositoryCustom {

    @Query("SELECT dm.mainCategory.name " + "FROM DoctorMainEntity dm " + "WHERE dm.doctor.seq = :docSeq")
    String getMainCategoryByDoctorSeq(@Param("docSeq") Long docSeq);
}
