package com.fasulting.repository.doctor;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static com.fasulting.entity.doctor.QDoctorEntity.doctorEntity;


public class DoctorRepositoryImpl implements DoctorRepositoryCustom {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Transactional
    @Override
    public void deleteDoctorByPs(Long seq) {
        jpaQueryFactory.delete(doctorEntity)
                .where(doctorEntity.ps.seq.eq(seq))
                .execute();
    }
}
