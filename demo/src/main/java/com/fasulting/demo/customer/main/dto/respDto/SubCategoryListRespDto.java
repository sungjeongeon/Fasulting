package com.fasulting.demo.customer.main.dto.respDto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class SubCategoryListRespDto {

    private Long subSeq;

    private String subName;
}
