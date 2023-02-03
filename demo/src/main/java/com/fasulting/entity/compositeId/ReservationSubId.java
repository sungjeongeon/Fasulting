package com.fasulting.entity.compositeId;

import com.fasulting.entity.reservation.ReservationEntity;
import com.fasulting.entity.category.SubCategoryEntity;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class ReservationSubId implements Serializable {

    @EqualsAndHashCode.Include
    public ReservationEntity reservation;

    @EqualsAndHashCode.Include
    public SubCategoryEntity subCategory;

    @Builder
    public ReservationSubId(ReservationEntity reservation, SubCategoryEntity subCategory) {
        this.reservation = reservation;
        this.subCategory = subCategory;
    }
}

