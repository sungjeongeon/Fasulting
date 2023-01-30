package com.fasulting.demo.ps.ps.service;

import com.fasulting.demo.ps.ps.dto.reqDto.DoctorReq;
import com.fasulting.demo.ps.ps.dto.reqDto.PsSeqReq;
import com.fasulting.demo.ps.ps.dto.reqDto.PsWithoutSeqReq;
import com.fasulting.demo.ps.ps.dto.respDto.PsInfoResp;
import org.springframework.web.multipart.MultipartFile;

public interface PsService {

    boolean psRegister(PsWithoutSeqReq psInfo); // 회원가입
    boolean resetPassword(PsWithoutSeqReq psResetInfo); // 비밀번호 재설정

    PsInfoResp getPsInfo(Long seq); // 병원 회원 정보 조회
    boolean editPsInfo(PsSeqReq psInfo); // 병원 정보 수정
    boolean withdrawPs(PsSeqReq psInfo); // 병원 회원 탈퇴
    boolean checkPassword(PsSeqReq psInfo); // 비밀번호 재확인 (로그인 상태에서)

    boolean checkEmail(String email); // 병원 회원 이메일 조회 및 중복 확인

    boolean editAddress(PsSeqReq psInfo); // 병원 주소 수정
    boolean editIntro(PsSeqReq psInfo); // 병원 소개말 수정
    boolean editNumber(PsSeqReq psInfo);  // 병원 연락처 수정
    boolean editHomepage(PsSeqReq psInfo); // 병원 홈페이지 수정
    boolean editCategory(PsSeqReq psInfo); // 병원 제공 수술 수정
    boolean editDoctor(DoctorReq doctor); // 의사 현황 수정


}
