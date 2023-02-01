package com.fasulting.demo.ps.psReservation.service;

import com.fasulting.demo.ps.psReservation.dto.respDto.ReservationRespDto;
import com.fasulting.demo.ps.psReservation.request.ReservationReq;

import java.time.LocalDateTime;
import java.util.List;

public interface PsReservationService {

    boolean modifyReservation(ReservationReq reservationReq);
    List<ReservationRespDto> getPostReservationList(Long psSeq, LocalDateTime current);

}
