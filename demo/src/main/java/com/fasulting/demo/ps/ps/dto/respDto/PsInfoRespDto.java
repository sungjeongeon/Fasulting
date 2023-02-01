package com.fasulting.demo.ps.ps.dto.respDto;

import com.fasulting.demo.common.doctor.dto.DoctorDto;
import com.fasulting.demo.common.review.respDto.ReviewDto;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Getter
@Builder
@ToString
public class PsInfoRespDto {

    private Long psSeq;

    private String psName;

    private String psIntro;

    private String psAddress;

    private String psProfileImg;

    private String psNumber;

    private String psHomepage;

    private String psEmail;

    private BigDecimal totalRatingResult; // 통계 평점

    private int reviewTotalCount; // 전체 리뷰 개수

    private List<String> subCategoryName; // 병원 서브 카테고리

    private Map<Integer, List<Integer>> defaultTime; // 병원 기본 운영 시간

    private List<DoctorDto> doctor; // 의사

    private List<ReviewDto> review; // 리뷰
}
