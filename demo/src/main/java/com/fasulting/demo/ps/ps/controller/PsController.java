package com.fasulting.demo.ps.ps.controller;

import com.fasulting.demo.common.ResponseBody;
import com.fasulting.demo.entity.PsEntity;
import com.fasulting.demo.ps.ps.dto.reqDto.PsSeqReq;
import com.fasulting.demo.ps.ps.dto.reqDto.PsWithoutSeqReq;
import com.fasulting.demo.ps.ps.dto.respDto.PsInfoResp;
import com.fasulting.demo.ps.ps.service.PsEmailService;
import com.fasulting.demo.ps.ps.service.PsEmailServiceImpl;
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
    private PsEmailService psEmailService;

    @Autowired
    public PsController(PsService psService, PsEmailServiceImpl psEmailService) {

        this.psService = psService;
        this.psEmailService = psEmailService;

    }

    /**
     * 1. 로그인 - jwt
     * @param psInfo
     * email & password
     * @return
     * seq & 가입 승인 여부
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody PsWithoutSeqReq psInfo) {
        return null;
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
     * @param psInfo
     * @return fail or success
     */
    @PostMapping("/regist")
    public ResponseEntity<?> psRegister(@RequestBody PsWithoutSeqReq psInfo) {
        log.info("psRegister - Call");

        if(psService.psRegister(psInfo)) {
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }
        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
    }

    /**
     * 4. 이메일 조회 및 중복 확인
     * @param email
     * @return fail or success
     * fail: email 중복
     * sucess: email 중복 아님
     */
    @GetMapping("/{email}")
    public ResponseEntity<?> checkEmail(@PathVariable String email) {
        log.info("ps check Email - Call");
        if(psService.checkEmail(email)) {
            // 이메일 존재
            return ResponseEntity.status(200).body(ResponseBody.create(200, "존재하는 이메일입니다."));
        }


        return ResponseEntity.status(200).body(ResponseBody.create(200, "존재하지 않는 이메일입니다."));
    }

    /**
     * 5. 병원 정보 조회
     * @param seq
     * @return 회원 정보 OR fail
     */
    @GetMapping("/info/{seq}")
    public ResponseEntity<?> getPsInfo(@PathVariable Long seq) {
        log.info("getPsInfo - Call");

        // 로그인 했는지 검사 필요

        return ResponseEntity.status(200).body(ResponseBody.create(200, "success", psService.getPsInfo(seq)));
    }

    /**
     * 6. 병원 정보 수정
     * @param psInfo 병원 계정 수정 정보
     * @return success OR fail
     */
    @PutMapping("/edit")
    public ResponseEntity<?> editPsInfo(@RequestBody PsSeqReq psInfo) {

        log.info("ps editPs - Call");

        // 로그인 했는지 검사 필요

        if(psService.editPsInfo(psInfo)) {
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }
        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));

    }

    /**
     * 7. 병원 게정 탈퇴
     * @param psInfo (seq)
     * @return success OR fail
     */
    @PatchMapping("/withdraw")
    public ResponseEntity<?> withdrawPs(@RequestBody PsSeqReq psInfo) {

        log.info("ps withdraw - Call");

        // 로그인 했는지 검사 필요

        if(psService.withdrawPs(psInfo)) {
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }
        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));

    }

    /**
     * 8. 보안 - 비밀번호 확인 (로그인 상태에서)
     * DB table 안의 기존 비밀 번호 & 새로 설정한 비밀번호 비교
     * @param psInfo (seq, password)
     * @return success OR fail
     */
    @PostMapping("/passcheck")
    public ResponseEntity<?> checkPassword(@RequestBody PsSeqReq psInfo) {
        log.info("checkPassword - Call");
        // 로그인 했는지 검사 필요
        if(psService.checkPassword(psInfo)) {
            // 비밀 번호 같음
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }
        // 비밀번호 다름
        return ResponseEntity.status(500).body(ResponseBody.create(200, "fail"));
    }

    /**
     * 9. 비밀번호 수정 - 재설정 - 비밀번호만 update
     * @param psInfo
     * @return success OR fail
     * success: update 성공
     * fail: update 실패
     */
    @PatchMapping("/reset")
    public ResponseEntity<?> restPassword(@RequestBody PsWithoutSeqReq psInfo) {
        log.info("ps reset Password - Call");

        if(psService.resetPassword(psInfo)){
            log.info("성공");
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }

        // 회원 수정 실패
        return ResponseEntity.status(200).body(ResponseBody.create(200, "fail"));
    }

    /**
     * 10. 이메일 인증 코드 발송 (회원 가입)
     * @param email
     * @return success OR fail
     * success: 회원 가입 인증 코드 메일 발송 완료
     * fail: 메일 발송 불발
     * @throws Exception
     */
    @GetMapping("/regist/{email}")
    public ResponseEntity<?> registSendEmailCode(@PathVariable String email){
        log.info("regist - sendEmailCode - Call");

        String code = null;
        try {
            code = psEmailService.sendRegistCodeMessage(email);

        } catch (Exception e) {

            log.info(e.getMessage());
            return ResponseEntity.status(400).body(ResponseBody.create(400, "success"));
        }

        log.info("인증코드: " + code);
        return ResponseEntity.status(200).body("success");
    }

    /**
     * 10-1. 이메일 인증 코드 발송 (비밀번호 재설정)
     * @param email
     * @return success OR fail
     * success: 회원 가입 인증 코드 메일 발송 완료
     * fail: 메일 발송 불발
     * @throws Exception
     */
    @GetMapping("/reset/{email}")
    public ResponseEntity<? extends ResponseBody> ResetSendEmailCode(@PathVariable String email){
        log.info("reset - sendEmailCode - Call");

        String code = null;
        try {
            code = psEmailService.sendResetCodeMessage(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("인증코드: " + code);
        return ResponseEntity.status(200).body(ResponseBody.create(200, "sueccess"));
    }

    ///////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 11. 이메일 인증 코드 확인 (accessCode, email)
     * @return
     */
    @PostMapping("/access")
    public ResponseEntity<?> checkEmailCode() {
        // 이메일 인증 코드 & Server에서 전송한 이메일 코드 일치 여부 확인
        return null; // fail OR success
    }

}
