package com.fasulting.demo.customer.review.db.entity;

import com.fasulting.demo.customer.user.db.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "review")
public class Review {

    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private int reviewId;

    @Column(name = "review_content")
    private String reviewContent;

    @Column(name = "review_time")
    private String reviewTime;

    @Column(name = "review_isAccused")
    private String reviewIsAccused;

    // 외래키
    // 회원 id - 단방향
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // 병원 id
//    @ManyToOne
//    @JoinColumn(name = "ps_id")
//    private Ps ps;

}
