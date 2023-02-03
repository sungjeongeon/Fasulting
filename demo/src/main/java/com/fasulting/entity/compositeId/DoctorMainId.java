package com.fasulting.entity.compositeId;

import com.fasulting.entity.doctor.DoctorEntity;
import com.fasulting.entity.category.MainCategoryEntity;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter
@NoArgsConstructor
public class DoctorMainId implements Serializable  {

    @EqualsAndHashCode.Include
    public DoctorEntity doctor;

    @EqualsAndHashCode.Include
    public MainCategoryEntity mainCategory;
}
