package com.fasulting.domain.user.reservation.dto.respDto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class PostReservationRespDto {

    private Long reservationSeq;
    private String psName;
    private List<String> subCategoryName;
    private String date; // yyyy.MM.dd (요일) 00시
}
