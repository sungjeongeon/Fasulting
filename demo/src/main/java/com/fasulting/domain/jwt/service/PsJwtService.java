package com.fasulting.domain.jwt.service;

import com.fasulting.domain.jwt.dto.reqDto.LoginReqDto;
import com.fasulting.domain.jwt.dto.respDtio.PsLoginRespDto;

import java.util.Map;
import java.util.Objects;

public interface PsJwtService {

    Map<String, Object> login(LoginReqDto userInfo);
    boolean logout(Long psSeq);


}
