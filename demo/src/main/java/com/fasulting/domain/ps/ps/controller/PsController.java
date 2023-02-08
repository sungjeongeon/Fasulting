package com.fasulting.domain.ps.ps.controller;

import com.fasulting.common.resp.ResponseBody;
import com.fasulting.domain.ps.ps.dto.reqDto.DoctorReqDto;
import com.fasulting.domain.ps.ps.dto.reqDto.PsDefaultReqDto;
import com.fasulting.domain.ps.ps.dto.reqDto.PsSeqReqDto;
import com.fasulting.domain.ps.ps.dto.reqDto.PsWithoutSeqReqDto;
import com.fasulting.domain.ps.ps.dto.respDto.PsInfoRespDto;
import com.fasulting.domain.ps.ps.service.PsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "병원 계정 관련 API", tags = {"PsController"})
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/ps")
@CrossOrigin("*") // 수정
public class PsController {

    private final PsService psService;

    /**
     * 병원 계정 가입
     *
     * @param psInfo
     * @return fail or success
     */
    @PostMapping("/regist")
    @ApiOperation(value = "병원 계정 회원 가입", notes = "병원 기본 정보, 전문의 리스트 기입하여 회원 가입")
    public ResponseEntity<?> psRegister(@ModelAttribute @ApiParam(value = "회원 가입 정보", required = true) PsWithoutSeqReqDto psInfo) {
        log.info("psRegister - Call");

        log.info(psInfo.toString());
        log.info("profile : " + psInfo.getProfileImg().getOriginalFilename());
        log.info("reg : " + psInfo.getRegistrationImg().getOriginalFilename());

        if (psService.psRegister(psInfo)) {
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }
        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
    }

    /**
     * 이메일 조회 및 중복 확인
     *
     * @param email
     * @return fail or success
     * fail: email 중복
     * sucess: email 중복 아님
     */
    @GetMapping("/{email}")
    @ApiOperation(value = "이메일 조회 및 중복 확인", notes = "이메일 받아서 DB 조회 및 중복 확인")
    public ResponseEntity<?> checkEmail(@PathVariable @ApiParam(value = "회원 이메일", required = true) String email) {
        log.info("ps check Email - Call");
        if (psService.checkEmail(email)) {
            // 이메일 존재
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }


        return ResponseEntity.status(204).body(ResponseBody.create(204, "fail"));
    }

    /**
     * 병원 정보 조회
     *
     * @param psSeq
     * @return 회원 정보 OR fail
     */
    @GetMapping("/info/{psSeq}")
    @ApiOperation(value = "병원 정보 조회", notes = "seq 받아서 병원 정보 조회")
    public ResponseEntity<?> getPsInfo(@PathVariable @ApiParam(value = "Ps seq", required = true) Long psSeq) {
        log.info("getPsInfo - Call");

        PsInfoRespDto resp = psService.getPsInfo(psSeq);

        if (resp != null) {
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success", resp));
        }

        return ResponseEntity.status(204).body(ResponseBody.create(204, "fail"));
    }

    /**
     * 병원 게정 탈퇴
     *
     * @param psInfo (seq)
     * @return success OR fail
     */
    @PatchMapping("/withdraw")
    @ApiOperation(value = "병원 계정 탈퇴", notes = "병원 seq받아 병원 계정 탈퇴 처리")
    public ResponseEntity<?> withdrawPs(@RequestBody @ApiParam(value = "Ps seq", required = true) PsSeqReqDto psInfo) {

        log.info("ps withdraw - Call");

        // 로그인 했는지 검사 필요

        if (psService.withdrawPs(psInfo)) {
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }
        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));

    }

    /**
     * 보안 - 비밀번호 확인 (로그인 상태에서)
     * DB table 안의 기존 비밀 번호 & 새로 설정한 비밀번호 비교
     *
     * @param psInfo (seq, password)
     * @return success OR fail
     */
    @PostMapping("/passcheck")
    @ApiOperation(value = "비밀번호 확인", notes = "병원 seq, 비밀번호 받아 DB의 기존 비밀번호와 비교")
    public ResponseEntity<?> checkPassword(@RequestBody @ApiParam(value = "병원 seq, 비밀번호", required = true) PsSeqReqDto psInfo) {
        log.info("checkPassword - Call");
        // 로그인 했는지 검사 필요
        if (psService.checkPassword(psInfo)) {
            // 비밀 번호 같음
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }
        // 비밀번호 다름
        return ResponseEntity.status(204).body(ResponseBody.create(204, "fail"));
    }

    /**
     * 비밀번호 수정 - 재설정 - 비밀번호만 update
     *
     * @param psInfo
     * @return success OR fail
     * success: update 성공
     * fail: update 실패
     */
    @PatchMapping("/reset")
    @ApiOperation(value = "비밀번호 수정", notes = "병원 이메일, 비밀번호 받아 DB의 기존 비밀번호와 비교")
    public ResponseEntity<?> restPassword(@RequestBody @ApiParam(value = "병원 이메일, 비밀번호", required = true) PsWithoutSeqReqDto psInfo) {
        log.info("ps reset Password - Call");

        if (psService.resetPassword(psInfo)) {
            log.info("성공");
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }

        // 회원 수정 실패
        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
    }

    /**
     * 주소 수정
     *
     * @param psInfo ps seq, 주소
     * @return
     */
    @PutMapping("/address")
    @ApiOperation(value = "주소 수정", notes = "ps seq, 주소 받아 수정")
    public ResponseEntity<?> editAddress(@RequestBody @ApiParam(value = "병원 seq, 주소", required = true) PsSeqReqDto psInfo) {
        log.info("ps edit Address - Call");

        if (psService.editAddress(psInfo)) {
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }
        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
    }

    /**
     * 소개말 수정
     *
     * @param psInfo ps seq, 소개말
     * @return
     */
    @PutMapping("/intro")
    @ApiOperation(value = "소개말 수정", notes = "ps seq, 소개말 받아 수정")
    public ResponseEntity<?> editIntro(@RequestBody @ApiParam(value = "병원 seq, 소개말", required = true) PsSeqReqDto psInfo) {
        log.info("ps edit Intro - Call");

        if (psService.editIntro(psInfo)) {
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }
        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
    }

    /**
     * 번호 수정
     *
     * @param psInfo
     * @return
     */
    @PutMapping("/number")
    @ApiOperation(value = "번호 수정", notes = "ps seq, 번호 받아 수정")
    public ResponseEntity<?> editNumber(@RequestBody @ApiParam(value = "병원 seq, 번호", required = true) PsSeqReqDto psInfo) {
        log.info("ps edit Number - Call");

        if (psService.editNumber(psInfo)) {
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }
        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
    }

    /**
     * 홈페이지 수정
     */
    @PutMapping("/hompage")
    @ApiOperation(value = "홈페이지 수정", notes = "ps seq, 홈페이지 받아 수정")
    public ResponseEntity<?> editHomepage(@RequestBody @ApiParam(value = "병원 seq, 홈페이지", required = true) PsSeqReqDto psInfo) {
        log.info("ps edit homepage - Call");

        if (psService.editHomepage(psInfo)) {
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }
        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
    }

    /**
     * 제공 수술 수정
     */
    @PutMapping("/category")
    @ApiOperation(value = "제공 수술 수정", notes = "ps seq, 제공 수술 (mainCategory) 받아 수정")
    public ResponseEntity<?> editCategory(@RequestBody @ApiParam(value = "병원 seq, 메인 카테고리 리스트", required = true) PsSeqReqDto psInfo) {
        log.info("ps edit Category - Call");

        log.info(psInfo.toString());

        if (psService.editCategory(psInfo)) {
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }
        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
    }

    /**
     * 의사 현황 수정
     */
    @PostMapping("/doctor")
    @ApiOperation(value = "의사 추가", notes = "ps seq, 의사 정보 받아 추가")
    public ResponseEntity<?> addDoctor(@ModelAttribute @ApiParam(value = "병원 seq, 의사 정보(doctor seq, 이미지 | 이름, 전문 분야)", required = true) DoctorReqDto doctor) {
        log.info("ps add Doctor - Call");

        psService.addDoctor(doctor);

        return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));

    }

    /**
     * 의사 삭제
     */
    @DeleteMapping("/doctor/{doctorSeq}")
    @ApiOperation(value = "의사 삭제", notes = "doctor seq 받아 삭제")
    public ResponseEntity<?> deleteDoctor(@PathVariable Long doctorSeq) {
        log.info("ps delete Doctor - Call");

        if (psService.deleteDoctor(doctorSeq)) {
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }
        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
    }

    /**
     * 운영 시간 수정 (설정)
     */
    @PutMapping("/operating")
    @ApiOperation(value = "운영 시간 수정", notes = "운영 시간 정보 받아 수정")
    public ResponseEntity<?> modifyPsDefault(@RequestBody PsDefaultReqDto psDefaultReqDto) {

        log.info("ps modifyPsDefault - call");

        psService.modifyPsDefault(psDefaultReqDto);

        return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));


    }


}
