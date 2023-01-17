package com.fasulting.demo.customer.review.db.entity;

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
    // 회원 id
    // 병원 id

}
