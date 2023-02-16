package com.fasulting.common.dto.respDto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
@ToString
public class ReviewRespDto {

    private Long reviewSeq;

    private Long userSeq;

    private Long psSeq;

    private String psName;

    private String userEmail;

    private BigDecimal point;

    private String regDate;

    private String content;

    private String userName;

    private List<String> subCategoryName;
}
