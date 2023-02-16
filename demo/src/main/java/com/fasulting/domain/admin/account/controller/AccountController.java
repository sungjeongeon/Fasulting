package com.fasulting.domain.admin.account.controller;

import com.fasulting.common.resp.ResponseBody;
import com.fasulting.common.util.LogCurrent;
import com.fasulting.domain.admin.account.dto.reqDto.ConfirmPsReqDto;
import com.fasulting.domain.admin.account.dto.respDto.PsWaitRespDto;
import com.fasulting.domain.admin.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fasulting.common.util.LogCurrent.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/account")
public class AccountController {

    private final AccountService accountService;

    /**
     * 병원 승인 대기 계정 조회
     * @return List<PsWaitRespDto>
     */
    @GetMapping("/ps")
    public ResponseEntity<?> getPsWaitList() {

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));
        List<PsWaitRespDto> psWaitList = accountService.getPsWaitList();

        if (!psWaitList.isEmpty()) {
            log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success", psWaitList));
        }
        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(204).body(ResponseBody.create(204, "fail"));

    }

    /**
     * 병원 계정 가입 승인
     * @param confirmPsReqDto
     * @return success or fail
     */
    @PatchMapping("/ps")
    public ResponseEntity<?> approvePs(@RequestBody ConfirmPsReqDto confirmPsReqDto) {

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));

        if (accountService.approvePs(confirmPsReqDto)) {
            log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }
        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail")); // 권한 없음

    }

}
