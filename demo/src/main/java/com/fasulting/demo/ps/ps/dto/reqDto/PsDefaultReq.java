package com.fasulting.demo.ps.ps.dto.reqDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class PsDefaultReq {

    private Long psSeq;
    private Map<String, List<Integer>> defaultTime;

    @Builder
    public PsDefaultReq(Long psSeq, Map<String, List<Integer>> defaultTime) {
        this.psSeq = psSeq;
        this.defaultTime = defaultTime;
    }
}
