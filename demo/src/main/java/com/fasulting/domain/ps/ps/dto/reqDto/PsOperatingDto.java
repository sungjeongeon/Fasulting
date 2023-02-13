package com.fasulting.domain.ps.ps.dto.reqDto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class PsOperatingDto {

    private Long psSeq;
    private List<String> operatingTime;

    @Builder
    public PsOperatingDto(Long psSeq, List<String> operatingTime) {
        this.psSeq = psSeq;
        this.operatingTime = operatingTime;
    }
}
