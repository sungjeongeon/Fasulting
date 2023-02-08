package com.fasulting.domain.jwt.dto.respDtio;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PsLoginRespDto extends TokenRespDto {

    private Long psSeq;
    private String psName;

    @Builder
    public PsLoginRespDto(String accessToken, String refreshToken, Long psSeq, String psName) {
        super(accessToken, refreshToken);
        this.psSeq = psSeq;
        this.psName = psName;
    }
}
