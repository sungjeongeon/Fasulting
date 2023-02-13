package com.fasulting.domain.jwt.controller;

import com.fasulting.common.resp.ResponseBody;
import com.fasulting.domain.jwt.dto.reqDto.LoginReqDto;
import com.fasulting.domain.jwt.dto.respDtio.PsLoginRespDto;
import com.fasulting.domain.jwt.service.PsJwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/ps")
@CrossOrigin("*") // 수정
public class PsJwtController {

    private final PsJwtService psJwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReqDto loginReqDto, HttpServletResponse response) {

        log.info("ps - login controller");

        PsLoginRespDto psLoginRespDto = psJwtService.login(loginReqDto);

        if (psLoginRespDto != null) {

            // JWT 쿠키 저장(쿠키 명 : token)
            Cookie cookie = new Cookie("refreshToken", psLoginRespDto.getRefreshToken());
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60 * 24 * 1); // 유효기간 1일
            // httpOnly 옵션을 추가해 서버만 쿠키에 접근할 수 있게 설정
            cookie.setHttpOnly(true);
            response.addCookie(cookie);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.AUTHORIZATION, psLoginRespDto.getAccessToken());

            return ResponseEntity.status(200).headers(headers).body(ResponseBody.create(200, "success", psLoginRespDto));
        }
        // 로그인 정보가 비어있는 경우
        else {
            return ResponseEntity.status(403).body(ResponseBody.create(403, "fail"));
        }
    }

    @GetMapping("/logout/{psSeq}")
    public ResponseEntity<?> logout(@PathVariable Long psSeq, HttpServletRequest request) {

        log.info("ps - logout controller");

        String accessToken = request.getHeader("Authorization");

        if(psJwtService.logout(psSeq)){
            HttpSession session = request.getSession();
            session.setAttribute(accessToken, accessToken);
            session.setMaxInactiveInterval(30 * 60);
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }

        return ResponseEntity.status(403).body(ResponseBody.create(403, "fail"));
    }
}
