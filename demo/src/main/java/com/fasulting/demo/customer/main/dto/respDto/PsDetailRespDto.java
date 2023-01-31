package com.fasulting.demo.customer.main.dto.respDto;

import com.fasulting.demo.common.review.respDto.ReviewDto;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
@ToString
public class PsDetailRespDto {

    private Long psSeq;

    private String psName;

    private String psIntro;

    private String psAddress;

    private String psProfileImg;

    private String psNumber;

    private String psHomepage;

    private String psEmail;

    private boolean isFavorite; // 즐겨찾기 추가 여부(user 접근 기준)

    private BigDecimal totalRatingResult; // 통계 평점

    private int reviewTotalCount; // 전체 리뷰 개수

    private List<String> subCategoryName; // 병원 서브 카테고리

    private List<PsDefaultDto> defaultTime; // 병원 기본 운영 시간

    private List<DoctorDto> doctor; // 의사

    private List<ReviewDto> review; // 리뷰
}
