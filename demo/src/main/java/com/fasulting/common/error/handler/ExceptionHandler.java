package com.fasulting.common.error.handler;

import com.fasulting.common.error.dto.ErrorRespDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@ControllerAdvice
public class ExceptionHandler {

    /**
     * NullPointerException Error
     * code : 500
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(NullPointerException.class)
    public ResponseEntity exNullHandle(NullPointerException e) {
        log.info("[exceptionHandle] {}",e.getMessage());
        return new ResponseEntity(new ErrorRespDto(INTERNAL_SERVER_ERROR.value(), "서버 내부 오류"), INTERNAL_SERVER_ERROR);
    }

    /**
     * FORBIDDEN
     * code : 403
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ResponseEntity exUnAuthHandle(HttpClientErrorException.Forbidden e) {
        log.info("[exceptionHandle] {}",e.getMessage());
        return new ResponseEntity(new ErrorRespDto(FORBIDDEN.value(), "인증 권한 오류"), FORBIDDEN);
    }

}
