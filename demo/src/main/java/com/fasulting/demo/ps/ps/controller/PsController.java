package com.fasulting.demo.ps.ps.controller;

import com.fasulting.demo.resp.ResponseBody;
import com.fasulting.demo.ps.ps.dto.reqDto.PsSeqReq;
import com.fasulting.demo.ps.ps.dto.reqDto.PsWithoutSeqReq;
import com.fasulting.demo.common.EmailService;
import com.fasulting.demo.common.EmailServiceImpl;
import com.fasulting.demo.ps.ps.service.PsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(value = "병원 계정 관련 API", tags = {"PsController"})
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
     * @param psInfo
     * email & password
     * @return
     * seq & 가입 승인 여부
     */
    @PostMapping("/login")
    @ApiOperation(value = "병원 계정 로그인", notes = "..")
    public ResponseEntity<?> login(@RequestBody @ApiParam(value = "로그인 정보", required = true) PsWithoutSeqReq psInfo) {
        return null;
    }

    /**
     * 2. 로그아웃 - jwt
     * @param seq
     * @return fail OR success
     */
    @GetMapping("/logout/{seq}")
    @ApiOperation(value = "병원 계정 로그아웃", notes = "..")
    public ResponseEntity<?> logout(@PathVariable @ApiParam(value = "로그아웃 정보 (ps seq)", required = true) int seq) {
        return null; // fail OR successs
    }


    /**
     * 3. 병원 계정 가입
     * @param psInfo
     * @return fail or success
     */
    @PostMapping("/regist")
    @ApiOperation(value = "병원 계정 회원 가입", notes = "병원 기본 정보, 전문의 리스트 기입하여 회원 가입")
    public ResponseEntity<?> psRegister(@ModelAttribute @ApiParam(value = "회원 가입 정보", required = true) PsWithoutSeqReq psInfo) {
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
    @ApiOperation(value = "이메일 조회 및 중복 확인", notes = "이메일 받아서 DB 조회 및 중복 확인")
    public ResponseEntity<?> checkEmail(@PathVariable @ApiParam(value = "회원 이메일", required = true) String email) {
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
    @ApiOperation(value = "병원 정보 조회", notes = "seq 받아서 병원 정보 조회")
    public ResponseEntity<?> getPsInfo(@PathVariable @ApiParam(value = "Ps seq", required = true) Long seq) {
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
    @ApiOperation(value = "병원 정보 수정", notes = "병원 수정 정보 받아 정보 수정")
    public ResponseEntity<?> editPsInfo(@ModelAttribute @ApiParam(value = "병원 수정 정보", required = true) PsSeqReq psInfo) {

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
    @ApiOperation(value = "병원 계정 탈퇴", notes = "병원 seq받아 병원 계정 탈퇴 처리")
    public ResponseEntity<?> withdrawPs(@RequestBody @ApiParam(value = "Ps seq", required = true) PsSeqReq psInfo) {

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
    @ApiOperation(value = "비밀번호 확인", notes = "병원 seq, 비밀번호 받아 DB의 기존 비밀번호와 비교")
    public ResponseEntity<?> checkPassword(@RequestBody @ApiParam(value = "병원 seq, 비밀번호", required = true) PsSeqReq psInfo) {
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
    @ApiOperation(value = "비밀번호 수정", notes = "병원 이메일, 비밀번호 받아 DB의 기존 비밀번호와 비교")
    public ResponseEntity<?> restPassword(@RequestBody @ApiParam(value = "병원 이메일, 비밀번호", required = true) PsWithoutSeqReq psInfo) {
        log.info("ps reset Password - Call");

        if(psService.resetPassword(psInfo)){
            log.info("성공");
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }

        // 회원 수정 실패
        return ResponseEntity.status(200).body(ResponseBody.create(200, "fail"));
    }

}
