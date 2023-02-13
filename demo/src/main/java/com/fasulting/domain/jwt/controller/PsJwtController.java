package com.fasulting.domain.jwt.controller;

import com.fasulting.common.resp.ResponseBody;
import com.fasulting.common.util.CookieUtil;
import com.fasulting.common.util.LogCurrent;
import com.fasulting.domain.jwt.dto.reqDto.LoginReqDto;
import com.fasulting.domain.jwt.dto.respDtio.PsLoginRespDto;
import com.fasulting.domain.jwt.service.PsJwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.fasulting.common.util.LogCurrent.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/ps")
public class PsJwtController {

    private final PsJwtService psJwtService;

    /**
     * ps login
     * @param loginReqDto
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReqDto loginReqDto, HttpServletRequest request, HttpServletResponse response) {

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));
        PsLoginRespDto psLoginRespDto = psJwtService.login(loginReqDto);

        if (psLoginRespDto != null) {

            // 기존 쿠키 삭제
            CookieUtil.deleteCookie(request, response, "refreshToken");
            // JWT 쿠키 저장(쿠키 명 : token)
            CookieUtil.addCookie(response, "refreshToken", psLoginRespDto.getRefreshToken());

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.AUTHORIZATION, psLoginRespDto.getAccessToken());

            log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
            return ResponseEntity.status(200).headers(headers).body(ResponseBody.create(200, "success", psLoginRespDto));
        }
        // 로그인 정보가 비어있는 경우
        else {
            log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
            return ResponseEntity.status(204).body(ResponseBody.create(204, "fail"));
        }
    }

    /**
     * ps logout
     * @param psSeq
     * @param request
     * @return
     */
    @GetMapping("/logout/{psSeq}")
    public ResponseEntity<?> logout(@PathVariable Long psSeq, HttpServletRequest request) {

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));
        String accessToken = request.getHeader("Authorization");

        if (psJwtService.logout(psSeq)) {
            HttpSession session = request.getSession();
            session.setAttribute(accessToken, accessToken);
            session.setMaxInactiveInterval(30 * 60);
            log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }
        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(403).body(ResponseBody.create(403, "fail"));
    }
}
