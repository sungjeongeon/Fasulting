package com.fasulting.repository.ps;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static com.fasulting.entity.ps.QPsMainSubEntity.psMainSubEntity;


public class PsMainSubRepositoryImpl implements PsMainSubRepositoryCustom {


    @Autowired
    private JPAQueryFactory queryFactory;

    @Transactional
    @Override
    public void deleteMainSubByPs(Long seq) {
        queryFactory
                .delete(psMainSubEntity)
                .where(psMainSubEntity.ps.seq.eq(seq))
                .execute();
    }

}
