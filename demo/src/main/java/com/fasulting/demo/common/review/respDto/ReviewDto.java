package com.fasulting.demo.common.review.respDto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
@ToString
public class ReviewDto {

    private Long reviewSeq;

    private Long userSeq;

    private Long psSeq;

    private String userEmail;

    private BigDecimal point;

    private String regDate;

    private String content;

    private List<String> subCategoryName;
}
