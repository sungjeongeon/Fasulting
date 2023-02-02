package com.fasulting.demo.ps.ps.dto.respDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PsLoginRespDto {

    private Long psSeq;
    private String psName;

}
