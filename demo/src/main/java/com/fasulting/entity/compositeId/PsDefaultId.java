package com.fasulting.entity.compositeId;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class PsDefaultId implements Serializable {

    @EqualsAndHashCode.Include
    public Long ps;

    @EqualsAndHashCode.Include
    public Long defaultCal;

    @EqualsAndHashCode.Include
    public Long time;

}
