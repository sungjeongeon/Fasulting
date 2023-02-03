package com.fasulting.domain.user.main.dto.respDto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class SubCategoryRespDto {

    private Long subSeq;

    private String subName;
}
