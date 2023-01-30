package com.fasulting.demo.ps.ps.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import static com.fasulting.demo.entity.QPsMainEntity.psMainEntity;

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
