package com.fasulting.demo.customer.user.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserRegisterReq {

    private String userEmail;

    private String userPassword;

    private String userName;

    private Date userBirth;

    private String userGender;

    private String userNation;

    private String userPhone;

    private String userNationCode;

}
