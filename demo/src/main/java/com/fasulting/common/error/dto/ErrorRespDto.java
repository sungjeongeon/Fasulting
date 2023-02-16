package com.fasulting.common.error.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorRespDto {
    private Integer statusCode;
    private String message;

    public ErrorRespDto(Date timestamp, int code, String message) {
        super();
        this.statusCode = code;
        this.message = message;
    }

}
