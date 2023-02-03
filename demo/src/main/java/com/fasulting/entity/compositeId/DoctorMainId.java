package com.fasulting.entity.compositeId;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter
@NoArgsConstructor
public class DoctorMainId implements Serializable  {

    @EqualsAndHashCode.Include
    public Long doctor;

    @EqualsAndHashCode.Include
    public Long mainCategory;
}
