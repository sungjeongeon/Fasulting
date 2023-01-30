package com.fasulting.demo.entity.compositeId;

import com.fasulting.demo.entity.*;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class PsOperatingId implements Serializable {

    @EqualsAndHashCode.Include
    public PsEntity ps;

    @EqualsAndHashCode.Include
    public OperatingCalEntity operatingCal;

    @EqualsAndHashCode.Include
    public OperatingTimeEntity operatingTime;
}
