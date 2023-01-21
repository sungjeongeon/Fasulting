package com.fasulting.demo.admin.account.controller;

import com.fasulting.demo.admin.account.request.ApprovePsReq;
import com.fasulting.demo.admin.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/account")
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
        return null;
    }

    /**
     * 2. 병원 계정 가입 승인
     * @param approvePsReq
     * @return success or fail
     */
    @PatchMapping("/ps")
    public ResponseEntity<?> ApprovePs(@RequestBody ApprovePsReq approvePsReq) {
        return null;
    }

}
