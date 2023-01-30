package com.fasulting.demo.customer.user.repository;

import com.fasulting.demo.customer.user.dto.respDto.FavoriteResp;
import com.fasulting.demo.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Long> {

//    @Query("SELECT f.ps.name, sub.subCategory.seq, sub.subCategory.name " + "FROM FavoriteEntity f " +
//            "LEFT OUTER JOIN PsMainSubEntity sub " + "ON sub.ps.seq = f.ps.seq " +
//            "WHERE f.user.seq = :userSeq")
//    List<Object> test(@Param("userSeq") Long userSeq);

    List<FavoriteEntity> findAllByUserSeq(Long userSeq);

    @Query("SELECT f " + "FROM FavoriteEntity f " +
            "WHERE f.user.seq = :userSeq AND f.ps.seq = :psSeq")
    FavoriteEntity findByUserSeqPsSeq(@Param("userSeq")Long userSeq, @Param("psSeq")Long psSeq);

}
