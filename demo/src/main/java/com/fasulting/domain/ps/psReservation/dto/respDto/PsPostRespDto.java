package com.fasulting.domain.ps.psReservation.dto.respDto;

import com.fasulting.common.dto.respDto.PsOperatingRespDto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PsPostRespDto {

    private List<PostReservationRespDto> reservation;
    private List<PsOperatingRespDto> operatingTime;

}
