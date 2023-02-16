package com.fasulting.domain.ps.ps.dto.reqDto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class PsDefaultReqDto {

    private Long psSeq;
    private Map<String, List<Integer>> defaultTime;

    @Builder
    public PsDefaultReqDto(Long psSeq, Map<String, List<Integer>> defaultTime) {
        this.psSeq = psSeq;
        this.defaultTime = defaultTime;
    }
}
