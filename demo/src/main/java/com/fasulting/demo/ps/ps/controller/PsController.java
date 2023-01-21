package com.fasulting.demo.ps.ps.controller;

import com.fasulting.demo.common.ResponseBody;
import com.fasulting.demo.ps.ps.request.*;
import com.fasulting.demo.ps.ps.service.PsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/ps")
@CrossOrigin("*") // 수정
public class PsController {

    private PsService psService;

    @Autowired
    public PsController(PsService psService) {
        this.psService = psService;
    }

    /**
     * 1. 로그인 - jwt
     * @param psLoginReq
     * email & password
     * @return
     * seq & 가입 승인 여부
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody PsLoginReq psLoginReq) {
        return null; // response: userid (DB table 안의)
    }

    /**
     * 2. 로그아웃 - jwt
     * @param seq
     * @return fail OR success
     */
    @GetMapping("/logout/{seq}")
    public ResponseEntity<?> logout(@PathVariable int seq) {
        return null; // fail OR successs
    }


    /**
     * 3. 병원 계정 가입
     * @param psRegisterReq
     * @return fail or success
     */
    @PostMapping("/regist")
    public ResponseEntity<?> regist(@RequestBody PsRegisterReq psRegisterReq) { return null; }

    /**
     * 4. 이메일 중복 확인
     * @param email
     * @return fail or success
     * fail: email 중복
     * sucess: email 중복 아님
     */
    @GetMapping("/duple/{email}")
    public ResponseEntity<?> dupleEmail(@PathVariable String email) {
       return null;
    }

    /**
     * 5. 병원 정보 조회
     * @param seq
     * @return 회원 정보 OR fail
     */
    @GetMapping("/info/{seq}")
    public ResponseEntity<?> getPsInfo(@PathVariable int seq) {
        return null;
    }

    /**
     * 6. 병원 정보 수정
     * @param seq 유저 아이디
     * @param editPsLoginReq 병원 계정 수정 정보
     * @return success OR fail
     */
    @PutMapping("/edit/{seq}")
    public ResponseEntity<?> editPsInfo(@PathVariable("seq") int seq, @RequestBody EditPsLoginReq editPsLoginReq) {
        return null;
    }

    /**
     * 7. 병원 게정 탈퇴
     * @param withdrawPsReq (seq)
     * @return success OR fail
     */
    @PatchMapping("/withdraw")
    public ResponseEntity<?> withdrawPs(@RequestBody WithdrawPsReq withdrawPsReq) {
        return null;
    }

    /**
     * 8. 보안 - 비밀번호 확인 (로그인 상태에서)
     * DB table 안의 기존 비밀 번호 & 새로 설정한 비밀번호 비교
     * @param checkPasswordReq (seq, password)
     * @return success OR fail
     */
    @PostMapping("/passcheck")
    public ResponseEntity<?> checkPassword(@RequestBody CheckPasswordReq checkPasswordReq) {
        return null;
    }

    /**
     * 9. 이메일 인증 코드 확인
     * @param checkEmailCodeReq (accessCode, email)
     * @return
     */
    @PostMapping("/access")
    public ResponseEntity<?> checkEmailCode(@RequestBody CheckEmailCodeReq checkEmailCodeReq) {
        // 이메일 인증 코드 & Server에서 전송한 이메일 코드 일치 여부 확인
        return null; // fail OR success
    }

    /**
     * 10. 비밀번호 수정 - 재설정 - 비밀번호만 update
     * @param psResetInfo
     * @return success OR fail
     * success: update 성공
     * fail: update 실패
     */
    @PatchMapping("/reset")
    public ResponseEntity<?> restPassword(@RequestBody PsResetInfo psResetInfo) {
        return null;
    }

    /**
     * 이메일 입력 (비밀번호 수정 - 로그인 상태)
     * @param seq
     * @param email
     * @return
     */
    @GetMapping("/{seq}/{email}")
    public ResponseEntity<?> checkEmail(@PathVariable("seq") String seq, @PathVariable("email") String email) {
        return null;
    }

}
