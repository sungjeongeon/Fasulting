package com.fasulting.demo.ps.psReservation.dto.respDto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PostReservationRespDto {

    List<PsOperatingRespDto> operatingTime;
    List<ReservationRespDto> reservation;

}
