package com.fasulting.domain.ps.psConsulting.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Data
@Builder
public class ResultReqDto {

    Long reservationSeq;
    String reportContent;
    String reportEstimate;

    Map<String, Object> params;
    String sessionId;

    MultipartFile afterImg;

}
