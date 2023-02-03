package com.fasulting.entity.compositeId;
import com.fasulting.entity.calendar.OperatingCalEntity;
import com.fasulting.entity.ps.PsEntity;
import com.fasulting.entity.calendar.TimeEntity;
import lombok.Builder;
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
    public TimeEntity time;

    @Builder
    public PsOperatingId(PsEntity ps, OperatingCalEntity operatingCal, TimeEntity time) {
        this.ps = ps;
        this.operatingCal = operatingCal;
        this.time = time;
    }
}
