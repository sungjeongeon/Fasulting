package com.fasulting.demo.ps.ps.repository;

import com.fasulting.demo.entity.MainCategoryEntity;
import com.fasulting.demo.entity.PsEntity;
import com.fasulting.demo.entity.PsMainEntity;
import com.fasulting.demo.entity.compositeId.PsMainId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PsMainRepository extends JpaRepository<PsMainEntity, PsMainId> {
}
