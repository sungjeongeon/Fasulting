package com.fasulting.demo.customer.user.dto.reqDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/** seq 포함된 요청*/
@Getter
@Setter
@ToString
public class UserSeqReq {

    private Long seq;

    private String name;

    private String number;

    private String password;

}
