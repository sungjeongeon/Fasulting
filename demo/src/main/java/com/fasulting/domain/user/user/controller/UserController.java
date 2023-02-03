package com.fasulting.domain.user.user.controller;

import com.fasulting.common.resp.ResponseBody;
import com.fasulting.domain.user.user.dto.reqDto.UserSeqReqDto;
import com.fasulting.domain.user.user.dto.reqDto.UserWithoutSeqReqDto;
import com.fasulting.domain.user.user.dto.respDto.UserInfoRespDto;
import com.fasulting.domain.user.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


// >> Spring Security - Filter
// >> Interceptor

// >> seq: DB table seq
// >> email: user email id

// userDto request 객체로 고치기
// response 객체 따로 고치기
// ***Entity는 Jpa에서 관리하는 객체***

@Api(value = "상담자 계정 관련 API", tags = {"UserController"})
@Slf4j
@RestController
@RequestMapping("/user")
@CrossOrigin("*") // 수정
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 로그인 - jwt
     * @param userIngo
     * userEmail & userPassword
     * @return
     * userSeq
     */
    @PostMapping("/login")
    @ApiOperation(value = "상담자 계정 로그인", notes = "..")
    public ResponseEntity<?> login(@RequestBody @ApiParam(value = "로그인 정보", required = true) UserWithoutSeqReqDto userInfo) {
        log.info("user login - Call");

        UserInfoRespDto user = userService.login(userInfo);

        if(user != null) {
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success", user));
        }
        return ResponseEntity.status(204).body(ResponseBody.create(204, "fail"));

    }

    /**
     * 로그아웃 - jwt
     * @param seq
     * @return fail OR success
     */
    @GetMapping("/logout/{seq}")
    @ApiOperation(value = "상담자 계정 로그아웃", notes = "..")
    public ResponseEntity<?> logout(@PathVariable @ApiParam(value = "로그인 정보", required = true) Long seq) {
        
        // ㅂㅂ
        
        return null; // fail OR successs
    }

    /**
     * 회원가입
     * @param userInfo
     * @return successs OF fail
     * success: 성공, fail: 실패
     */
    @PostMapping("/regist")
    @ApiOperation(value = "상담자 계정 회원 가입", notes = "기본 정보 기입하여 회원 가입")
    public ResponseEntity<?> userRegister(@RequestBody  @ApiParam(value = "회원 가입 정보", required = true) UserWithoutSeqReqDto userInfo) {
        log.info("userRegister - Call");

        if(userService.userRegister(userInfo)) {
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }
        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
    }
    

    /**
     * 회원가입 - 이메일 조회 및 확인
     * @param email
     * @return fail or success 
     * fail: email 존재
     * sucess: email 존재 하지 않음
     */
    @GetMapping("/{email}")
    @ApiOperation(value = "이메일 조회 및 중복 확인", notes = "이메일 받아서 DB 조회 및 중복 확인")
    public ResponseEntity<?> checkEmail(@PathVariable @ApiParam(value = "회원 이메일", required = true) String email) {
        log.info("check Email - Call");
        if(userService.checkEmail(email)) {
            // 이메일 존재
            return ResponseEntity.status(200).body(ResponseBody.create(200, "존재하는 이메일입니다."));
        }

        // userEmail & DB userEmail 비교
        return ResponseEntity.status(200).body(ResponseBody.create(200, "존재하지 않는 이메일입니다."));
    }

    /**
     * 비밀번호 수정 - 재설정 - 비밀번호만 update
     * 이전과 같은 비밀번호가 들어오는 경우 fail
     * @param userInfo
     * @return success OR fail
     */
    @PatchMapping("/reset")
    @ApiOperation(value = "비밀번호 수정", notes = "..")
    public ResponseEntity<?> restPassword(@RequestBody @ApiParam(value = "로그아웃 정보 (email, password)", required = true) UserWithoutSeqReqDto userInfo) {
        log.info("reset Password - Call");

        if(userService.resetPassword(userInfo)){
            log.info("성공");
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }

        // 회원 수정 실패
        return ResponseEntity.status(200).body(ResponseBody.create(200, "fail"));
    }

    /**
     * 회원 정보 조회
     * @param seq
     * @return 회원 정보 OR fail
     */
    @GetMapping("/info/{seq}")
    @ApiOperation(value = "회원 정보 조회", notes = "seq 받아서 회원 정보 조회")
    public ResponseEntity<?> getUserInfo(@PathVariable @ApiParam(value = "User seq", required = true)  Long seq) {
        log.info("getUserInfo - Call");

        // 로그인 했는지 검사 필요

        UserInfoRespDto userInfo = userService.getUserInfo(seq);

        if(userInfo != null) {
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success", userInfo));
        }

        return ResponseEntity.status(204).body(ResponseBody.create(204, "fail"));
    }

    /**
     * 회원 탈퇴
     * @param userInfo (userSeq)
     * @return success OR fail
     */
    @PatchMapping("/withdraw")
    @ApiOperation(value = "회원 탈퇴", notes = "회원 seq받아 탈퇴 처리")
    public ResponseEntity<?> withdrawUser(@RequestBody @ApiParam(value = "User seq", required = true) UserSeqReqDto userInfo) {
        log.info("withdraw - Call");

        // 로그인 했는지 검사 필요

        if(userService.withdrawUser(userInfo)) {
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }
        return ResponseEntity.status(204).body(ResponseBody.create(204, "fail"));
    }

    /**
     * 보안 - 비밀번호 확인 (로그인 상태에서)
     * @param userInfo (userSeq, userPassword)
     * @return success OR fail
     */
    @PostMapping("/passcheck")
    @ApiOperation(value = "비밀번호 확인", notes = "User seq, 비밀번호 받아 DB의 기존 비밀번호와 비교")
    public ResponseEntity<?> checkPassword(@RequestBody @ApiParam(value = "User seq, 비밀번호", required = true) UserSeqReqDto userInfo) {
        log.info("checkPassword - Call");
        // 로그인 했는지 검사 필요
        if(userService.checkPassword(userInfo)) {
            // 비밀 번호 같음
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }
        // 비밀번호 다름
        return ResponseEntity.status(500).body(ResponseBody.create(200, "fail"));
    }


//    /**
//     * 회원 정보 수정
//     * @param userInfo 유저 인포 (userName, userPassword)
//     * @return success OR fail
//     */
//    @ApiOperation(value = "회원 정보 수정", notes = "수정 정보 받아 정보 수정")
//    @PutMapping("/edit")
//    public ResponseEntity<?> editUserInfo(@RequestBody @ApiParam(value = "회원 수정 정보", required = true)  UserSeqReq userInfo) {
//        log.info("editUser - Call");
//
//        // 로그인 했는지 검사 필요
//
//        if(userService.editUserInfo(userInfo.getSeq(), userInfo)) {
//            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
//        }
//        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
//    }

}
