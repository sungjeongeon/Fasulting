package com.fasulting.demo.ps.psReservation.service;

import com.fasulting.demo.ps.psReservation.dto.reqDto.ReservationReqDto;
import com.fasulting.demo.ps.psReservation.dto.respDto.PostReservationRespDto;

import java.time.LocalDateTime;

public interface PsReservationService {

    boolean modifyReservation(ReservationReqDto reservationReqDto);
    PostReservationRespDto getPostReservationList(Long psSeq, LocalDateTime current);

}
