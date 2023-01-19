package com.fasulting.demo.customer.user.response;

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
    private String userGender;
    private String userPhone;
    private String userNationCode;

}
