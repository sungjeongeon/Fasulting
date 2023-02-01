package com.fasulting.demo.ps.psReservation.dto.respDto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PsOperatingRespDto {

    private Integer year;
    private Integer month;
    private Integer day;
    private Integer dayOfWeek;
    private List<String> time;

}
