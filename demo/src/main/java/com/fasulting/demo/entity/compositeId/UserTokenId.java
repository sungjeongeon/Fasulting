package com.fasulting.demo.entity.compositeId;

import com.fasulting.demo.entity.ReviewEntity;
import com.fasulting.demo.entity.SubCategoryEntity;
import com.fasulting.demo.entity.TokenEntity;
import com.fasulting.demo.entity.UserEntity;
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