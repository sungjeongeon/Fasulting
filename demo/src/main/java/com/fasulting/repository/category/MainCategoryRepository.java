package com.fasulting.repository.category;

import com.fasulting.entity.category.MainCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MainCategoryRepository extends JpaRepository<MainCategoryEntity, Long> {

    Optional<MainCategoryEntity> findMainByName(String name);

}
