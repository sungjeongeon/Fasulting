package com.fasulting.demo.customer.user.controller;

import com.fasulting.demo.common.ResponseBody;
import com.fasulting.demo.customer.user.dto.reqDto.*;
import com.fasulting.demo.customer.user.service.UserEmailService;
import com.fasulting.demo.customer.user.service.UserService;
import com.fasulting.demo.entity.UserEntity;
import com.fasulting.demo.ps.ps.request.CheckPasswordReq;
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
    private final UserEmailService userEmailService;

    @Autowired
    public UserController(UserService userService, UserEmailService userEmailService) {
        this.userService = userService;
        this.userEmailService = userEmailService;
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
     * 3. 이메일 인증 코드 발송 (회원 가입)
     * @param email
     * @return success OR fail
     * success: 회원 가입 인증 코드 메일 발송 완료
     * fail: 메일 발송 불발
     * @throws Exception
     */
    @GetMapping("/regist/{email}")
    public ResponseEntity<?> registSendEmailCode(@PathVariable String email){
        log.info("SendEmailCode - Call");

        String code = null;
        try {
            code = userEmailService.sendRegistCodeMessage(email);

        } catch (Exception e) {

            log.info(e.getMessage());
            return ResponseEntity.status(400).body(ResponseBody.create(400, "success"));
        }

        log.info("인증코드: " + code);
        return ResponseEntity.status(200).body("success");
    }

    /**
     * 3-1. 이메일 인증 코드 발송 (비밀번호 재설정)
     * @param email
     * @return success OR fail
     * success: 회원 가입 인증 코드 메일 발송 완료
     * fail: 메일 발송 불발
     * @throws Exception
     */
    @GetMapping("/reset/{email}")
    public ResponseEntity<? extends ResponseBody> ResetSendEmailCode(@PathVariable String email){
        log.info("SendEmailCode - Call");

        String code = null;
        try {
            code = userEmailService.sendResetCodeMessage(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("인증코드: " + code);
        return ResponseEntity.status(200).body(ResponseBody.create(200, "sueccess"));
    }

    /**
     * 4. 비밀번호 수정 - 재설정 - 비밀번호만 update
     * @param userInfo
     * @return success OR fail
     * success: 가입 성공
     * fail: 가입 실패
     */
    @PatchMapping("/reset")
    public ResponseEntity<?> restPassword(@RequestBody UserWithoutSeqReq userInfo) {
        log.info("Reset Password - Call");

        if(userService.resetPassword(userInfo)){
            log.info("성공");
            return ResponseEntity.status(200).body(ResponseBody.create(200, "sueccess"));
        }

        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
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
            return ResponseEntity.status(200).body(ResponseBody.create(200, "sueccess"));
        }
        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
    }
    

    /**
     * 6. 회원가입 - 이메일 중복 확인
     * @param email
     * @return fail or success 
     * fail: userEmail 중복
     * sucess: userEmail 중복 아님
     */
    @GetMapping("/duple/{email}")
    public ResponseEntity<?> checkEmail(@PathVariable String email) {
        log.info("Duple Email - Call");
        if(userService.checkEmail(email)) {
            // 이메일 중복
            return ResponseEntity.status(409).body(ResponseBody.create(409, "fail"));
        }

        // userEmail & DB userEmail 비교
        return ResponseEntity.status(200).body(ResponseBody.create(200, "success")); 
    }

    /**
     * 7. 회원 정보 조회
     * @param seq
     * @return 회원 정보 OR fail
     */
    @GetMapping("/info/{seq}")
    public ResponseEntity<?> getUserInfo(@PathVariable Long seq) {
        log.info("GetUserInfo - Call");

        // 로그인 했는지 검사 필요

        return ResponseEntity.status(200).body(userService.getUserInfo(seq));
    }

    /**
     * 8. 회원 정보 수정
     * @param seq 유저 아이디
     * @param userInfo 유저 인포 (userName, userPassword)
     * @return success OR fail
     */
    @PutMapping("/edit/{seq}")
    public ResponseEntity<?> editUserInfo(@PathVariable("seq") Long seq, @RequestBody UserSeqReq userInfo) {
        log.info("EditUser - Call");

        // 로그인 했는지 검사 필요

        if(userService.editUserInfo(seq, userInfo)) {
            return ResponseEntity.status(200).body(ResponseBody.create(200, "sueccess"));
        }
        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
    }

    /**
     * 9. 회원 탈퇴
     * @param userInfo (userSeq)
     * @return success OR fail
     */
    @PatchMapping("/withdraw")
    public ResponseEntity<?> withdrawUser(@RequestBody UserSeqReq userInfo) {
        log.info("Withdraw - Call");

        // 로그인 했는지 검사 필요

        if(userService.withdrawUser(userInfo)) {
            return ResponseEntity.status(200).body(ResponseBody.create(200, "sueccess"));
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
        // 로그인 했는지 검사 필요
        if(userService.checkPassword(userInfo)) {
            return ResponseEntity.status(200).body(ResponseBody.create(200, "sueccess"));
        }
        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
    }

    /////////////////////////////////////////////////////////////////////////////////////

    // 11. 보안 - 인증코드 확인 (수정, 가입)
    // userDto: email
    @PostMapping("/access")
    public ResponseEntity<?> accessCode(@RequestBody UserSeqReq userInfo) {
        // 이메일 인증 코드 & Server에서 전송한 이메일 코드 일치 여부 확인

        return null; // fail OR success
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