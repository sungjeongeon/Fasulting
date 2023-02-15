package com.fasulting.repository.ps;

import com.fasulting.entity.ps.PsDefaultEntity;
import com.fasulting.entity.ps.PsEntity;
import com.fasulting.entity.compositeId.PsDefaultId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PsDefaultRepository extends JpaRepository<PsDefaultEntity, PsDefaultId> {

//    @Query("SELECT pd.defaultCal " + "FROM PsDefaultEntity pd " + "WHERE pd.ps.seq = :psSeq")
//    List<DefaultCalEntity> getDefaultCalByPsSeq (@Param("psSeq")Long psSeq);

    List<PsDefaultEntity> findAllByPsSeq(Long psSeq);

    @Transactional
    void deleteAllByPs(PsEntity ps);
}
