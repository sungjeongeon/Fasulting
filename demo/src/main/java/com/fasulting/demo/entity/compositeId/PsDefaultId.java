package com.fasulting.demo.entity.compositeId;

import com.fasulting.demo.entity.DefaultCalEntity;
import com.fasulting.demo.entity.PsEntity;
import com.fasulting.demo.entity.TimeEntity;
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
