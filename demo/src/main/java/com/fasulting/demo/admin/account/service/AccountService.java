package com.fasulting.demo.admin.account.service;

import com.fasulting.demo.admin.account.dto.respDto.PsWaitRespDto;

import java.util.List;

public interface AccountService {
    List<PsWaitRespDto> getPsWaitList();

    boolean approvePs(Long psSeq);
}
