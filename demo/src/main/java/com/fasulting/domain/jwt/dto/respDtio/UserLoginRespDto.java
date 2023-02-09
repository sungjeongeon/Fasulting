package com.fasulting.domain.jwt.dto.respDtio;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserLoginRespDto extends TokenRespDto {

    private Long userSeq;
    private String userName;
    private boolean adminYn;

    @Builder
    public UserLoginRespDto(String accessToken, String refreshToken, Long userSeq, String userName, boolean adminYn) {
        super(accessToken, refreshToken);
        this.userSeq = userSeq;
        this.userName = userName;
        this.adminYn = adminYn;
    }
}
