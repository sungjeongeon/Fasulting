package com.fasulting.domain.user.user.service;

import com.fasulting.domain.user.user.dto.reqDto.UserLoginReqDto;
import com.fasulting.entity.user.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserLoginService extends UserDetailsService {

    UserEntity login(UserLoginReqDto userInfo);

}
