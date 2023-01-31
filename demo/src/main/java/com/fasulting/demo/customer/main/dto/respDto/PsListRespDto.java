package com.fasulting.demo.customer.main.dto.respDto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
@ToString
public class PsListRespDto {

    private Long psSeq;

    private String psProfileImg;

    private String psName;

    private String psAddress;

    private String psIntro;

    private List<String> subCategoryName;

    private BigDecimal totalRatingResult;

    private int reviewTotalCount;

}
