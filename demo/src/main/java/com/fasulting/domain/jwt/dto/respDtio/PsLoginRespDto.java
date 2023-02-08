package com.fasulting.domain.jwt.dto.respDtio;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PsLoginRespDto extends TokenRespDto {

    private Long psSeq;
    private String psName;
    private boolean confirmYn;

    @Builder
    public PsLoginRespDto(String accessToken, String refreshToken, Long psSeq, String psName, String confirmYn) {
        super(accessToken, refreshToken);
        this.psSeq = psSeq;
        this.psName = psName;

        if(confirmYn.equals("Y")){
            this.confirmYn = true;
        }
        else {
            this.confirmYn = false;
        }
    }
}
