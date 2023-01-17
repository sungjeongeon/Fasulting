package com.fasulting.demo.customer.consulting.db.entity;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "consulting")
public class Consulting {

    @Id
    @Column(name = "consulting_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int consultingId;

    @Column(name = "ps_id")
    private int psId;

//    외래키
//    @Column(name = "user_id")
//    private int userId;
//
//    @Column(name = "review_id")
//    private int reviewId;
//
//    @Column(name = "sub_category_id")
//    private int subCategoryId;

    @Column(name = "consulting_confirm")
    private String consultingConfirm;

    @Column(name = "consulting_notification")
    private String consultingNotification;

    // table과 관계 설정

}
