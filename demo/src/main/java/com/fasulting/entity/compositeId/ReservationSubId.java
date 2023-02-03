package com.fasulting.entity.compositeId;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class ReservationSubId implements Serializable {

    @EqualsAndHashCode.Include
    public Long reservation;

    @EqualsAndHashCode.Include
    public Long subCategory;

    @Builder
    public ReservationSubId(Long reservation, Long subCategory) {
        this.reservation = reservation;
        this.subCategory = subCategory;
    }
}

