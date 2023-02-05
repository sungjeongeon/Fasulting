package com.fasulting.domain.admin.account.service;

import com.fasulting.domain.admin.account.dto.reqDto.ConfirmPsReqDto;
import com.fasulting.domain.admin.account.dto.respDto.PsWaitRespDto;

import java.util.List;

public interface AccountService {
    List<PsWaitRespDto> getPsWaitList();

    boolean approvePs(ConfirmPsReqDto confirmPsReqDto);
}
