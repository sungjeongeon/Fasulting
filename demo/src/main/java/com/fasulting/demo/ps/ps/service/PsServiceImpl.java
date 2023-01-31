package com.fasulting.demo.ps.ps.service;

import com.fasulting.demo.entity.*;
import com.fasulting.demo.entity.compositeId.DoctorMainId;
import com.fasulting.demo.ps.ps.dto.reqDto.DoctorReq;
import com.fasulting.demo.ps.ps.dto.reqDto.PsSeqReq;
import com.fasulting.demo.ps.ps.dto.reqDto.PsWithoutSeqReq;
import com.fasulting.demo.ps.ps.dto.respDto.PsInfoResp;
import com.fasulting.demo.ps.ps.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class PsServiceImpl implements PsService {

    @Autowired
    private PsRepository psRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private MainCategoryRepository mainRepository;
    @Autowired
    private SubCategoryRepository subRepository;
    @Autowired
    private PsMainRepository psMainRepository;;
    @Autowired
    private PsMainSubRepository psMainSubRepository;
    @Autowired
    private DoctorMainRepository doctorMainRepository;

    // 배포할 때 경로 바꾸기
    private final String dirPath = "C:/fasulting/ps/files";

    private final String domain = "https://localhost:8080/resources/upload/";

    private String uploadFile(UUID uuid, MultipartFile imgFile, String imgUrl) {
        File folder = new File(dirPath);
        if(!folder.exists()) folder.mkdirs(); // 폴더 생성

        String imgSaveUrl = uuid + "_" + imgFile.getOriginalFilename();
        File file = new File(dirPath + File.separator + imgSaveUrl); // profileImgSaveUrl 경로 이용해서 폴더 만듬
        try {
            imgFile.transferTo(file); // 이미지 최종 경로로 보내줘서 저장
            return dirPath + File.separator + imgSaveUrl;
        } catch (IOException e) {
            log.info(e.getMessage());
        }
        return null;
    }

    // 병원 회원 가입
    @Override
    public boolean psRegister(PsWithoutSeqReq psInfo) {

        /////////////// 병원 저장 ///////////////
        MultipartFile profileImgFile = psInfo.getProfileImg();
        MultipartFile registrationImgFile = psInfo.getRegistrationImg();

        String profileImgUrl = null;
        if(profileImgFile != null && !profileImgFile.isEmpty()) {
            // 파일 중복명 방지 uuid 생성
            UUID uuid = UUID.randomUUID();

            profileImgUrl = uploadFile(uuid, profileImgFile, null);
        }

        String registrationImgUrl = null;

        if(registrationImgFile != null && !registrationImgFile.isEmpty()) {
            UUID uuid = UUID.randomUUID();

            registrationImgUrl = uploadFile(uuid, registrationImgFile, null);
        }

        PsEntity ps = PsEntity.builder().email(psInfo.getEmail())
                .password(psInfo.getPassword())
                .name(psInfo.getName())
                .address(psInfo.getAddress())
                .zipcode(psInfo.getZipcode())
                .registration(psInfo.getRegistration())
                .registrationImg(registrationImgUrl)
                .number(psInfo.getNumber())
                .director(psInfo.getDirector())
                .homepage(psInfo.getHomepage())
                .profileImg(profileImgUrl)
                .intro(psInfo.getIntro())
                .build();

        psRepository.save(ps);


        /////////////// 병원 - 전문의 리스트 저장 ///////////////
        for(DoctorReq doctor : psInfo.getDoctorList()) {
            String doctorImgUrl = null;

            MultipartFile doctorImgFile = doctor.getImg();
            if(doctorImgFile != null && !doctorImgFile.isEmpty()) {
                UUID uuid = UUID.randomUUID();

                doctorImgUrl = uploadFile(uuid, doctor.getImg(), null);
            }

            DoctorEntity doc = DoctorEntity.builder().ps(ps)
                    .img(doctorImgUrl)
                    .name(doctor.getName())
                    .build();

            doctorRepository.save(doc);

            /////////////// 병원 - 전문의 - 메인 카테고리 매핑 저장 => "DoctorMain" ///////////////
            String name = doctor.getMainCategory();
            MainCategoryEntity mainCategory = mainRepository.findMainByName(name).get();


            DoctorMainEntity doctorMain = DoctorMainEntity.builder().doctor(doc)
                            .mainCategory(mainCategory).build();

            doctorMainRepository.save(doctorMain);

        }

        log.info(ps.toString());

        /////////////// 병원 - 메인 카테고리 매핑 저장 => "PsMain" ///////////////
        for(String name : psInfo.getMainCategoryList()) {

            MainCategoryEntity mainCategory = mainRepository.findMainByName(name).get();

            if(mainCategory != null) {
                PsMainEntity psMain = PsMainEntity.builder().ps(ps)
                        .mainCategory(mainCategory).build();

                psMainRepository.save(psMain);
            }

        }

        /////////////// 병원 - 서브 카테고리 매핑 저장 => "PasMainSub" ///////////////
        for(String name : psInfo.getSubCategoryList()) {

            SubCategoryEntity subCategory = subRepository.findMainByName(name).get();

            MainCategoryEntity mainCategory = subCategory.getMainCategory();

            if(subCategory != null) {
                PsMainSubEntity psMainSub = PsMainSubEntity.builder().ps(ps)
                        .mainCategory(mainCategory).subCategory(subCategory).build();

                psMainSubRepository.save(psMainSub);
            }

        }

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
//            psInfo.setRegistrationImg(ps.getRegistrationImg());

            // 의사 리스트 추가
            // 제공 수술 추가 => main + sub
            // 리뷰 조회 추가
            // 운영 시간 추가


            log.info(ps.getRegistrationImg());

            // doctor
            // main, sub category

            return psInfo;
        }
        return null;
    }

    // 병원 회원 정보 수정
//    @Override
//    @Transactional
//    public boolean editPsInfo(PsSeqReq psInfo) {
//        Long seq = psInfo.getSeq();
//        MultipartFile profileImgFile = psInfo.getProfileImg();
//        if(psRepository.findById(seq).isPresent()) {
//            PsEntity ps = psRepository.findById(seq).get();
//
//            String profileImgUrl = null;
//            if(profileImgFile != null && !profileImgFile.isEmpty()) {
//                // 파일 중복명 방지 uuid 생성
//                UUID uuid = UUID.randomUUID();
//
//                profileImgUrl = uploadFile(uuid, profileImgFile, null);
//            }
//
//            log.info(psInfo.toString());
//            ps.updatePsEntity(psInfo, profileImgUrl);
//
//            return true;
//        }
//
//        return false;
//    }

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
    @Transactional
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

    // 주소 수정
    @Override
    @Transactional
    public boolean editAddress(PsSeqReq psInfo) {
        Long seq = psInfo.getSeq();

        if(psRepository.findById(seq).isPresent()) {
            PsEntity ps = psRepository.findById(seq).get();

            String preAddress = ps.getAddress();

            ps.updateAddress(psInfo.getAddress());

            String postAddress = psInfo.getAddress();

            if(preAddress.equals(postAddress)){
                return false;
            }

            return true;
        }

        return false;
    }

    // 소개말 수정
    @Override
    @Transactional
    public boolean editIntro(PsSeqReq psInfo) {
        Long seq = psInfo.getSeq();

        if(psRepository.findById(seq).isPresent()) {
            PsEntity ps = psRepository.findById(seq).get();

            String preIntro = ps.getIntro();

            ps.updateIntro(psInfo.getIntro());

            String postIntro = psInfo.getIntro();

            if(preIntro.equals(postIntro)){
                return false;
            }

            return true;
        }

        return false;
    }

    // 전화 번호 수정
    @Override
    @Transactional
    public boolean editNumber(PsSeqReq psInfo) {
        Long seq = psInfo.getSeq();

        if(psRepository.findById(seq).isPresent()) {
            PsEntity ps = psRepository.findById(seq).get();

            String preNumber = ps.getNumber();

            ps.updateNumber(psInfo.getNumber());

            String postNumber = psInfo.getNumber();

            if(preNumber.equals(postNumber)){
                return false;
            }

            return true;
        }


        return false;
    }

    // 홈페이지 주소 수정
    @Override
    @Transactional
    public boolean editHomepage(PsSeqReq psInfo) {
        Long seq = psInfo.getSeq();

        if(psRepository.findById(seq).isPresent()) {
            PsEntity ps = psRepository.findById(seq).get();

            String preHomepage = ps.getHomepage();

            ps.updateHomepage(psInfo.getHomepage());

            String postHomepage = psInfo.getHomepage();

            if(preHomepage.equals(postHomepage)){
                return false;
            }

            return true;
        }

        return false;
    }

    // 카테고리 수정
    @Override
    @Transactional
    public boolean editCategory(PsSeqReq psInfo) {
        Long seq = psInfo.getSeq();

        // delete 하고
        psMainSubRepository.deleteMainSubByPs(seq);
        psMainRepository.deleteMainByPs(seq);

        PsEntity ps = psRepository.findById(seq).get();

        /////////////// 병원 - 메인 카테고리 매핑 저장 => "PsMain" ///////////////
        for(String name : psInfo.getMainCategoryList()) {

            MainCategoryEntity mainCategory = mainRepository.findMainByName(name).get();

            log.info(mainCategory.toString());

            if(mainCategory != null) {
                PsMainEntity psMain = PsMainEntity.builder().ps(ps)
                        .mainCategory(mainCategory).build();

                psMainRepository.save(psMain);
            }

        }

        /////////////// 병원 - 서브 카테고리 매핑 저장 => "PasMainSub" ///////////////
        for(String name : psInfo.getSubCategoryList()) {

            SubCategoryEntity subCategory = subRepository.findMainByName(name).get();
            log.info(subCategory.toString());

            MainCategoryEntity mainCategory = subCategory.getMainCategory();
            log.info(mainCategory.toString());

            if(subCategory != null) {
                PsMainSubEntity psMainSub = PsMainSubEntity.builder().ps(ps)
                        .mainCategory(mainCategory).subCategory(subCategory).build();

                psMainSubRepository.save(psMainSub);
            }

        }

        return true;
    }

    // 전문의 삭제
    @Override
    @Transactional
    public boolean deleteDoctor(Long doctorSeq) {

        // delete
        doctorMainRepository.deleteMainByDoctor(doctorSeq);
        doctorRepository.deleteById(doctorSeq);

        return true;
    }

    @Override
    @Transactional
    public boolean addDoctor(DoctorReq doctor) {
        // 파일
        MultipartFile imgFile = doctor.getImg();

        PsEntity ps = psRepository.findById(doctor.getPsSeq()).get();

        /////////////// 병원 - 전문의 리스트 저장 ///////////////
        String doctorImgUrl = null;

        MultipartFile doctorImgFile = doctor.getImg();
        if(doctorImgFile != null && !doctorImgFile.isEmpty()) {
            UUID uuid = UUID.randomUUID();
            doctorImgUrl = uploadFile(uuid, imgFile, null);
        }

        DoctorEntity doc = DoctorEntity.builder().ps(ps)
                .img(doctorImgUrl)
                .name(doctor.getName())
                .build();

        doctorRepository.save(doc);

        /////////////// 병원 - 전문의 - 메인 카테고리 매핑 저장 => "DoctorMain" ///////////////
        String name = doctor.getMainCategory();
        MainCategoryEntity mainCategory = mainRepository.findMainByName(name).get();

        DoctorMainEntity doctorMain = DoctorMainEntity.builder().doctor(doc)
                .mainCategory(mainCategory).build();

        doctorMainRepository.save(doctorMain);

        return true;
    }


}
