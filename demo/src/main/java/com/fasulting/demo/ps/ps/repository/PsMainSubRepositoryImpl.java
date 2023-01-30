package com.fasulting.demo.ps.ps.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static com.fasulting.demo.entity.QPsMainSubEntity.psMainSubEntity;

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
