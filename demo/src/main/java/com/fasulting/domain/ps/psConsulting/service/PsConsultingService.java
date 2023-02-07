package com.fasulting.domain.ps.psConsulting.service;

import com.fasulting.domain.ps.psConsulting.dto.ResultReqDto;

import java.util.Map;

public interface PsConsultingService {
    Map<String, String> getBeforeImg(Long reservationSeq);

    boolean writeResult(ResultReqDto resultReq);
}
