package com.fasulting.repository.ps;

import com.fasulting.entity.ps.PsEntity;
import com.fasulting.entity.ps.TotalRatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface TotalRatingRepository extends JpaRepository<TotalRatingEntity, Long> {

    @Query("SELECT t.result " + "FROM TotalRatingEntity t " +
            "WHERE t.ps.seq = :psSeq")
    BigDecimal getResultByPsSeq(@Param("psSeq") Long psSeq);

    Optional<TotalRatingEntity> findByPs(PsEntity ps);
}
