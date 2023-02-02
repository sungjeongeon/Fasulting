package com.fasulting.demo.common.psOperating.repository;

import com.fasulting.demo.entity.OperatingCalEntity;
import com.fasulting.demo.entity.PsEntity;
import com.fasulting.demo.entity.PsOperatingEntity;
import com.fasulting.demo.entity.compositeId.PsOperatingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PsOperatingRepository extends JpaRepository<PsOperatingEntity, PsOperatingId> {
    List<PsOperatingEntity> findAllByPs(PsEntity ps);

    void deleteAllByPs(PsEntity ps);

    List<PsOperatingEntity> findByPsAndOperatingCal(PsEntity ps, OperatingCalEntity operatingCal);
}