package com.fasulting.domain.user.reservation.dto.respDto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Builder
@ToString
public class ReportRespDto {

    // 병원 정보 //
    private Long psSeq;
    private String psName;
    private String psAddress;
    private String psNumber;
    private String psHomepage;
    private String psEmail;
    private Map<Integer, List<Integer>> defaultTime; // 병원 기본 운영 시간

    // 소견서 내용 //
    private String beforeImgPath;
    private String afterImgPath;
    private String estimate;
    private String content;
    private List<String> subCategoryName;

}
