package com.fasulting.demo.customer.user.dto.reqDto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/** seq 없이 */
@Getter
@Setter
public class UserWithoutSeqReq {

    private String email;

    private String password;

    private String name;

    private String birth;

    private String gender;

    private String nation;

    private String number;

    private String nationCode;

}
