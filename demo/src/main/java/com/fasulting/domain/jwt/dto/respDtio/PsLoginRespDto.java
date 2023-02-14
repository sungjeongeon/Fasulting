package com.fasulting.domain.jwt.dto.respDtio;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PsLoginRespDto {

    private Long psSeq;
    private String psName;
    private boolean confirmYn;

    @Builder
    public PsLoginRespDto(Long psSeq, String psName, String confirmYn) {
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
