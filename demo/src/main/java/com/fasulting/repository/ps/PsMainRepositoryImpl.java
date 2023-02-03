package com.fasulting.repository.ps;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static com.fasulting.entity.QPsMainEntity.psMainEntity;


public class PsMainRepositoryImpl implements PsMainRepositoryCustom {

    @Autowired
    private JPAQueryFactory queryFactory;

    @Transactional
    @Override
    public void deleteMainByPs(Long seq) {
        queryFactory
                .delete(psMainEntity)
                .where(psMainEntity.ps.seq.eq(seq))
                .execute();
    }

}
