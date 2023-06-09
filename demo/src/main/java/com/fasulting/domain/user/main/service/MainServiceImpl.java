package com.fasulting.domain.user.main.service;

import com.fasulting.common.dto.respDto.DoctorRespDto;
import com.fasulting.common.dto.respDto.MainCategoryRespDto;
import com.fasulting.common.dto.respDto.ReviewRespDto;
import com.fasulting.common.dto.respDto.SubCategoryRespDto;
import com.fasulting.common.util.LogCurrent;
import com.fasulting.domain.user.main.dto.respDto.PsDetailRespDto;
import com.fasulting.domain.user.main.dto.respDto.PsListRespDto;
import com.fasulting.entity.category.MainCategoryEntity;
import com.fasulting.entity.category.SubCategoryEntity;
import com.fasulting.entity.doctor.DoctorEntity;
import com.fasulting.entity.ps.PsDefaultEntity;
import com.fasulting.entity.ps.PsEntity;
import com.fasulting.entity.review.ReviewEntity;
import com.fasulting.entity.user.UserEntity;
import com.fasulting.repository.category.MainCategoryRepository;
import com.fasulting.repository.category.SubCategoryRepository;
import com.fasulting.repository.doctor.DoctorMainRepository;
import com.fasulting.repository.doctor.DoctorRepository;
import com.fasulting.repository.ps.*;
import com.fasulting.repository.review.ReviewRepository;
import com.fasulting.repository.review.ReviewSubRepository;
import com.fasulting.repository.user.FavoriteRepository;
import com.fasulting.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.fasulting.common.util.FileManage.domain;
import static com.fasulting.common.util.LogCurrent.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {

    private final MainCategoryRepository mainCategoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final PsRepository psRepository;
    private final PsMainRepository psMainRepository;
    private  final PsMainSubRepository psMainSubRepository;
    private final TotalRatingRepository totalRatingRepository;
    private final ReviewRepository reviewRepository;
    private final FavoriteRepository favoriteRepository;
    private final PsDefaultRepository psDefaultRepository;
    private final DoctorRepository doctorRepository;
    private final DoctorMainRepository doctorMainRepository;
    private final ReviewSubRepository reviewSubRepository;
    private final UserRepository userRepository;

    /**
     * 메인 카테고리 리스트 조회
     * @return
     */
    @Override
    public List<MainCategoryRespDto> getMainCategoryList(){

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));
        List<MainCategoryEntity> list = mainCategoryRepository.findAll();

        if(list == null) {
            log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
            return  null;
        }

        List<MainCategoryRespDto> returnList = new ArrayList<>();

        for(MainCategoryEntity main : list) {
            MainCategoryRespDto resp = MainCategoryRespDto.builder()
                    .mainSeq(main.getSeq())
                    .mainName(main.getName())
                    .build();

            returnList.add(resp);
        }

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
        return returnList;
    }

    /**
     * 메인 카테고리 선택 후 서브 카테고리 조회
     * @param mainSeq
     * @return
     */
    @Override
    public List<SubCategoryRespDto> getSubcategoryList(Long mainSeq) {

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));
        MainCategoryEntity main = mainCategoryRepository.findById(mainSeq).orElseThrow(() -> {
            throw new NullPointerException();
        });

        List<SubCategoryEntity> list = subCategoryRepository.findByMainCategory(main);

        if(list == null) {
            log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
            return  null;
        }

        List<SubCategoryRespDto> resp = new ArrayList<>();

        for(SubCategoryEntity sub : list) {
            SubCategoryRespDto subList = SubCategoryRespDto.builder()
                    .subSeq(sub.getSeq())
                    .subName(sub.getName())
                    .build();

            resp.add(subList);
        }

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
        return resp;
    }

    /**
     * 메인 카테고리 선택 후 병원 리스트 조회
     * @param mainSeq
     * @return
     */
    @Override
    public List<PsListRespDto> getPsList(Long mainSeq) {

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));
        List<PsEntity> psList = psMainRepository.findPsByMainSeq(mainSeq);

        if(psList == null) {
            log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
            return null;
        }

        List<PsListRespDto> respList = new ArrayList<>();

        for(PsEntity ps : psList){
            Long psSeq = ps.getSeq();

            PsListRespDto resp = PsListRespDto.builder()
                    .psSeq(psSeq)
                    .psName(ps.getName())
                    .psProfileImg(domain + ps.getProfileImgPath())
                    .psIntro(ps.getIntro())
                    .psAddress(ps.getAddress())
                    .subCategoryName(psMainSubRepository.getSubNameByPsSeq(psSeq))
                    .totalRatingResult(totalRatingRepository.getResultByPsSeq(psSeq))
                    .reviewTotalCount(reviewRepository.getCountByPsSeq(psSeq))
                    .build();

            respList.add(resp);

        }

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
        return respList;
    }

    /**
     * 병원 정보 상세 조회
     * @param userSeq
     * @param psSeq
     * @return
     */
    @Override
    public PsDetailRespDto getPsDetail(Long userSeq, Long psSeq) {

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));

        UserEntity user = userRepository.findById(userSeq).orElseThrow(() -> {
            throw new NullPointerException();
        });
//        if (!CheckInfo.checkLoginInfo(user.getSeq(), user.getEmail(), user.getRole().getAuthority())){
//            log.error(logCurrent(getClassName(), getMethodName(), END));
//            throw new UnAuthorizedException();
//        }

        PsEntity ps = psRepository.findById(psSeq).orElseThrow(() -> {
            throw new NullPointerException();
        });

        // 운영시간
        List<PsDefaultEntity> psDefaultList = psDefaultRepository.findAllByPsSeq(psSeq);

        Map<Integer, List<Integer>> map = new HashMap<>();

        for(int i = 1; i<= 7; i++){
            map.put(i, new ArrayList<>()); // 1: 일요일 ~ 7 : 토요일
        }

        for(PsDefaultEntity psDefault : psDefaultList){
            int dayOfWeek = psDefault.getDefaultCal().getDayOfWeek();
            int time = psDefault.getTime().getNum();

            List<Integer> value = map.get(dayOfWeek);

            value.add(time);
            map.put(dayOfWeek, value);

        }

        // 의사
        List<DoctorEntity> docList = doctorRepository.findAllByPsSeq(psSeq);

        List<DoctorRespDto> docDtoList = new ArrayList<>();

        for(DoctorEntity doctor : docList){
            DoctorRespDto doctorRespDto = DoctorRespDto.builder()
                    .doctorSeq(doctor.getSeq())
                    .name(doctor.getName())
                    .profileImg(domain + doctor.getImgPath())
                    .mainCategoryName(doctorMainRepository.getMainCategoryByDoctorSeq(doctor.getSeq()))
                    .build();

            docDtoList.add(doctorRespDto);
        }


        // 리뷰
        List<ReviewEntity> reviewList = reviewRepository.findAllByPsSeq(psSeq);

        List<ReviewRespDto> reviewRespDtoList = new ArrayList<>();

        for(ReviewEntity review : reviewList){

            String userName = review.getUser().getName();

            ReviewRespDto reviewRespDto = ReviewRespDto.builder()
                    .reviewSeq(review.getSeq())
                    .userSeq(review.getUser().getSeq())
                    .userEmail(review.getUser().getEmail())
                    .userName(userName.charAt(0) + userName.replaceAll("[^/]", "*").substring(1))
                    .psSeq(review.getPs().getSeq())
                    .psName(review.getPs().getName())
                    .point(review.getPoint())
                    .regDate(review.getRegDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                    .content(review.getContent())
                    .subCategoryName(reviewSubRepository.getSubCategoryByReviewSeq(review.getSeq()))
                    .build();

            reviewRespDtoList.add(reviewRespDto);
        }

        // 전체
        PsDetailRespDto resp = PsDetailRespDto.builder()
                .psSeq(psSeq)
                .psName(ps.getName())
                .psIntro(ps.getIntro())
                .psAddress(ps.getAddress())
                .psProfileImg(domain + ps.getProfileImgPath())
                .psNumber(ps.getNumber())
                .psEmail(ps.getEmail())
                .psHomepage(ps.getHomepage())
                .isFavorite(favoriteRepository.findByUserSeqPsSeq(userSeq, psSeq).isPresent())
                .subCategoryName(psMainSubRepository.getSubNameByPsSeq(psSeq))
                .totalRatingResult(totalRatingRepository.getResultByPsSeq(psSeq))
                .reviewTotalCount(reviewRepository.getCountByPsSeq(psSeq))
                .doctor(docDtoList)
                .review(reviewRespDtoList)
                .defaultTime(map)
                .build();

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
        return resp;
    }
}
