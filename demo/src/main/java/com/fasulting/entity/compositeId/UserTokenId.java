package com.fasulting.entity.compositeId;

import com.fasulting.entity.TokenEntity;
import com.fasulting.entity.user.UserEntity;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class UserTokenId implements Serializable {

    @EqualsAndHashCode.Include
    public UserEntity user;

    @EqualsAndHashCode.Include
    public TokenEntity token;
}