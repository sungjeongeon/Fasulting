package com.fasulting.demo.customer.user.dto.respDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserInfoResp {

    private String userEmail;
    private String userName;
    private String userBirth;
    private String userNation;
    private String userPhone;
    private String userNationCode;

}
