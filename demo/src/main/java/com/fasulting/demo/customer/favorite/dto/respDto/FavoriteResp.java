package com.fasulting.demo.customer.favorite.dto.respDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
@ToString
public class FavoriteResp {

    private Long psSeq;

    private String psName;

    private List<String> subCategoryName;

    private BigDecimal totalRatingResult;

    private int reviewTotalCount;


}
