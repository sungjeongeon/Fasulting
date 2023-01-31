package com.fasulting.demo.common.email;

import com.fasulting.demo.resp.ResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/email")
@CrossOrigin("*") // 수정
public class EmailController {

    private EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    /**
     * 이메일 인증 코드 발송 (회원 가입)
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
            code = emailService.sendRegistCodeMessage(email);

        } catch (Exception e) {

            log.info(e.getMessage());
            return ResponseEntity.status(400).body(ResponseBody.create(400, "fail"));
        }

        log.info("인증코드: " + code);
        return ResponseEntity.status(200).body("success");
    }

    /**
     * 이메일 인증 코드 발송 (비밀번호 재설정)
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
            code = emailService.sendResetCodeMessage(email);
        } catch (Exception e) {
            log.info(e.getMessage());
            return ResponseEntity.status(400).body(ResponseBody.create(400, "fail"));
        }
        log.info("인증코드: " + code);
        return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
    }

    /**
     * 이메일 인증 코드 확인 (accessCode, email)
     * @return
     */
    @PostMapping("/access")
    public ResponseEntity<?> checkEmailCode() {
        // 이메일 인증 코드 & Server에서 전송한 이메일 코드 일치 여부 확인
        // 인증코드 발송 시 인증코드를 담아서 전송 후 프론트단에서 일치 여부 확인
        return null; // fail OR success
    }
}
