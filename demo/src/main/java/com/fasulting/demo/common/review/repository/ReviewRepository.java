package com.fasulting.demo.common.review.repository;

import com.fasulting.demo.entity.ConsultingEntity;
import com.fasulting.demo.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    @Query("SELECT COUNT(r) " + "FROM ReviewEntity r " +
            "WHERE r.ps.seq = :psSeq " +
            "AND ( r.delYn LIKE 'N' OR r.delYn IS NULL )" +
            "AND ( r.decYn LIKE 'N' OR r.decYn IS NULL )")
    int getCountByPsSeq(@Param("psSeq") Long psSeq);

    @Query("SELECT r " + "FROM ReviewEntity r " +
            "WHERE r.ps.seq = :psSeq " +
            "AND ( r.delYn LIKE 'N' OR r.delYn IS NULL )" +
            "AND ( r.decYn LIKE 'N' OR r.decYn IS NULL )")
    List<ReviewEntity> findAllByPsSeq(@Param("psSeq") Long psSeq);

    @Query("SELECT r " + "FROM ReviewEntity r " +
            "WHERE r.ps.seq = :userSeq " +
            "AND ( r.delYn LIKE 'N' OR r.delYn IS NULL )" +
            "AND ( r.decYn LIKE 'N' OR r.decYn IS NULL )")
    List<ReviewEntity> findAllByUserSeq(@Param("userSeq") Long userSeq);

    Optional<ReviewEntity> findByConsulting(ConsultingEntity consulting);


}
