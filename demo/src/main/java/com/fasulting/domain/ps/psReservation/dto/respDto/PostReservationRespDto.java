package com.fasulting.domain.ps.psReservation.dto.respDto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class PostReservationRespDto {

    private Long reservationSeq;
    private Long psSeq;
    private Long userSeq;
    private String title; // useName
    private List<String> subCategoryName;
    private String reservationDateStart; // yyyy-MM-ddTHH:mm
    private String reservationDateEnd; // yyyy-MM-ddTHH:mm
    private String dayOfWeek;

}
