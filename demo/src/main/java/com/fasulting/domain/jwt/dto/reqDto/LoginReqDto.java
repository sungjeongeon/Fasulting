package com.fasulting.domain.jwt.dto.reqDto;

import lombok.Data;

@Data
public class LoginReqDto {

    private String email;
    private String password;

}
