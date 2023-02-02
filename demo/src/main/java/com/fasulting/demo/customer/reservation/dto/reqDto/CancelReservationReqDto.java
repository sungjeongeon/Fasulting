package com.fasulting.demo.customer.reservation.dto.reqDto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CancelReservationReqDto {

    private Long userSeq;
    private Long reservationSeq;
}
