package com.fasulting.demo.customer.user.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckPasswordReq {

    private int userSeq;
    private String userPassword;

}
