package com.fasulting.demo.entity.compositeId;

import com.fasulting.demo.entity.*;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class PsOperatingId implements Serializable {

    @EqualsAndHashCode.Include
    public Long ps;

    @EqualsAndHashCode.Include
    public Long operatingCal;

    @EqualsAndHashCode.Include
    public Long time;
}
