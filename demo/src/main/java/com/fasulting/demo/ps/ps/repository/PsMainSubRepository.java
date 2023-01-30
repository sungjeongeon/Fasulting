package com.fasulting.demo.ps.ps.repository;

import com.fasulting.demo.entity.PsMainSubEntity;
import com.fasulting.demo.entity.compositeId.PsMainSubId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PsMainSubRepository extends JpaRepository<PsMainSubEntity, PsMainSubId>, PsMainSubRepositoryCustom {
}
