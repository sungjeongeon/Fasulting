package com.fasulting.demo.ps.psReservation.dto.respDto;

import com.fasulting.demo.common.psOperating.dto.respDto.PsOperatingRespDto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PsPostRespDto {

    private List<PostReservationRespDto> reservation;
    private List<PsOperatingRespDto> operatingTime;

}
