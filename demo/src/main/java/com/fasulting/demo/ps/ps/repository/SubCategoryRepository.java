package com.fasulting.demo.ps.ps.repository;

import com.fasulting.demo.entity.MainCategoryEntity;
import com.fasulting.demo.entity.SubCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubCategoryRepository extends JpaRepository<SubCategoryEntity, Long> {

    Optional<SubCategoryEntity> findMainByName(String name);

}
