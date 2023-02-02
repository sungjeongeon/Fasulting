package com.fasulting.demo.ps.ps.service;

import com.fasulting.demo.common.defaultCal.repository.DefaultCalRepository;
import com.fasulting.demo.common.doctor.dto.DoctorDto;
import com.fasulting.demo.common.operatingCal.repository.OperatingCalRepository;
import com.fasulting.demo.common.psOperating.repository.PsOperatingRepository;
import com.fasulting.demo.common.rating.TotalRatingRepository;
import com.fasulting.demo.common.review.repository.ReviewRepository;
import com.fasulting.demo.common.review.repository.ReviewSubRepository;
import com.fasulting.demo.common.review.respDto.ReviewRespDto;
import com.fasulting.demo.common.time.repository.TimeRepository;
import com.fasulting.demo.entity.*;
import com.fasulting.demo.ps.ps.dto.reqDto.DoctorReqDto;
import com.fasulting.demo.ps.ps.dto.reqDto.PsDefaultReqDto;
import com.fasulting.demo.ps.ps.dto.reqDto.PsSeqReqDto;
import com.fasulting.demo.ps.ps.dto.reqDto.PsWithoutSeqReqDto;
import com.fasulting.demo.ps.ps.dto.respDto.PsInfoRespDto;
import com.fasulting.demo.ps.ps.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Slf4j
public class PsServiceImpl implements PsService {

    private final OperatingCalRepository operatingCalRepository;
    private final PsOperatingRepository psOperatingRepository;
    private final TimeRepository timeRepository;
    private final DefaultCalRepository defaultCalRepository;
    private final PsRepository psRepository;
    private final DoctorRepository doctorRepository;
    private final MainCategoryRepository mainRepository;
    private final SubCategoryRepository subRepository;
    private final PsMainRepository psMainRepository;
    private final PsMainSubRepository psMainSubRepository;
    private final DoctorMainRepository doctorMainRepository;
    private final ReviewSubRepository reviewSubRepository;
    private final PsDefaultRepository psDefaultRepository;
    private final ReviewRepository reviewRepository;
    private final TotalRatingRepository totalRatingRepository;

    public PsServiceImpl(OperatingCalRepository operatingCalRepository, PsOperatingRepository psOperatingRepository, TimeRepository timeRepository, DefaultCalRepository defaultCalRepository, PsRepository psRepository, DoctorRepository doctorRepository, MainCategoryRepository mainRepository, SubCategoryRepository subRepository, PsMainRepository psMainRepository, PsMainSubRepository psMainSubRepository, DoctorMainRepository doctorMainRepository, ReviewSubRepository reviewSubRepository, PsDefaultRepository psDefaultRepository, ReviewRepository reviewRepository, TotalRatingRepository totalRatingRepository) {
        this.operatingCalRepository = operatingCalRepository;
        this.psOperatingRepository = psOperatingRepository;
        this.timeRepository = timeRepository;
        this.defaultCalRepository = defaultCalRepository;
        this.psRepository = psRepository;
        this.doctorRepository = doctorRepository;
        this.mainRepository = mainRepository;
        this.subRepository = subRepository;
        this.psMainRepository = psMainRepository;
        this.psMainSubRepository = psMainSubRepository;
        this.doctorMainRepository = doctorMainRepository;
        this.reviewSubRepository = reviewSubRepository;
        this.psDefaultRepository = psDefaultRepository;
        this.reviewRepository = reviewRepository;
        this.totalRatingRepository = totalRatingRepository;
    }

    // 배포할 때 경로 바꾸기
    private final String dirPath = "C:/fasulting/ps/files";

    private final String domain = "https://localhost:8080/resources/upload/";

    private String uploadFile(UUID uuid, MultipartFile imgFile, String imgUrl) {
        File folder = new File(dirPath);
        if (!folder.exists()) folder.mkdirs(); // 폴더 생성

        String imgSaveUrl = uuid + "_" + imgFile.getOriginalFilename();
        File file = new File(dirPath + File.separator + imgSaveUrl);
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
    public boolean psRegister(PsWithoutSeqReqDto psInfo) {

        /////////////// 병원 저장 ///////////////
        MultipartFile profileImgFile = psInfo.getProfileImg();
        MultipartFile registrationImgFile = psInfo.getRegistrationImg();

        String profileImgUrl = null;
        if (profileImgFile != null && !profileImgFile.isEmpty()) {
            // 파일 중복명 방지 uuid 생성
            UUID uuid = UUID.randomUUID();

            profileImgUrl = uploadFile(uuid, profileImgFile, null);
        }

        String registrationImgUrl = null;

        if (registrationImgFile != null && !registrationImgFile.isEmpty()) {
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


//        /////////////// 병원 - 전문의 리스트 저장 ///////////////
//        for (DoctorReqDto doctor : psInfo.getDoctorList()) {
//            String doctorImgUrl = null;
//
//            MultipartFile doctorImgFile = doctor.getImg();
//            if (doctorImgFile != null && !doctorImgFile.isEmpty()) {
//                UUID uuid = UUID.randomUUID();
//
//                doctorImgUrl = uploadFile(uuid, doctor.getImg(), null);
//            }
//
//            DoctorEntity doc = DoctorEntity.builder().ps(ps)
//                    .img(doctorImgUrl)
//                    .name(doctor.getName())
//                    .build();
//
//            doctorRepository.save(doc);
//
//            /////////////// 병원 - 전문의 - 메인 카테고리 매핑 저장 => "DoctorMain" ///////////////
//            String name = doctor.getMainCategory();
//            MainCategoryEntity mainCategory = mainRepository.findMainByName(name).get();
//
//
//            DoctorMainEntity doctorMain = DoctorMainEntity.builder().doctor(doc)
//                    .mainCategory(mainCategory).build();
//
//            doctorMainRepository.save(doctorMain);
//
//        }
//
//        log.info(ps.toString());

        /////////////// 병원 - 메인 카테고리 매핑 저장 => "PsMain" ///////////////
        for (String name : psInfo.getMainCategoryList()) {

            MainCategoryEntity mainCategory = mainRepository.findMainByName(name).get();

            if (mainCategory != null) {
                PsMainEntity psMain = PsMainEntity.builder().ps(ps)
                        .mainCategory(mainCategory).build();

                psMainRepository.save(psMain);
            }

        }

        /////////////// 병원 - 서브 카테고리 매핑 저장 => "PasMainSub" ///////////////
        for (String name : psInfo.getSubCategoryList()) {

            SubCategoryEntity subCategory = subRepository.findMainByName(name).get();

            MainCategoryEntity mainCategory = subCategory.getMainCategory();

            if (subCategory != null) {
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
    public boolean resetPassword(PsWithoutSeqReqDto psInfo) {
        if (psRepository.findPsByEmail(psInfo.getEmail()).isPresent()) {
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
        if (psRepository.findPsByEmail(email).isPresent()) {
            log.info("병원 회원 이메일 존재");
            return true;
        } else {
            log.info("병원 회원 이메일 존재하지 않음");
            return false;
        }
    }

    // 병원 정보 조회
    @Override
    public PsInfoRespDto getPsInfo(Long psSeq) {
        PsEntity ps = psRepository.findById(psSeq).get();

        if (ps == null) {

            // 처리
        }

        // 운영시간

        List<PsDefaultEntity> psDefaultList = psDefaultRepository.findAllByPsSeq(psSeq);

        Map<Integer, List<Integer>> map = new HashMap<>();

        if (psDefaultList != null) {

            for (int i = 1; i <= 7; i++) {
                map.put(i, new ArrayList<>()); // 1: 일요일 ~ 7 : 토요일
            }

            for (PsDefaultEntity psDefault : psDefaultList) {
                int dayOfWeek = psDefault.getDefaultCal().getDayOfWeek();

                int time = psDefault.getTime().getNum();

                List<Integer> value = map.get(dayOfWeek);

                value.add(time);
                map.put(dayOfWeek, value);

            }

        } else {
            for (int i = 1; i <= 7; i++) {
                List<Integer> list = new ArrayList<>();
                list.add(-1);
                map.put(i, list); // 1: 일요일 ~ 7 : 토요일

                DefaultCalEntity defaultCal = defaultCalRepository.findByDayOfWeek(i).get();

                TimeEntity time = timeRepository.findByNum(-1).get();

                PsDefaultEntity psDefault = PsDefaultEntity.builder()
                        .ps(ps)
                        .defaultCal(defaultCal)
                        .time(time)
                        .build();

                psDefaultRepository.save(psDefault);
            }
        }


        // 의사
        List<DoctorEntity> docList = doctorRepository.findAllByPsSeq(psSeq);

        List<DoctorDto> docDtoList = new ArrayList<>();

        for (DoctorEntity doctor : docList) {
            DoctorDto doctorDto = DoctorDto.builder()
                    .doctorSeq(doctor.getSeq())
                    .name(doctor.getName())
                    .profileImg(doctor.getImg())
                    .mainCategoryName(doctorMainRepository.getMainCategoryByDoctorSeq(doctor.getSeq()))
                    .build();

            docDtoList.add(doctorDto);
        }

        if (psDefaultList == null) {

            // 처리
        }

        // 리뷰
        List<ReviewEntity> reviewList = reviewRepository.findAllByPsSeq(psSeq);


        if (reviewList == null) {

            // 처리
        }

        List<ReviewRespDto> reviewDtoList = new ArrayList<>();

        for (ReviewEntity review : reviewList) {

            ReviewRespDto reviewDto = ReviewRespDto.builder()
                    .reviewSeq(review.getSeq())
                    .userEmail(review.getUser().getEmail())
                    .point(review.getPoint())
                    .regDate(review.getRegDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                    .content(review.getContent())
                    .subCategoryName(reviewSubRepository.getSubCategoryByDoctorSeq(review.getSeq()))
                    .build();

            reviewDtoList.add(reviewDto);
        }

        // 전체
        PsInfoRespDto resp = PsInfoRespDto.builder()
                .psSeq(psSeq)
                .psName(ps.getName())
                .psIntro(ps.getIntro())
                .psAddress(ps.getAddress())
                .psProfileImg("server domain" + File.separator + ps.getProfileImg())
                .psNumber(ps.getNumber())
                .psEmail(ps.getEmail())
                .subCategoryName(psMainSubRepository.getSubNameByPsSeq(psSeq))
                .totalRatingResult(totalRatingRepository.getResultByPsSeq(psSeq))
                .reviewTotalCount(reviewRepository.getCountByPsSeq(psSeq))
                .doctor(docDtoList)
                .review(reviewDtoList)
                .defaultTime(map)
                .build();


        return resp;
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
    public boolean withdrawPs(PsSeqReqDto psInfo) {
        Long seq = psInfo.getSeq();
        if (psRepository.findById(seq).isPresent()) {
            PsEntity ps = psRepository.findById(seq).get();

            ps.withdrawlPs("y", "ps_" + psInfo.getSeq(), LocalDateTime.now());

            return true;
        }

        return false;
    }

    // 비밀번호 재확인 (로그인 상태에서)
    @Override
    @Transactional
    public boolean checkPassword(PsSeqReqDto psInfo) {
        Long seq = psInfo.getSeq();
        if (psRepository.findById(seq).isPresent()) {
            String password = psRepository.findById(seq).get().getPassword();

            if (password.equals(psInfo.getPassword())) {
                return true;
            }
        }

        return false;
    }

    // 주소 수정
    @Override
    @Transactional
    public boolean editAddress(PsSeqReqDto psInfo) {
        Long seq = psInfo.getSeq();

        if (psRepository.findById(seq).isPresent()) {
            PsEntity ps = psRepository.findById(seq).get();

            String preAddress = ps.getAddress();

            ps.updateAddress(psInfo.getAddress());

            String postAddress = psInfo.getAddress();

            if (preAddress.equals(postAddress)) {
                return false;
            }

            return true;
        }

        return false;
    }

    // 소개말 수정
    @Override
    @Transactional
    public boolean editIntro(PsSeqReqDto psInfo) {
        Long seq = psInfo.getSeq();

        if (psRepository.findById(seq).isPresent()) {
            PsEntity ps = psRepository.findById(seq).get();

            String preIntro = ps.getIntro();

            ps.updateIntro(psInfo.getIntro());

            String postIntro = psInfo.getIntro();

            if (preIntro.equals(postIntro)) {
                return false;
            }

            return true;
        }

        return false;
    }

    // 전화 번호 수정
    @Override
    @Transactional
    public boolean editNumber(PsSeqReqDto psInfo) {
        Long seq = psInfo.getSeq();

        if (psRepository.findById(seq).isPresent()) {
            PsEntity ps = psRepository.findById(seq).get();

            String preNumber = ps.getNumber();

            ps.updateNumber(psInfo.getNumber());

            String postNumber = psInfo.getNumber();

            if (preNumber.equals(postNumber)) {
                return false;
            }

            return true;
        }


        return false;
    }

    // 홈페이지 주소 수정
    @Override
    @Transactional
    public boolean editHomepage(PsSeqReqDto psInfo) {
        Long seq = psInfo.getSeq();

        if (psRepository.findById(seq).isPresent()) {
            PsEntity ps = psRepository.findById(seq).get();

            String preHomepage = ps.getHomepage();

            ps.updateHomepage(psInfo.getHomepage());

            String postHomepage = psInfo.getHomepage();

            if (preHomepage.equals(postHomepage)) {
                return false;
            }

            return true;
        }

        return false;
    }

    // 카테고리 수정
    @Override
    @Transactional
    public boolean editCategory(PsSeqReqDto psInfo) {
        Long seq = psInfo.getSeq();

        // delete 하고
        psMainSubRepository.deleteMainSubByPs(seq);
        psMainRepository.deleteMainByPs(seq);

        PsEntity ps = psRepository.findById(seq).get();

        /////////////// 병원 - 메인 카테고리 매핑 저장 => "PsMain" ///////////////
        for (String name : psInfo.getMainCategoryList()) {

            MainCategoryEntity mainCategory = mainRepository.findMainByName(name).get();

            log.info(mainCategory.toString());

            if (mainCategory != null) {
                PsMainEntity psMain = PsMainEntity.builder().ps(ps)
                        .mainCategory(mainCategory).build();

                psMainRepository.save(psMain);
            }

        }

        /////////////// 병원 - 서브 카테고리 매핑 저장 => "PasMainSub" ///////////////
        for (String name : psInfo.getSubCategoryList()) {

            SubCategoryEntity subCategory = subRepository.findMainByName(name).get();
            log.info(subCategory.toString());

            MainCategoryEntity mainCategory = subCategory.getMainCategory();
            log.info(mainCategory.toString());

            if (subCategory != null) {
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

    // 운영 시간 수정 (설정)
    @Transactional
    @Override
    public boolean modifyPsDefault(PsDefaultReqDto psDefaultReqDto) {

        PsEntity ps = psRepository.findById(psDefaultReqDto.getPsSeq()).get();

        //////////// default 운영 시간
        // delete
        psDefaultRepository.deleteAllByPs(ps);

        // insert
        Map<String, List<Integer>> map = psDefaultReqDto.getDefaultTime();

        for (int i = 1; i <= 7; i++) {
            List<Integer> list = map.get(i + "");

            DefaultCalEntity defaultCal = defaultCalRepository.findByDayOfWeek(i).get();

            for (Integer num : list) {
                TimeEntity time = timeRepository.findByNum(num).get();

                PsDefaultEntity psDefault = PsDefaultEntity.builder()
                        .ps(ps)
                        .time(time)
                        .defaultCal(defaultCal)
                        .build();

                psDefaultRepository.save(psDefault);
            }
        }

        //////////// 현실 운영 시간 (달력)
        // delete
        psOperatingRepository.deleteAllByPs(ps);


        // 새로 생성
        for (int i = 1; i <= 7; i++) {

            // 해당 요일 가져오기 (1년 중에 여로개일수도이짜나)
            List<OperatingCalEntity> operatingCalList = operatingCalRepository.findAllByDayOfWeek(i);

            List<Integer> numList = map.get(i + "");

            for (OperatingCalEntity operatingCal : operatingCalList) {

                for (Integer num : numList) {
                    TimeEntity time = timeRepository.findByNum(num).get();

                    PsOperatingEntity psOperating = PsOperatingEntity.builder()
                            .operatingCal(operatingCal)
                            .ps(ps)
                            .time(time)
                            .build();

                    psOperatingRepository.save(psOperating);
                }

            }

        }


        return true;
    }

    // 전문의 추가 설정
    @Override
    @Transactional
    public boolean addDoctor(DoctorReqDto doctor) {
        // 파일
        MultipartFile imgFile = doctor.getImg();

        PsEntity ps = psRepository.findById(doctor.getPsSeq()).get();

        /////////////// 병원 - 전문의 리스트 저장 ///////////////
        String doctorImgUrl = null;

        MultipartFile doctorImgFile = doctor.getImg();
        if (doctorImgFile != null && !doctorImgFile.isEmpty()) {
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
