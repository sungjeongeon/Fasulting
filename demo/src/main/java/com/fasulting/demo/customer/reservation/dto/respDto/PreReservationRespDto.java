package com.fasulting.demo.customer.reservation.dto.respDto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class PreReservationRespDto {

    private Long consultingSeq;
    private String psName;
    private String estimate;
    private List<String> subCategoryName;
    private String date; // yyyy.MM.dd (요일) 00시
    private boolean isReviewed;
    private boolean isReported;

}
