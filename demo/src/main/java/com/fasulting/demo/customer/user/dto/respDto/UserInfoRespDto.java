package com.fasulting.demo.customer.user.dto.respDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoRespDto {

    private Long userSeq;
    private String userEmail;
    private String userName;
    private String userBirth;
    private String userNumber;

}
