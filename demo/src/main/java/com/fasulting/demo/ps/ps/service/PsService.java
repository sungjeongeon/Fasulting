package com.fasulting.demo.ps.ps.service;

import com.fasulting.demo.ps.ps.dto.reqDto.DoctorReq;
import com.fasulting.demo.ps.ps.dto.reqDto.PsDefaultReq;
import com.fasulting.demo.ps.ps.dto.reqDto.PsSeqReq;
import com.fasulting.demo.ps.ps.dto.reqDto.PsWithoutSeqReq;
import com.fasulting.demo.ps.ps.dto.respDto.PsInfoRespDto;

public interface PsService {

    boolean psRegister(PsWithoutSeqReq psInfo); // 회원가입
    boolean resetPassword(PsWithoutSeqReq psResetInfo); // 비밀번호 재설정
    boolean withdrawPs(PsSeqReq psInfo); // 병원 회원 탈퇴
    boolean checkPassword(PsSeqReq psInfo); // 비밀번호 재확인 (로그인 상태에서)

    boolean checkEmail(String email); // 병원 회원 이메일 조회 및 중복 확인

    boolean editAddress(PsSeqReq psInfo); // 병원 주소 수정
    boolean editIntro(PsSeqReq psInfo); // 병원 소개말 수정
    boolean editNumber(PsSeqReq psInfo);  // 병원 연락처 수정
    boolean editHomepage(PsSeqReq psInfo); // 병원 홈페이지 수정
    boolean editCategory(PsSeqReq psInfo); // 병원 제공 수술 수정
    boolean addDoctor(DoctorReq doctor); // 의사 추가
    boolean deleteDoctor(Long doctorSeq); // 의사 삭제
    boolean modifyPsDefault(PsDefaultReq psDefaultReq); // 운영 시간 수정

    PsInfoRespDto getPsInfo(Long psSeq); // 병원 회원 정보 조회

}
