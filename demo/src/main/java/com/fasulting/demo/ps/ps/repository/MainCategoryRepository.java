package com.fasulting.demo.ps.ps.repository;

import com.fasulting.demo.entity.MainCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MainCategoryRepository extends JpaRepository<MainCategoryEntity, Long> {

    Optional<MainCategoryEntity> findMainByName(String name);

}
