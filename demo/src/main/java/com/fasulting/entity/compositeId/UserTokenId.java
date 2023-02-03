package com.fasulting.entity.compositeId;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class UserTokenId implements Serializable {

    @EqualsAndHashCode.Include
    public Long user;

    @EqualsAndHashCode.Include
    public Long token;
}