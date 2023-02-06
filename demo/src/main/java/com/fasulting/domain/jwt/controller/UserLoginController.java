package com.fasulting.domain.jwt.controller;

import com.fasulting.domain.jwt.dto.reqDto.UserLoginReqDto;
import com.fasulting.domain.jwt.service.UserJwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin("*") // 수정
public class UserLoginController {

    private final UserJwtService userJwtService;

    /**
     * 로그인 - jwt
     *
     * @param userLoginReqDto userEmail & userPassword
     * @return userSeq
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginReqDto userLoginReqDto, HttpServletResponse response) {

        Map<String, String> map = userJwtService.login(userLoginReqDto);

        if (map != null) {

            // JWT 쿠키 저장(쿠키 명 : token)
            Cookie cookie = new Cookie("refreshToken", map.get("refresh-token"));
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60 * 24 * 1); // 유효기간 1일
            // httoOnly 옵션을 추가해 서버만 쿠키에 접근할 수 있게 설정
            cookie.setHttpOnly(true);
            response.addCookie(cookie);

            return new ResponseEntity<Map<String, String>>(map, HttpStatus.ACCEPTED);
        }
        // 로그인 정보가 비어있는 경우
        else {
            map.put("message", "fail");
            return new ResponseEntity<Map<String, String>>(map, HttpStatus.ACCEPTED);
        }
    }

}
