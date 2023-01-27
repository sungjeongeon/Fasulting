package com.fasulting.demo.customer.user.controller;

import com.fasulting.demo.resp.ResponseBody;
import com.fasulting.demo.customer.user.dto.reqDto.*;
import com.fasulting.demo.customer.user.service.UserService;
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
     * 1. 로그인 - jwt
     * @param user
     * userEmail & userPassword
     * @return
     * userSeq
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserWithoutSeqReq user) {
        return null; // response: userid (DB table 안의)
    }

    /**
     * 2. 로그아웃 - jwt
     * @param seq
     * @return fail OR success
     */
    @GetMapping("/logout/{seq}")
    public ResponseEntity<?> logout(@PathVariable Long seq) {
        return null; // fail OR successs
    }

    /**
     * 4. 비밀번호 수정 - 재설정 - 비밀번호만 update
     * @param userInfo
     * @return success OR fail
     * success: 재설정 성공
     * fail: 재설정 실패
     */
    @PatchMapping("/reset")
    public ResponseEntity<?> restPassword(@RequestBody UserWithoutSeqReq userInfo) {
        log.info("reset Password - Call");

        if(userService.resetPassword(userInfo)){
            log.info("성공");
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }

        // 회원 수정 실패
        return ResponseEntity.status(200).body(ResponseBody.create(200, "fail"));
    }

    /**
     * 5. 회원가입
     * @param userInfo
     * @return successs OF fail
     * success: 성공, fail: 실패
     */
    @PostMapping("/regist")
    public ResponseEntity<?> userRegister(@RequestBody UserWithoutSeqReq userInfo) {
        log.info("userRegister - Call");

        if(userService.userRegister(userInfo)) {
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }
        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
    }
    

    /**
     * 6. 회원가입 - 이메일 조회 및 확인
     * @param email
     * @return fail or success 
     * fail: email 존재
     * sucess: email 존재 하지 않음
     */
    @GetMapping("/{email}")
    public ResponseEntity<?> checkEmail(@PathVariable String email) {
        log.info("check Email - Call");
        if(userService.checkEmail(email)) {
            // 이메일 존재
            return ResponseEntity.status(200).body(ResponseBody.create(200, "존재하는 이메일입니다."));
        }

        // userEmail & DB userEmail 비교
        return ResponseEntity.status(200).body(ResponseBody.create(200, "존재하지 않는 이메일입니다."));
    }

    /**
     * 7. 회원 정보 조회
     * @param seq
     * @return 회원 정보 OR fail
     */
    @GetMapping("/info/{seq}")
    public ResponseEntity<?> getUserInfo(@PathVariable Long seq) {
        log.info("getUserInfo - Call");

        // 로그인 했는지 검사 필요

        return ResponseEntity.status(200).body(userService.getUserInfo(seq));
    }

    /**
     * 8. 회원 정보 수정
     * @param userInfo 유저 인포 (userName, userPassword)
     * @return success OR fail
     */
//    @PutMapping("/edit")
//    public ResponseEntity<?> editUserInfo(@RequestBody UserSeqReq userInfo) {
//        log.info("editUser - Call");
//
//        // 로그인 했는지 검사 필요
//
//        if(userService.editUserInfo(userInfo.getSeq(), userInfo)) {
//            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
//        }
//        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
//    }

    /**
     * 9. 회원 탈퇴
     * @param userInfo (userSeq)
     * @return success OR fail
     */
    @PatchMapping("/withdraw")
    public ResponseEntity<?> withdrawUser(@RequestBody UserSeqReq userInfo) {
        log.info("withdraw - Call");

        // 로그인 했는지 검사 필요

        if(userService.withdrawUser(userInfo)) {
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }
        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
    }

    /**
     * 10. 보안 - 비밀번호 확인 (로그인 상태에서)
     * DB table 안의 기존 비밀 번호 & 새로 설정한 비밀번호 비교
     * @param userInfo (userSeq, userPassword)
     * @return success OR fail
     */
    @PostMapping("/passcheck")
    public ResponseEntity<?> checkPassword(@RequestBody UserSeqReq userInfo) {
        log.info("checkPassword - Call");
        // 로그인 했는지 검사 필요
        if(userService.checkPassword(userInfo)) {
            // 비밀 번호 같음
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }
        // 비밀번호 다름
        return ResponseEntity.status(500).body(ResponseBody.create(200, "fail"));
    }

    // 12. 즐겨찾기 추가
    // userDto: userSeq, psId
    @PostMapping("/favorite")
    public ResponseEntity<?> addFavorite(@RequestBody UserSeqReq userInfo) {
        return null; // fail OR success
    }

    // 13. 즐겨찿기 조회
    @GetMapping("/favorite/{seq}")
    public ResponseEntity<?> favoriteList(@PathVariable Long seq) {
        return null; // favorite 객체 리스트
    }

}

// Database 연결