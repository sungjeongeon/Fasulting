package com.fasulting.domain.admin.account.controller;

import com.fasulting.domain.admin.account.dto.reqDto.ConfirmPsReqDto;
import com.fasulting.domain.admin.account.dto.respDto.PsWaitRespDto;
import com.fasulting.domain.admin.account.service.AccountService;
import com.fasulting.common.resp.ResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin/account")
@CrossOrigin("*")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    /**
     * 1. 병원 승인 대기 계정 조회
     * @return 병원 승인 계정 리스트
     */
    @GetMapping("/ps")
    public ResponseEntity<?> getPsWaitList() {

        log.info("getPsWaitList - Call");

        List<PsWaitRespDto> psWaitList = accountService.getPsWaitList();

        if (psWaitList != null) {
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success", psWaitList));
        }

        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));

    }

    /**
     * 2. 병원 계정 가입 승인
     * @param confirmPsReqDto
     * @return success or fail
     */
    @PatchMapping("/ps")
    public ResponseEntity<?> ApprovePs(@RequestBody ConfirmPsReqDto confirmPsReqDto) {

        log.info("ApprovePs - Call");

        if (accountService.approvePs(confirmPsReqDto)) {
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }

        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));

    }

}
