package com.fasulting.demo.customer.user.controller;

import com.fasulting.demo.common.ResponseBody;
import com.fasulting.demo.customer.user.db.entity.User;
import com.fasulting.demo.customer.user.request.*;
import com.fasulting.demo.customer.user.service.UserEmailService;
import com.fasulting.demo.customer.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<?> login(@RequestBody User user) {
        return null; // response: userid (DB table 안의)
    }

    /**
     * 2. 로그아웃 - jwt
     * @param userSeq
     * @return fail OR success
     */
    @GetMapping("/logout/{userSeq}")
    public ResponseEntity<?> logout(@PathVariable int userSeq) {
        return null; // fail OR successs
    }

    /**
     * 3. 이메일 인증 코드 발송 (회원 가입)
     * @param userEmail
     * @return success OR fail
     * success: 회원 가입 인증 코드 메일 발송 완료
     * fail: 메일 발송 불발
     * @throws Exception
     */
    @GetMapping("/regist/{userEmail}")
    public ResponseEntity<? extends ResponseBody> RegistSendEmailCode(@PathVariable String userEmail) throws Exception {
        log.info("SendEmailCode - Call");

        String code = userEmailService.sendRegistCodeMessage(userEmail);
        log.info("인증코드: " + code);
        return ResponseEntity.status(200).body(ResponseBody.create(200, "sueccess"));
    }

    /**
     * 3-1. 이메일 인증 코드 발송 (비밀번호 재설정)
     * @param userEmail
     * @return success OR fail
     * success: 회원 가입 인증 코드 메일 발송 완료
     * fail: 메일 발송 불발
     * @throws Exception
     */
    @GetMapping("/reset/{userEmail}")
    public ResponseEntity<? extends ResponseBody> ResetSendEmailCode(@PathVariable String userEmail) throws Exception {
        log.info("SendEmailCode - Call");

        String code = userEmailService.sendResetCodeMessage(userEmail);
        log.info("인증코드: " + code);
        return ResponseEntity.status(200).body(ResponseBody.create(200, "sueccess"));
    }

    /**
     * 4. 비밀번호 수정 - 재설정 - 비밀번호만 update
     * @param userResetInfo
     * @return success OR fail
     * success: 가입 성공
     * fail: 가입 실패
     */
    @PatchMapping("/reset")
    public ResponseEntity<?> RestPassword(@RequestBody UserBasicInfoReq userResetInfo) {
        log.info("Reset Password - Call");

        if(userService.ResetPassword(userResetInfo)){
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
    public ResponseEntity<?> userRegister(@RequestBody UserRegisterReq userInfo) {
        log.info("userRegister - Call");

        if(userService.userRegister(userInfo)) {
            return ResponseEntity.status(200).body(ResponseBody.create(200, "sueccess"));
        }
        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
    }
    

    /**
     * 6. 회원가입 - 이메일 중복 확인
     * @param userEmail
     * @return fail or success 
     * fail: userEmail 중복
     * sucess: userEmail 중복 아님
     */
    @GetMapping("/duple/{userEmail}")
    public ResponseEntity<?> DupleEmail(@PathVariable String userEmail) {
        log.info("Duple Email - Call");
        if(userService.DupleEmail(userEmail)) {
            // 이메일 중복
            return ResponseEntity.status(409).body(ResponseBody.create(409, "fail"));
        }

        // userEmail & DB userEmail 비교
        return ResponseEntity.status(200).body(ResponseBody.create(200, "success")); 
    }

    /**
     * 7. 회원 정보 조회
     * @param userSeq
     * @return 회원 정보 OR fail
     */
    @GetMapping("/info/{userId}")
    public ResponseEntity<?> GetUserInfo(@PathVariable int userSeq) {
        log.info("GetUserInfo - Call");

        // 로그인 했는지 검사 필요

        return ResponseEntity.status(200).body(userService.GetUserInfo(userSeq));
    }

    /**
     * 8. 회원 정보 수정
     * @param userSeq 유저 아이디
     * @param userInfo 유저 인포 (userName, userPassword)
     * @return success OR fail
     */
    @PutMapping("/edit/{user_id}")
    public ResponseEntity<?> EditUserInfo(@PathVariable("user_id") int userSeq, @RequestBody EditUserInfoReq userInfo) {
        log.info("EditUser - Call");

        // 로그인 했는지 검사 필요

        if(userService.EditUserInfo(userSeq, userInfo)) {
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
    public ResponseEntity<?> WithdrawUser(@RequestBody WithdrawReq userInfo) {
        log.info("Withdraw - Call");

        // 로그인 했는지 검사 필요

        if(userService.WidrawUser(userInfo)) {
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
    public ResponseEntity<?> CheckPassword(@RequestBody CheckPasswordReq userInfo) {
        // 로그인 했는지 검사 필요
        if(userService.CheckPassword(userInfo)) {
            return ResponseEntity.status(200).body(ResponseBody.create(200, "sueccess"));
        }
        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
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
    // userDto: userSeq, psId
    @PostMapping("/favorite")
    public ResponseEntity<?> AddFavorite(@RequestBody User user) {
        return null; // fail OR success
    }

    // 13. 즐겨찿기 조회
    @GetMapping("/favorite/{userSeq}")
    public ResponseEntity<?> FavoriteList(@PathVariable int userSeq) {
        return null; // favorite 객체 리스트
    }

}

// Database 연결