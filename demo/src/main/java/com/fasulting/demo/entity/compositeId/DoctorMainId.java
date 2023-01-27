package com.fasulting.demo.entity.compositeId;

import com.fasulting.demo.entity.DoctorEntity;
import com.fasulting.demo.entity.MainCategoryEntity;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class DoctorMainId implements Serializable  {

    @EqualsAndHashCode.Include
    public DoctorEntity doctor;

    @EqualsAndHashCode.Include
    public MainCategoryEntity mainCategory;
}
