package com.fasulting.demo.ps.ps.service;

import com.fasulting.demo.entity.PsEntity;
import com.fasulting.demo.entity.UserEntity;
import com.fasulting.demo.ps.ps.dto.reqDto.PsSeqReq;
import com.fasulting.demo.ps.ps.dto.reqDto.PsWithoutSeqReq;
import com.fasulting.demo.ps.ps.dto.respDto.PsInfoResp;
import com.fasulting.demo.ps.ps.repository.PsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Slf4j
public class PsServiceImpl implements PsService {

    @Autowired
    private PsRepository psRepository;

    // 병원 회원 가입
    @Override
    public boolean psRegister(PsWithoutSeqReq psInfo) {

        PsEntity ps = PsEntity.builder().email(psInfo.getEmail())
                .password(psInfo.getPassword())
                .name(psInfo.getName())
                .address(psInfo.getAddress())
                .zipcode(psInfo.getZipcode())
                .registration(psInfo.getRegistration())
                .registrationImg(psInfo.getRegistrationImg())
                .number(psInfo.getNumber())
                .director(psInfo.getDirector())
                .homepage(psInfo.getHomepage())
                .profileImg(psInfo.getProfileImg())
                .intro(psInfo.getIntro())
                .build();

        // 병원 카테고리 => main & sub
        // doctor 객체 (name, img, subCategoryList)

        psRepository.save(ps);

        return true;
    }

    // 비밀번호 재설정
    @Transactional
    @Override
    public boolean resetPassword(PsWithoutSeqReq psInfo) {
        if(psRepository.findPsByEmail(psInfo.getEmail()).isPresent()) {
            // email이 있다면 그 email 가진 ps 찾기
            PsEntity ps = psRepository.findPsByEmail(psInfo.getEmail()).get();

            log.info(psInfo.getPassword());

            // password update
            ps.resetPassword(psInfo.getPassword());
            return true;
        }

        return false;
    }

    // 이메일 조회 및 중복 확인
    @Override
    public boolean checkEmail(String email) {
        if(psRepository.findPsByEmail(email).isPresent()) {
            log.info("병원 회원 이메일 존재");
            return true;
        }
        else {
            log.info("병원 회원 이메일 존재하지 않음");
            return false;
        }
    }
    
    // 병원 정보 조회
    @Override
    public PsInfoResp getPsInfo(Long seq) {
        if(psRepository.findById(seq).isPresent()) {
            PsEntity ps = psRepository.findById(seq).get();

            PsInfoResp psInfo = new PsInfoResp();

            psInfo.setName(ps.getName());
            psInfo.setProfileImg(ps.getProfileImg());
            psInfo.setIntro(ps.getIntro());
            psInfo.setAddress(ps.getAddress());
            psInfo.setZipcode(ps.getZipcode());
            psInfo.setNumber(ps.getNumber());
            psInfo.setHomepage(ps.getHomepage());
            psInfo.setDirector(ps.getDirector());
            psInfo.setRegistration(ps.getRegistration());
            psInfo.setRegistrationImg(ps.getRegistrationImg());

            // doctor
            // main, sub category

            return psInfo;
        }
        return null;
    }

    // 병원 회원 정보 수정
    @Override
    @Transactional
    public boolean editPsInfo(PsSeqReq psInfo) {
        Long seq = psInfo.getSeq();
        if(psRepository.findById(seq).isPresent()) {
            PsEntity ps = psRepository.findById(seq).get();

            log.info(psInfo.toString());
            ps.updatePsEntity(psInfo);

            return true;
        }

        return false;
    }

    // 병원 회원 탈퇴
    @Override
    @Transactional
    public boolean withdrawPs(PsSeqReq psInfo) {
        Long seq = psInfo.getSeq();
        if(psRepository.findById(seq).isPresent()) {
            PsEntity ps = psRepository.findById(seq).get();

            ps.withdrawlPs("y", "ps_" + psInfo.getSeq(), LocalDateTime.now());

            return true;
        }

        return false;
    }

    // 비밀번호 재확인 (로그인 상태에서)
    @Override
    public boolean checkPassword(PsSeqReq psInfo) {
        Long seq = psInfo.getSeq();
        if(psRepository.findById(seq).isPresent()) {
            String password = psRepository.findById(seq).get().getPassword();

            if(password.equals(psInfo.getPassword())) {
                return true;
            }
        }

        return false;
    }

}
