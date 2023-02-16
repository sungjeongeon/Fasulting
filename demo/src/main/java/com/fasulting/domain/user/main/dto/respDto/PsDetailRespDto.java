package com.fasulting.domain.user.main.dto.respDto;

import com.fasulting.common.dto.respDto.DoctorRespDto;
import com.fasulting.common.dto.respDto.ReviewRespDto;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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

    private Map<Integer, List<Integer>> defaultTime; // 병원 기본 운영 시간

    private List<DoctorRespDto> doctor; // 의사

    private List<ReviewRespDto> review; // 리뷰
}
