package com.fasulting.demo.customer.reservation.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// 논의 필요한 Entity

@Entity
@Getter
@Setter
@Table(name = "reservation")
public class Reservation {

    @Id
    @Column(name = "reservation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reservationId;

    // 외래키
    // 상담id
    // 달력id
    // 회원id
    // 병원id

}
