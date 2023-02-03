package com.fasulting.domain.ps.psReservation.service;

import com.fasulting.domain.ps.psReservation.dto.reqDto.ReservationReqDto;
import com.fasulting.domain.ps.psReservation.dto.respDto.PreDetailRespDto;
import com.fasulting.domain.ps.psReservation.dto.respDto.PreReservationRespDto;
import com.fasulting.domain.ps.psReservation.dto.respDto.PsPostRespDto;

import java.time.LocalDateTime;
import java.util.List;

public interface PsReservationService {

    boolean cancelReservation(ReservationReqDto reservationReqDto);
    PsPostRespDto getPostReservationList(Long psSeq, LocalDateTime current);

    List<PreReservationRespDto> getPreReservationList(Long psSeq, LocalDateTime current);

    PreDetailRespDto getPreDetail(Long consultingSeq);
}
