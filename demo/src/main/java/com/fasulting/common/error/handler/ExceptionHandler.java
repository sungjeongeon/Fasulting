package com.fasulting.common.error.handler;

import com.fasulting.common.error.dto.ErrorRespDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@ControllerAdvice
public class ExceptionHandler {

    /**
     * NullPointerException Error
     * code : 500
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(NullPointerException.class)
    public ResponseEntity exHandle(NullPointerException e) {
        log.info("[exceptionHandle] {}",e.getMessage());
        return new ResponseEntity(new ErrorRespDto(INTERNAL_SERVER_ERROR.value(), "서버 내부 오류"), INTERNAL_SERVER_ERROR);
    }

    /**
     * UnAuthorizedException
     * code : 403
     */

}
