package com.fasulting.demo.entity.compositeId;

import com.fasulting.demo.entity.PsEntity;
import com.fasulting.demo.entity.ReservationEntity;
import com.fasulting.demo.entity.SubCategoryEntity;
import com.fasulting.demo.entity.TokenEntity;
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
}

