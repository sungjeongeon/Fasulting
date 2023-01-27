package com.fasulting.demo.customer.user.dto.respDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserInfoResp {

    private String email;
    private String name;
    private String birth;
    private String nation;
    private String phone;
    private String nationCode;

}
