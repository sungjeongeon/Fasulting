package com.fasulting.demo.customer.consulting.db.entity;

import com.fasulting.demo.customer.review.db.entity.Review;
import com.fasulting.demo.customer.user.db.entity.User;
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

    @Column(name = "consulting_confirm")
    private String consultingConfirm;

    @Column(name = "consulting_notification")
    private String consultingNotification;

    // table과 관계 설정
    //    외래키
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

//    @ManyToOne
//    @JoinColumn(name = "sub_category_id")
//    private SubCategory subCategory;
//
//    @ManyToOne
//    @JoinColumn(name = "ps_id")
//    private Ps ps;
}
