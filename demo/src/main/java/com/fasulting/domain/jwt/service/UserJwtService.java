package com.fasulting.domain.jwt.service;

import com.fasulting.domain.jwt.dto.reqDto.UserLoginReqDto;

import java.util.Map;

public interface UserJwtService{

    public Map<String, Object> login(UserLoginReqDto userInfo);
}
