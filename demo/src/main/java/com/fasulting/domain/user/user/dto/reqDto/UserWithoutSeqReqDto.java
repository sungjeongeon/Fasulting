package com.fasulting.domain.user.user.dto.reqDto;

import lombok.Getter;
import lombok.Setter;

/** seq 없이 */
@Getter
@Setter
public class UserWithoutSeqReqDto {

    private String email;

    private String password;

    private String name;

    private String birth;

    private String nation;

    private String number;

    private String nationCode;

}
