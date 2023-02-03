package com.fasulting.repository.doctor;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static com.fasulting.entity.QDoctorMainEntity.doctorMainEntity;

public class DoctorMainRepositoryImpl implements DoctorMainRepositoryCustom {

    @Autowired
    private JPAQueryFactory queryFactory;

    @Transactional
    @Override
    public void deleteMainByDoctor(Long seq) {
        queryFactory
                .delete(doctorMainEntity)
                .where(doctorMainEntity.doctor.seq.eq(seq))
                .execute();
    }

}
