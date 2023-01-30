package com.fasulting.demo.common.rating;

import com.fasulting.demo.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<RatingRepository, Long> {


}
