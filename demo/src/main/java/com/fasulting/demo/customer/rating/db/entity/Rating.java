package com.fasulting.demo.customer.rating.db.entity;

import com.fasulting.demo.customer.consulting.db.entity.Consulting;
import com.fasulting.demo.customer.user.db.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private int ratingId;

    @Column(name = "rating_point")
    private BigDecimal ratingPoint;

    // 외래키
//    @ManyToOne
//    @JoinColumn(name = "total_rating_id")
//    private  TotalRating totalRating;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "consulting_id")
    private Consulting consulting;

}
