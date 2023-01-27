package com.fasulting.demo.entity.compositeId;

import com.fasulting.demo.entity.MainCategoryEntity;
import com.fasulting.demo.entity.PsEntity;
import com.fasulting.demo.entity.TokenEntity;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class PsTokenId implements Serializable {

    @EqualsAndHashCode.Include
    public PsEntity ps;

    @EqualsAndHashCode.Include
    public TokenEntity token;
}
