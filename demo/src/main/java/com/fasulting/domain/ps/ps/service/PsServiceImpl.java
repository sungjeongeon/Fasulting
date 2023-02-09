package com.fasulting.domain.ps.ps.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.fasulting.common.RoleType;
import com.fasulting.common.dto.respDto.DoctorRespDto;
import com.fasulting.common.dto.respDto.MainCategoryRespDto;
import com.fasulting.common.dto.respDto.ReviewRespDto;
import com.fasulting.common.dto.respDto.SubCategoryRespDto;
import com.fasulting.common.util.FileManage;
import com.fasulting.domain.ps.ps.dto.reqDto.*;
import com.fasulting.domain.ps.ps.dto.respDto.CategoryListRespDto;
import com.fasulting.domain.ps.ps.dto.respDto.PsInfoRespDto;
import com.fasulting.entity.calendar.DefaultCalEntity;
import com.fasulting.entity.calendar.OperatingCalEntity;
import com.fasulting.entity.calendar.TimeEntity;
import com.fasulting.entity.category.MainCategoryEntity;
import com.fasulting.entity.category.SubCategoryEntity;
import com.fasulting.entity.doctor.DoctorEntity;
import com.fasulting.entity.doctor.DoctorMainEntity;
import com.fasulting.entity.ps.*;
import com.fasulting.entity.review.ReviewEntity;
import com.fasulting.repository.calendar.DefaultCalRepository;
import com.fasulting.repository.calendar.OperatingCalRepository;
import com.fasulting.repository.calendar.TimeRepository;
import com.fasulting.repository.category.MainCategoryRepository;
import com.fasulting.repository.category.SubCategoryRepository;
import com.fasulting.repository.doctor.DoctorMainRepository;
import com.fasulting.repository.doctor.DoctorRepository;
import com.fasulting.repository.ps.*;
import com.fasulting.repository.review.ReviewRepository;
import com.fasulting.repository.review.ReviewSubRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.fasulting.common.util.FileManage.*;

@Service
@Slf4j
@RequiredArgsConstructor
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
    private final PasswordEncoder passwordEncoder;

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public CategoryListRespDto getCategoryList() {

        List<MainCategoryRespDto> mainList = new ArrayList<>();

        List<MainCategoryEntity> mList = mainRepository.findAll();

        for (MainCategoryEntity m : mList) {
            MainCategoryRespDto main = MainCategoryRespDto.builder()
                    .mainSeq(m.getSeq())
                    .mainName(m.getName())
                    .build();

            mainList.add(main);
        }

        List<SubCategoryRespDto> subList = new ArrayList<>();

        List<SubCategoryEntity> sList = subRepository.findAll();

        for (SubCategoryEntity s : sList) {
            SubCategoryRespDto sub = SubCategoryRespDto.builder()
                    .mainSeq(s.getSeq())
                    .subSeq(s.getSeq())
                    .subName(s.getName())
                    .build();

            subList.add(sub);
        }

        if (!mainList.isEmpty() && !subList.isEmpty()) {
            CategoryListRespDto resp = CategoryListRespDto.builder()
                    .mainCategoryList(mainList)
                    .subCategoryList(subList)
                    .build();

            return resp;
        }

        return null;
    }

    // 병원 회원 가입
    @Override
    public boolean psRegister(PsWithoutSeqReqDto psInfo) {

        /////////////// 병원 저장 ///////////////
        MultipartFile profileImgFile = psInfo.getProfileImg();
        log.info(profileImgFile.getOriginalFilename());
        MultipartFile registrationImgFile = psInfo.getRegistrationImg();
        log.info(registrationImgFile.getOriginalFilename());

        String profileImgUrl = null;


        if (profileImgFile != null && !profileImgFile.isEmpty()) {
            // 파일 중복명 방지 uuid 생성
            UUID uuid = UUID.randomUUID();

            profileImgUrl = FileManage.uploadFile(profileImgFile, uuid,  psProfileImgDirPath);

            log.info(profileImgUrl);
        }

        String registrationImgUrl = null;

        if (registrationImgFile != null && !registrationImgFile.isEmpty()) {
            UUID uuid = UUID.randomUUID();

            registrationImgUrl = FileManage.uploadFile(registrationImgFile, uuid, psRegImgDirPath);
            log.info(registrationImgUrl);
        }


        PsEntity ps = PsEntity.builder()
                .email(psInfo.getEmail())
                .password(passwordEncoder.encode(psInfo.getPassword()))
                .name(psInfo.getName())
                .address(psInfo.getAddress())
                .zipcode(psInfo.getZipcode())
                .registration(psInfo.getRegistration())
                .regImgPath(registrationImgUrl)
                .regImgOrigin(registrationImgFile.getOriginalFilename())
                .number(psInfo.getNumber())
                .director(psInfo.getDirector())
                .homepage(psInfo.getHomepage())
                .profileImgPath(profileImgUrl)
                .profileImgOrigin(profileImgFile.getOriginalFilename())
                .intro(psInfo.getIntro())
                .build();

        psRepository.save(ps);

        log.info("save ps");


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

            MainCategoryEntity mainCategory = mainRepository.findMainByName(name).orElseThrow(() -> {
                throw new NullPointerException();
            });

            PsMainEntity psMain = PsMainEntity.builder().ps(ps)
                    .mainCategory(mainCategory).build();
            psMainRepository.save(psMain);


        }

//        log.info("dpd");

        /////////////// 병원 - 서브 카테고리 매핑 저장 => "PasMainSub" ///////////////
        for (String name : psInfo.getSubCategoryList()) {

            SubCategoryEntity subCategory = subRepository.findByName(name).orElseThrow(() -> {
                throw new NullPointerException();
            });


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
        // email이 있다면 그 email 가진 ps 찾기
        PsEntity ps = psRepository.findPsByEmail(psInfo.getEmail()).orElseThrow(() -> {
            throw new NullPointerException();
        });

//            log.info(psInfo.getPassword());
        if (passwordEncoder.matches(psInfo.getPassword(), ps.getPassword())) {
            return false;
        }
        // password update
        ps.resetPassword(passwordEncoder.encode(psInfo.getPassword()));
        return true;

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
        PsEntity ps = psRepository.findById(psSeq).orElseThrow(() -> {
            throw new NullPointerException();
        });

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

                DefaultCalEntity defaultCal = defaultCalRepository.findByDayOfWeek(i).orElseThrow(() -> {
                    throw new NullPointerException();
                });


                TimeEntity time = timeRepository.findByNum(-1).orElseThrow(() -> {
                    throw new NullPointerException();
                });


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

        List<DoctorRespDto> docDtoList = new ArrayList<>();

        for (DoctorEntity doctor : docList) {
            DoctorRespDto doctorRespDto = DoctorRespDto.builder()
                    .doctorSeq(doctor.getSeq())
                    .name(doctor.getName())
                    .profileImg(doctor.getImgPath())
                    .mainCategoryName(doctorMainRepository.getMainCategoryByDoctorSeq(doctor.getSeq()))
                    .build();

            docDtoList.add(doctorRespDto);
        }


        // 리뷰
        List<ReviewEntity> reviewList = reviewRepository.findAllByPsSeq(psSeq);

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
                .psProfileImg(domain + ps.getProfileImgPath())
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

            ps.updateByWithdrawal(RoleType.PS + "_" + psInfo.getSeq(), LocalDateTime.now());

            psRepository.save(ps);

            return true;
        }

        return false;
    }

    // 비밀번호 재확인 (로그인 상태에서)
    @Override
    @Transactional
    public boolean checkPassword(PsSeqReqDto psInfo) {
        Long seq = psInfo.getSeq();
        PsEntity ps = psRepository.findById(seq).orElseThrow(() -> {
            throw new NullPointerException();
        });

        if (passwordEncoder.matches(psInfo.getPassword(), ps.getPassword())) {
            return true;
        }


        return false;
    }

    // 주소 수정
    @Override
    @Transactional
    public boolean editAddress(PsSeqReqDto psInfo) {
        Long seq = psInfo.getSeq();

        PsEntity ps = psRepository.findById(seq).orElseThrow(() -> {
            throw new NullPointerException();
        });

        String preAddress = ps.getAddress();

        ps.updateAddress(psInfo.getAddress());

        String postAddress = psInfo.getAddress();

        if (preAddress.equals(postAddress)) {
            return false;
        }

        return true;

    }

    // 소개말 수정
    @Override
    @Transactional
    public boolean editIntro(PsSeqReqDto psInfo) {

        log.info("ps edit intro - service call");
        Long seq = psInfo.getSeq();


        PsEntity ps = psRepository.findById(seq).orElseThrow(() -> {
            throw new NullPointerException();
        });

        String preIntro = ps.getIntro();

        ps.updateIntro(psInfo.getIntro());

        String postIntro = psInfo.getIntro();

        if (preIntro.equals(postIntro)) {
            return false;
        }

        return true;

    }

    // 전화 번호 수정
    @Override
    @Transactional
    public boolean editNumber(PsSeqReqDto psInfo) {
        Long seq = psInfo.getSeq();


        PsEntity ps = psRepository.findById(seq).orElseThrow(() -> {
            throw new NullPointerException();
        });

        String preNumber = ps.getNumber();

        ps.updateNumber(psInfo.getNumber());

        String postNumber = psInfo.getNumber();

        if (preNumber.equals(postNumber)) {
            return false;
        }

        return true;

    }

    // 홈페이지 주소 수정
    @Override
    @Transactional
    public boolean editHomepage(PsSeqReqDto psInfo) {
        Long seq = psInfo.getSeq();


        PsEntity ps = psRepository.findById(seq).orElseThrow(() -> {
            throw new NullPointerException();
        });

        String preHomepage = ps.getHomepage();

        ps.updateHomepage(psInfo.getHomepage());

        String postHomepage = psInfo.getHomepage();

        if (preHomepage.equals(postHomepage)) {
            return false;
        }

        return true;

    }

    /**
     * 카테고리 수정
     * @param psInfo
     * @return
     */
    @Override
    @Transactional
    public boolean editCategory(PsSeqReqDto psInfo) {
        Long seq = psInfo.getSeq();

        // delete 하고
        psMainSubRepository.deleteMainSubByPs(seq);
        psMainRepository.deleteMainByPs(seq);

        PsEntity ps = psRepository.findById(seq).orElseThrow(() -> {
            throw new NullPointerException();
        });

        /////////////// 병원 - 메인 - 서브 카테고리 매핑 저장 ///////////////
        for (String name : psInfo.getSubCategoryList()) {

            SubCategoryEntity sub = subRepository.findByName(name).orElseThrow(() -> {
                throw new NullPointerException();
            });

            MainCategoryEntity main = sub.getMainCategory();

            log.info(main.toString());

            PsMainEntity psMain = PsMainEntity.builder()
                    .ps(ps)
                    .mainCategory(main)
                    .build();

            psMainRepository.save(psMain); // 병원 - 메인

            PsMainSubEntity psMainSub = PsMainSubEntity.builder()
                    .ps(ps)
                    .mainCategory(main)
                    .subCategory(sub)
                    .build();

            psMainSubRepository.save(psMainSub); // 병원 - 메인 - 서브
            
        }

        return true;
    }

    // 전문의 삭제
    @Override
    @Transactional
    public boolean deleteDoctor(DoctorDelReqDto doctorDelReqDto) {

        Long psSeq = doctorDelReqDto.getPsSeq();
        Long doctorSeq = doctorDelReqDto.getDoctorSeq();

        DoctorEntity doctor = doctorRepository.findById(doctorSeq).orElseThrow(() -> new NullPointerException());

        if (doctor.getPs().getSeq() != psSeq) {
            return false;
        }

        // delete
        doctorMainRepository.deleteMainByDoctor(doctorSeq);
        doctorRepository.deleteById(doctorSeq);

        return true;
    }

    // 운영 시간 수정 (설정)
    @Transactional
    @Override
    public boolean modifyPsDefault(PsDefaultReqDto psDefaultReqDto) {


        log.info(psDefaultReqDto.getPsSeq().toString());

//        log.info("ps is " + psRepository.findById(psDefaultReqDto.getPsSeq()).isPresent());

        PsEntity ps = psRepository.findById(psDefaultReqDto.getPsSeq()).orElseThrow(() -> {
            throw new NullPointerException();
        });

        log.info(ps + " : " + ps.toString());

        //////////// default 운영 시간
        // delete
        psDefaultRepository.deleteAllByPs(ps);

        // insert
        Map<String, List<Integer>> map = psDefaultReqDto.getDefaultTime();

        for (int i = 1; i <= 7; i++) {
            List<Integer> list = map.get(i + "");

            DefaultCalEntity defaultCal = defaultCalRepository.findByDayOfWeek(i).orElseThrow(() -> {
                throw new NullPointerException();
            });

            for (Integer num : list) {
                TimeEntity time = timeRepository.findByNum(num).orElseThrow(() -> {
                    throw new NullPointerException();
                });


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
                    TimeEntity time = timeRepository.findByNum(num).orElseThrow(() -> {
                        throw new NullPointerException();
                    });


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

    // 의사 현황 수정
    @Override
    @Transactional
    public boolean addDoctor(DoctorReqDto doctor) {
        // 파일
        MultipartFile imgFile = doctor.getImg();

        PsEntity ps = psRepository.findById(doctor.getPsSeq()).orElseThrow(() -> {
            throw new NullPointerException();
        });


        /////////////// 병원 - 전문의 리스트 저장 ///////////////
        String doctorImgUrl = null;

        MultipartFile doctorImgFile = doctor.getImg();
        if (doctorImgFile != null && !doctorImgFile.isEmpty()) {
            UUID uuid = UUID.randomUUID();

            doctorImgUrl = FileManage.uploadFile(imgFile, uuid, doctorImgPath);a
        }

        DoctorEntity doc = DoctorEntity.builder().ps(ps)
                .imgPath(doctorImgUrl)
                .imgOrigin(doctorImgFile.getOriginalFilename())
                .name(doctor.getName())
                .build();

        doctorRepository.save(doc);

        /////////////// 병원 - 전문의 - 메인 카테고리 매핑 저장 => "DoctorMain" ///////////////
        String name = doctor.getMainCategory();
        MainCategoryEntity mainCategory = mainRepository.findMainByName(name).orElseThrow(() -> {
            throw new NullPointerException();
        });


        DoctorMainEntity doctorMain = DoctorMainEntity.builder().doctor(doc)
                .mainCategory(mainCategory).build();

        doctorMainRepository.save(doctorMain);

        return true;
    }


}
