package com.fasulting.domain.admin.admin.dto.respDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminLoginRespDto {

    private Long adminSeq;
    private String adminName;

}
