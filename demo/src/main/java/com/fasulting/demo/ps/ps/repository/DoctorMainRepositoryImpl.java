package com.fasulting.demo.ps.ps.repository;

import com.fasulting.demo.entity.QDoctorEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static com.fasulting.demo.entity.QDoctorEntity.doctorEntity;
import static com.fasulting.demo.entity.QDoctorMainEntity.doctorMainEntity;
import static com.fasulting.demo.entity.QPsMainSubEntity.psMainSubEntity;

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
