package com.fasulting.demo.customer.user.service;

import com.fasulting.demo.customer.user.db.entity.User;
import com.fasulting.demo.customer.user.request.*;
import com.fasulting.demo.customer.user.response.UserInfoResp;

public interface UserService {

    boolean userRegister(UserRegisterReq userRegisterInfo); // 회원가입
    boolean ResetPassword(UserBasicInfoReq userResetInfo); // 비밀번호 재설정
    UserInfoResp GetUserInfo(int userSeq); // 회원 정보 조회
    boolean DupleEmail(String userEmail); // 회원 이메일 중복 확인
    boolean EditUserInfo(int userSeq, EditUserInfoReq userInfo); // 회원 정보 수정
    boolean WidrawUser(WithdrawReq userInfo); //회원 탈퇴
    boolean CheckPassword(CheckPasswordReq userInfo);

}
