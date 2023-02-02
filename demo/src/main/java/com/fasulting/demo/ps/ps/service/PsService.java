package com.fasulting.demo.ps.ps.service;

import com.fasulting.demo.ps.ps.dto.reqDto.DoctorReqDto;
import com.fasulting.demo.ps.ps.dto.reqDto.PsDefaultReqDto;
import com.fasulting.demo.ps.ps.dto.reqDto.PsSeqReqDto;
import com.fasulting.demo.ps.ps.dto.reqDto.PsWithoutSeqReqDto;
import com.fasulting.demo.ps.ps.dto.respDto.PsInfoRespDto;
import com.fasulting.demo.ps.ps.dto.respDto.PsLoginRespDto;

public interface PsService {

    boolean psRegister(PsWithoutSeqReqDto psInfo); // 회원가입
    boolean resetPassword(PsWithoutSeqReqDto psResetInfo); // 비밀번호 재설정
    boolean withdrawPs(PsSeqReqDto psInfo); // 병원 회원 탈퇴
    boolean checkPassword(PsSeqReqDto psInfo); // 비밀번호 재확인 (로그인 상태에서)

    boolean checkEmail(String email); // 병원 회원 이메일 조회 및 중복 확인

    boolean editAddress(PsSeqReqDto psInfo); // 병원 주소 수정
    boolean editIntro(PsSeqReqDto psInfo); // 병원 소개말 수정
    boolean editNumber(PsSeqReqDto psInfo);  // 병원 연락처 수정
    boolean editHomepage(PsSeqReqDto psInfo); // 병원 홈페이지 수정
    boolean editCategory(PsSeqReqDto psInfo); // 병원 제공 수술 수정
    boolean addDoctor(DoctorReqDto doctor); // 의사 추가
    boolean deleteDoctor(Long doctorSeq); // 의사 삭제
    boolean modifyPsDefault(PsDefaultReqDto psDefaultReqDto); // 운영 시간 수정

    PsInfoRespDto getPsInfo(Long psSeq); // 병원 회원 정보 조회

    PsLoginRespDto login(PsWithoutSeqReqDto psInfo);
}
