package com.fasulting.demo.common.review.repository;

import com.fasulting.demo.entity.ConsultingEntity;
import com.fasulting.demo.entity.ReviewEntity;
import com.fasulting.demo.entity.compositeId.PsDefaultId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    @Query("SELECT COUNT(r) " + "FROM ReviewEntity r " +
            "WHERE r.ps.seq = :psSeq")
    int getCountByPsSeq(@Param("psSeq") Long psSeq);

    List<ReviewEntity> findAllByPsSeq(Long psSeq);

    List<ReviewEntity> findAllByUserSeq(Long userSeq);

    Optional<ReviewEntity> findByConsulting(ConsultingEntity consulting);


}
