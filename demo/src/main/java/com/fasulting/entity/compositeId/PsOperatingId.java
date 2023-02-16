package com.fasulting.entity.compositeId;

import lombok.Builder;
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

    @Builder
    public PsOperatingId(Long ps, Long operatingCal, Long time) {
        this.ps = ps;
        this.operatingCal = operatingCal;
        this.time = time;
    }
}
