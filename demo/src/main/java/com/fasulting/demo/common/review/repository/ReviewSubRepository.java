package com.fasulting.demo.common.review.repository;

import com.fasulting.demo.entity.ReviewEntity;
import com.fasulting.demo.entity.ReviewSubEntity;
import com.fasulting.demo.entity.compositeId.ReviewSubId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewSubRepository extends JpaRepository<ReviewSubEntity, ReviewSubId> {

    @Query("SELECT rs.subCategory.name " + "FROM ReviewSubEntity rs " + "WHERE rs.review.seq = :reviewSeq")
    List<String> getSubCategoryByDoctorSeq(@Param("reviewSeq") Long reviewSeq);


}
