package com.fasulting.entity.compositeId;

import com.fasulting.entity.calendar.DefaultCalEntity;
import com.fasulting.entity.ps.PsEntity;
import com.fasulting.entity.calendar.TimeEntity;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class PsDefaultId implements Serializable {

    @EqualsAndHashCode.Include
    public PsEntity ps;

    @EqualsAndHashCode.Include
    public DefaultCalEntity defaultCal;

    @EqualsAndHashCode.Include
    public TimeEntity time;

}
