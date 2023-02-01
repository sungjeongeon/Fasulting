package com.fasulting.demo.customer.reservation.service;

import com.fasulting.demo.customer.reservation.dto.respDto.ReservationTableRespDto;

import java.time.LocalDateTime;

public interface ReservationService {

    ReservationTableRespDto getReservationTable(Long psSeq, LocalDateTime current);
}
