package com.fasulting.domain.user.user.controller;

import com.fasulting.common.jwt.JwtTokenProvider;
import com.fasulting.common.resp.ResponseBody;
import com.fasulting.domain.user.user.dto.reqDto.UserLoginReqDto;
import com.fasulting.domain.user.user.service.UserLoginService;
import com.fasulting.entity.user.UserEntity;
import com.fasulting.repository.user.UserRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin("*") // 수정
public class UserLoginController {
    private final UserRepository userRepository;

    private final JwtTokenProvider jwtTokenProvider;
    private final UserLoginService loginService;

    /**
     * 로그인 - jwt
     * @param userInfo
     * userEmail & userPassword
     * @return
     * userSeq
     */
    @PostMapping("/login")
    @ApiOperation(value = "상담자 계정 로그인", notes = "..")
    public ResponseEntity<?> login(@RequestBody @ApiParam(value = "로그인 정보", required = true) UserLoginReqDto userInfo) {
        log.info("user login - Call");

        UserEntity user = loginService.login(userInfo);

        if(user != null) {
            String token = jwtTokenProvider.createJwtAccessToken(user.getEmail(), user.getRole().getAuthority());
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success", token));
        }
        return ResponseEntity.status(204).body(ResponseBody.create(204, "fail"));

    }

}
