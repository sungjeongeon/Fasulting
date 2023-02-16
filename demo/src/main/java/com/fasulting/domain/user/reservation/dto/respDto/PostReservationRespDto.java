package com.fasulting.domain.user.reservation.dto.respDto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class PostReservationRespDto{

    private Long reservationSeq;
    private Long psSeq;
    private Long userSeq;
    private String psName;
    private List<String> subCategoryName;
    private int year;
    private int month;
    private int day;
    private int dayOfWeek;
    private int hour;
    private int minute;

}
