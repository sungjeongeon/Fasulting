package com.fasulting.domain.jwt.service;

import com.fasulting.domain.jwt.dto.reqDto.LoginReqDto;
import com.fasulting.domain.jwt.dto.respDtio.UserLoginRespDto;

import java.util.Map;

public interface UserJwtService{

    Map<String, Object> login(LoginReqDto userInfo);

    boolean logout(Long userSeq);
}
