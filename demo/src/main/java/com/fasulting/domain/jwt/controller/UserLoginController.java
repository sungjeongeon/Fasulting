package com.fasulting.domain.jwt.controller;

import com.fasulting.domain.jwt.dto.reqDto.UserLoginReqDto;
import com.fasulting.domain.jwt.service.UserJwtServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin("*") // 수정
public class UserLoginController {

    private final UserJwtServiceImpl userJwtServiceImpl;

    /**
     * 로그인 - jwt
     *
     * @param userLoginReqDto userEmail & userPassword
     * @return userSeq
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginReqDto userLoginReqDto) {

        Map<String, Object> resultMap = userJwtServiceImpl.login(userLoginReqDto);

        if (resultMap != null) {

            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
        }
        // 로그인 정보가 비어있는 경우
        else {
            resultMap.put("message", "fail");
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
        }
    }

//    @PostMapping("/refresh")
//    public ResponseEntity<?> validateRefreshToken(@RequestBody HashMap<String, String> bodyJson){
//
//        log.info("refresh controller 실행");
//        // refresh 토큰 얻어오기
//        Map<String, String> map = jwtService.validateRefreshToken(bodyJson.get("refreshToken"));
//
//        if(map.get("status").equals("402")){
//            log.info("RefreshController - Refresh Token이 만료.");
//            RefreshApiResponseMessage refreshApiResponseMessage = new RefreshApiResponseMessage(map);
//            return new ResponseEntity<?>(refreshApiResponseMessage, HttpStatus.UNAUTHORIZED);
//        }
//
//        log.info("RefreshController - Refresh Token이 유효.");
//        RefreshApiResponseMessage refreshApiResponseMessage = new RefreshApiResponseMessage(map);
//        return new ResponseEntity<?>(refreshApiResponseMessage, HttpStatus.OK);
//
//    }

}
