package com.fasulting.demo.ps.ps.dto.respDto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Builder
@ToString
public class PsInfoRespDto {

    private Long psSeq;

    private String psName;

    private String psIntro;

    private String Address;

    private String psProfileImg;

    private String psNumber;

    private String psHomepage;

    private String psEmail;

    private boolean isFavorite; // 즐겨찾기 추가 여부(user 접근 기준)

    private BigDecimal totalRatingResult; // 통계 평점

    private int reviewCount; // 전체 리뷰 개수
}
