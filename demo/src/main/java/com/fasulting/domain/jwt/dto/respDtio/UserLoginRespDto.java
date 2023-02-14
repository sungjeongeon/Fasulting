package com.fasulting.domain.jwt.dto.respDtio;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserLoginRespDto {

    private Long userSeq;
    private String userName;
    private boolean adminYn;

    @Builder
    public UserLoginRespDto(Long userSeq, String userName, boolean adminYn) {
        this.userSeq = userSeq;
        this.userName = userName;
        this.adminYn = adminYn;
    }
}
