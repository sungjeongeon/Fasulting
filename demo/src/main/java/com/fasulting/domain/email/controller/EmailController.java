package com.fasulting.domain.email.controller;

import com.fasulting.common.resp.ResponseBody;
import com.fasulting.common.util.LogCurrent;
import com.fasulting.domain.email.dto.reqDto.EmailReqDto;
import com.fasulting.domain.email.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.fasulting.common.util.LogCurrent.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    /**
     * 이메일 인증 코드 발송 (회원 가입)
     * @param email
     * @return success OR fail
     * success: 회원 가입 인증 코드 메일 발송 완료
     * fail: 메일 발송 불발
     * @throws Exception
     */
    @GetMapping("/regist/{email}")
    public ResponseEntity<?> registSendEmailCode(@PathVariable String email, HttpServletRequest request){
        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));

        String code = null;
        HttpSession session = request.getSession();

        try {
            code = emailService.sendRegistCodeMessage(email);
            session.setAttribute(email, code);
            // 세션 유지시간 설정(초단위)
            session.setMaxInactiveInterval(3*60); // 3분

        } catch (Exception e) {

            log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
            return ResponseEntity.status(400).body(ResponseBody.create(400, "fail"));
        }

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
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
    public ResponseEntity<? extends ResponseBody> ResetSendEmailCode(@PathVariable String email, HttpServletRequest request){
        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));

        String code = null;
        HttpSession session = request.getSession();

        try {
            code = emailService.sendResetCodeMessage(email);
            session.setAttribute(email, code);
            // 세션 유지시간 설정(초단위)
            session.setMaxInactiveInterval(3*60); // 3분

        } catch (Exception e) {
            log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
            return ResponseEntity.status(400).body(ResponseBody.create(400, "fail"));
        }
        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
    }

    /**
     * 이메일 인증 코드 확인 (accessCode, email)
     * @return
     */
    @PostMapping("/access")
    public ResponseEntity<?> checkEmailCode(@RequestBody EmailReqDto emailReqDto, HttpServletRequest request) {
        // 이메일 인증 코드 & Server에서 전송한 이메일 코드 일치 여부 확인
        // 인증코드 발송 시 인증코드를 담아서 전송 후 프론트단에서 일치 여부 확인

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));

        HttpSession session = request.getSession();
        String code = (String)session.getAttribute(emailReqDto.getEmail());

        if(code.equals(emailReqDto.getEmailCode())){
            session.removeAttribute(emailReqDto.getEmail());
            log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }
        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(400).body(ResponseBody.create(500, "fail"));
    }
}
