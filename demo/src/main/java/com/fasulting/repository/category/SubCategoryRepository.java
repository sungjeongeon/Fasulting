package com.fasulting.repository.category;

import com.fasulting.entity.category.MainCategoryEntity;
import com.fasulting.entity.category.SubCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubCategoryRepository extends JpaRepository<SubCategoryEntity, Long> {

    Optional<SubCategoryEntity> findByName(String name);

    List<SubCategoryEntity> findByMainCategory(MainCategoryEntity main);

}
