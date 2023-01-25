package com.fasulting.demo.common;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ResponseBody {

    String message = null;
    Integer statusCode = null;

    public ResponseBody() {}

    public ResponseBody(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public ResponseBody(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public static ResponseBody create(Integer statusCode, String message) {
        ResponseBody body = new ResponseBody();
        body.message = message;
        body.statusCode = statusCode;
        return body;
    }

}
