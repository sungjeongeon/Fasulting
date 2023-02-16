package com.fasulting.domain.user.reservation.dto.reqDto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CancelReservationReqDto {

    private Long userSeq;
    private Long reservationSeq;
}
