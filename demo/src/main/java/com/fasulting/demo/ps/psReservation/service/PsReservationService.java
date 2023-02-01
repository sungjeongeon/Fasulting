package com.fasulting.demo.ps.psReservation.service;

import com.fasulting.demo.ps.psReservation.dto.respDto.ReservationRespDto;
import com.fasulting.demo.ps.psReservation.dto.reqDto.ReservationReqDto;

import java.time.LocalDateTime;
import java.util.List;

public interface PsReservationService {

    boolean modifyReservation(ReservationReqDto reservationReqDto);
    List<ReservationRespDto> getPostReservationList(Long psSeq, LocalDateTime current);

}
