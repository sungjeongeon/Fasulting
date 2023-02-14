package com.fasulting.domain.jwt.dto.respDtio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class TokenRespDto {

    protected String accessToken;
    protected String refreshToken;
}
