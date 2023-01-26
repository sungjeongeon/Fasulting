package com.fasulting.demo.customer.user.service;

import com.fasulting.demo.customer.user.dto.reqDto.*;
import com.fasulting.demo.customer.user.dto.respDto.UserInfoResp;

public interface UserService {

    boolean userRegister(UserWithoutSeqReq userRegisterInfo); // 회원가입
    boolean resetPassword(UserWithoutSeqReq userResetInfo); // 비밀번호 재설정

    UserInfoResp getUserInfo(Long seq); // 회원 정보 조회
    boolean editUserInfo(Long seq, UserSeqReq userInfo); // 회원 정보 수정
    boolean withdrawUser(UserSeqReq userInfo); //회원 탈퇴
    boolean checkPassword(UserSeqReq userInfo); // 비밀번호 확인(로그인 상태에서)


    boolean checkEmail(String email); // 회원 이메일 조회 및 중복 확인
}
