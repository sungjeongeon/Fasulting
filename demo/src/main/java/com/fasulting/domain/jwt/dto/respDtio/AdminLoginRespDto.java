package com.fasulting.domain.jwt.dto.respDtio;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AdminLoginRespDto extends TokenRespDto {

    private Long adminSeq;
    private String adminName;

    @Builder
    public AdminLoginRespDto(String accessToken, String refreshToken, Long adminSeq, String adminName) {
        super(accessToken, refreshToken);
        this.adminSeq = adminSeq;
        this.adminName = adminName;
    }
}
