package com.fasulting.domain.jwt.service;

import com.fasulting.domain.jwt.dto.reqDto.LoginReqDto;
import com.fasulting.domain.jwt.dto.respDtio.AdminLoginRespDto;

import java.util.Map;

public interface AdminJwtService {

    AdminLoginRespDto login(LoginReqDto userInfo);
    boolean logout(Long adminSeq);
}
