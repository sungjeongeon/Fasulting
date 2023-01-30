package com.fasulting.demo.common.review;

import com.fasulting.demo.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReveiwRepository extends JpaRepository<ReviewEntity, Long> {


}
