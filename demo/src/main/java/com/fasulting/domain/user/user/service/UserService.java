package com.fasulting.domain.user.user.service;

import com.fasulting.domain.user.user.dto.reqDto.UserSeqReqDto;
import com.fasulting.domain.user.user.dto.reqDto.UserWithoutSeqReqDto;
import com.fasulting.domain.user.user.dto.respDto.UserInfoRespDto;

public interface UserService {

    boolean userRegister(UserWithoutSeqReqDto userRegisterInfo); // 회원가입

    boolean resetPassword(UserWithoutSeqReqDto userResetInfo); // 비밀번호 재설정

    UserInfoRespDto getUserInfo(Long seq); // 회원 정보 조회

    boolean withdrawUser(UserSeqReqDto userInfo); //회원 탈퇴

    boolean checkPassword(UserSeqReqDto userInfo); // 비밀번호 확인(로그인 상태에서)

    boolean checkEmail(String email); // 회원 이메일 조회 및 중복 확인

    UserInfoRespDto login(UserWithoutSeqReqDto userInfo);

    //    boolean editUserInfo(Long seq, UserSeqReq userInfo); // 회원 정보 수정
}
