package com.fasulting.demo.resp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseBody {

    String message;
    Integer statusCode;
    Object responseObj;


    public static ResponseBody create(Integer statusCode, String message){

        ResponseBody body  = new ResponseBody();
        body.setMessage(message);
        body.setStatusCode(statusCode);

        return  body;
    }

    public static ResponseBody create(Integer statusCode, String message, Object responseObj){

        ResponseBody body = ResponseBody.create(statusCode, message);
        body.setResponseObj(responseObj);

        return  body;
    }
}
