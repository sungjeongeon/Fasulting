package com.fasulting.demo.ps.psReservation.service;

import com.fasulting.demo.ps.psReservation.dto.reqDto.ReservationReqDto;
import com.fasulting.demo.ps.psReservation.dto.respDto.PostReservationRespDto;
import com.fasulting.demo.ps.psReservation.dto.respDto.PreDetailRespDto;
import com.fasulting.demo.ps.psReservation.dto.respDto.PreReservationRespDto;

import java.time.LocalDateTime;
import java.util.List;

public interface PsReservationService {

    boolean modifyReservation(ReservationReqDto reservationReqDto);
    PostReservationRespDto getPostReservationList(Long psSeq, LocalDateTime current);

    List<PreReservationRespDto> getPreReservationList(Long psSeq, LocalDateTime current);

    PreDetailRespDto getPreDetail(Long consultingSeq);
}
