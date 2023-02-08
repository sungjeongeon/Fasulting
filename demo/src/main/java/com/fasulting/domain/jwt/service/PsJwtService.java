package com.fasulting.domain.jwt.service;

import com.fasulting.domain.jwt.dto.reqDto.LoginReqDto;

import java.util.Map;

public interface PsJwtService {

    Map<String, Object> login(LoginReqDto userInfo);

    boolean logout(Long psSeq);


}
