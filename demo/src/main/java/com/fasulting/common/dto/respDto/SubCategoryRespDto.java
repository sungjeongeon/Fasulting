package com.fasulting.common.dto.respDto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class SubCategoryRespDto {

    private Long mainSeq;
    private Long subSeq;
    private String subName;
}
