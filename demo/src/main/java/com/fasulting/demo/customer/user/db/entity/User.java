package com.fasulting.demo.customer.user.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User {


    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_birth")
    private String userBirth;

    @Column(name = "user_gender")
    private String userGender;

    @Column(name = "user_phone")
    private String userPhone;

    @Column(name = "user_validation")
    private String userValidation;

    @Column(name = "user_regist_time")
    private String userRegistTime;

    @Column(name = "user_nation")
    private String userNation;

    @Column(name = "user_nation_code")
    private String userNationCode;

    // 가입 날짜?


    // 다른 테이블과 매핑
    // @ManyToOne
    // @JoinColumn(name = "manager_code", updatable = false, insertable = false)
    // @JsonBackReference
    // private Example example;

}
