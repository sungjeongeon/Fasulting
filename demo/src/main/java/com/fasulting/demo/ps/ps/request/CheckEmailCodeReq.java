package com.fasulting.demo.ps.ps.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckEmailCodeReq {

    private String accessCode;
    private String email;

}
