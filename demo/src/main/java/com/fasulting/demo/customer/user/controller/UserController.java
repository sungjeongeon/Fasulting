package com.fasulting.demo.customer.user.controller;

import com.fasulting.demo.customer.user.db.entity.User;
import com.fasulting.demo.customer.user.request.*;
import com.fasulting.demo.customer.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;


// >> Spring Security - Filter
// >> Interceptor

// >> id: DB table id
// >> email: user email id

// userDto request 객체로 고치기
// response 객체 따로 고치기
// ***Entity는 Jpa에서 관리하는 객체***

@Slf4j
@RestController
@RequestMapping("/user")
@CrossOrigin("*") // 수정
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 1. 로그인 - jwt
    // userDto: email, password
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        return null; // response: userid (DB table 안의)
    }

    // 2. 로그아웃 - jwt
    @GetMapping("/logout/{userId}")
    public ResponseEntity<?> logout(@PathVariable int userId) {
        return null; // fail OR successs
    }

    // 3. 비밀번호 수정 - 이메일 입력
    @GetMapping("/{userEmail}")
    public ResponseEntity<?> SendEmailCode(@PathVariable String userEmail) {
        log.info("SendEmailCode - Call");

        // 1. 이메일 중복 확인
        // 2. 인증 코드 보내는 로직 들어가야 함 => 서비스로 ^^
        if(userService.SendEmailCode(userEmail)){
            Random random = new Random();
            int authCode = random.nextInt(4589362) + 49311;

            String from = "0613minjeong@gmail.com";
            String to = userEmail;
            String title = "fasulting 회원 가입 인증 메일입니다.";
            String content = "fasulting과 함께 해주셔서 감사합니다." +
                    "<br><br>" +
                    "인증 번호는 " + authCode + "입니다." +
                    "<br>" +
                    "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";

            //

            // return success
        }
        else {
            // return fail
        }

        return null; // fail OR success
    }

    // 이메일 전송 메소드
    public void mailSend(String from, String to, String title, String content) {
        MimeMessage message
    }

    // 4. 비밀번호 수정 - 재설정 - 비밀번호만 update
    // userDto: email, password
    @PatchMapping("/reset")
    public ResponseEntity<?> RestPassword(@RequestBody UserBasicInfoReq userResetInfo) {
        log.info("Reset Password - Call");

        if(userService.ResetPassword(userResetInfo)){
            log.info("성공");
            // return true
        }
        else {
            // return false
        }

        return null; // fail OR success
    }

    // 5. 회원가입
    // userDto: DB user table 다 넣은
    @PostMapping("/regist")
    public ResponseEntity<?> userRegister(@RequestBody UserRegisterReq userInfo) {
        log.info("userRegister - Call");

        userService.userRegister(userInfo);
        return null; // fail OR success
    }

    // 6. 회원가입 - 이메일 중복 확인
    @GetMapping("/duple/{userEmail}")
    public ResponseEntity<?> DupleEmail(@PathVariable String userEmail) {
        log.info("Duple Email - Call");
        if(userService.DupleEmail(userEmail)) {
            // return 중복임
        }

        // userEmail & DB userEmail 비교
        return null; // fail OR success
    }

    // 7. 회원 정보 조회
    @GetMapping("/info/{userId}")
    public ResponseEntity<?> GetUserInfo(@PathVariable int userId) {
        log.info("GetUserInfo - Call");

        if(userService.GetUserInfo(userId) != null){
            log.info(userService.GetUserInfo(userId).toString());
            // return 회원 정보
        }

        return null; // user객체 (회원 정보 다 들어있는)
    }

    // 8. 회원 정보 수정
    // userDto: name, phone

    /**
     *
     * @param userId 유저 아이디
     * @param userInfo 유저 인포
     * @return
     */
    @PutMapping("/edit/{user_id}")
    public ResponseEntity<?> EditUserInfo(@PathVariable("user_id") int userId, @RequestBody EditUserInfoReq userInfo) {

        log.info("EditUser - Call");

        if(userService.EditUserInfo(userId, userInfo)) {
            // return 성공
        }

        return null; // fail
    }

    // 9. 회원 탈퇴
    // userDto: id
    @PatchMapping("/withdraw")
    public ResponseEntity<?> WithdrawUser(@RequestBody WithdrawReq userInfo) {
        log.info("Withdraw - Call");

        if(userService.WidrawUser(userInfo)) {
            // return 성공
        }
        return null; // fail OR success
    }

    // 10. 보안 - 비밀번호 확인 (로그인 상태에서)
    // userDto: id, password
    @PostMapping("/passcheck")
    public ResponseEntity<?> CheckPassword(@RequestBody CheckPasswordReq userInfo) {
        // DB table 안의 기존 비밀 번호 & 새로 설정한 비밀번호 비교

        if(userService.CheckPassword(userInfo)) {
            // return 성공
        }
        return null; // fail OR success
    }

    /////////////////////////////////////////////////////////////////////////////////////

    // 11. 보안 - 인증코드 확인 (수정, 가입)
    // userDto: email
    @PostMapping("/access")
    public ResponseEntity<?> AccessCode(@RequestBody User user) {
        // 이메일 인증 코드 & Server에서 전송한 이메일 코드 일치 여부 확인

        return null; // fail OR success
    }

    // 12. 즐겨찾기 추가
    // userDto: userId, psId
    @PostMapping("/favorite")
    public ResponseEntity<?> AddFavorite(@RequestBody User user) {
        return null; // fail OR success
    }

    // 13. 즐겨찿기 조회
    @GetMapping("/favorite/{userId}")
    public ResponseEntity<?> FavoriteList(@PathVariable int userId) {
        return null; // favorite 객체 리스트
    }

}

// Database 연결