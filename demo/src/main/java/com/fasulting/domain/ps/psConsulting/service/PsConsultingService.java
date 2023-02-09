package com.fasulting.domain.ps.psConsulting.service;

import com.fasulting.domain.ps.psConsulting.dto.ResultReqDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface PsConsultingService {
    Map<String, String> getBeforeImg(Long reservationSeq);

    ResponseEntity<String> initializeSession(@RequestBody(required = false) Map<String, Object> params);
    ResponseEntity<String> createConnection(@PathVariable("sessionId") String sessionId,
                                            @RequestBody(required = false) Map<String, Object> params);

    boolean writeResult(ResultReqDto resultReq);
}
