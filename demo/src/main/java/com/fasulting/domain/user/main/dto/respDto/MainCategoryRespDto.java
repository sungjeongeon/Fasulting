package com.fasulting.domain.user.main.dto.respDto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class MainCategoryRespDto {

    private Long mainSeq;

    private String mainName;
}
