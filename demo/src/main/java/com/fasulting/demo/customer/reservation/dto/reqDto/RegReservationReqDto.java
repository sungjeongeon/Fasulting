package com.fasulting.demo.customer.reservation.dto.reqDto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class RegReservationReqDto {

    private Long userSeq;
    private Long psSeq;
    private int year;
    private int month;
    private  int day;
    private int dayOfWeek;
    private int time;

    private List<Long> subCategory;
}
