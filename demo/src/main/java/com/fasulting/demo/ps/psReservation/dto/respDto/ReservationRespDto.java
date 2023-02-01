package com.fasulting.demo.ps.psReservation.dto.respDto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ReservationRespDto {

    private Long reservationSeq;
    private String reservationDate;
    private String reservationTime;
    private String userName;

    private List<String> subCategoryName;

}
