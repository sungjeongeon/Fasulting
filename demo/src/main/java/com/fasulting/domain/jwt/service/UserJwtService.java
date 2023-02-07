package com.fasulting.domain.jwt.service;

import com.fasulting.domain.jwt.dto.reqDto.UserLoginReqDto;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface UserJwtService{

    Map<String, Object> login(UserLoginReqDto userInfo);

    boolean logout(Long userSeq);
}
