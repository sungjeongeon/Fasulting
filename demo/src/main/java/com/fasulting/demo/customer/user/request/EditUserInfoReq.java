package com.fasulting.demo.customer.user.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EditUserInfoReq {
    private String userName;
    private String userPhone;

}
