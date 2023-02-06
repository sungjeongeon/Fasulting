package com.fasulting.domain.jwt.dto.reqDto;

import lombok.Data;

@Data
public class UserLoginReqDto {

    private String email;
    private String password;

}
