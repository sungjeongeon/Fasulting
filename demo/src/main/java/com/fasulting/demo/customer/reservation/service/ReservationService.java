package com.fasulting.demo.customer.reservation.service;

import com.fasulting.demo.customer.reservation.dto.reqDto.CancelReservationReqDto;
import com.fasulting.demo.customer.reservation.dto.reqDto.RegReservationReqDto;
import com.fasulting.demo.customer.reservation.dto.respDto.PostReservationRespDto;
import com.fasulting.demo.customer.reservation.dto.respDto.PreReservationRespDto;
import com.fasulting.demo.customer.reservation.dto.respDto.ReportRespDto;
import com.fasulting.demo.customer.reservation.dto.respDto.ReservationTableRespDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {

    ReservationTableRespDto getReservationTable(Long psSeq, LocalDateTime current);

    boolean addReservation(RegReservationReqDto regReservationReqDto);

    List<PreReservationRespDto> getPreReservationList(Long userSeq);

    List<PostReservationRespDto> getPostReservationList(Long userSeq);

    boolean cancelReservation(CancelReservationReqDto cancelReservationReqDto);

    ReportRespDto getReport(Long userSeq, Long consultingSeq);
}
