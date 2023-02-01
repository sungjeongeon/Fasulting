package com.fasulting.demo.customer.main.service;

import com.fasulting.demo.common.doctor.dto.DoctorDto;
import com.fasulting.demo.common.rating.TotalRatingRepository;
import com.fasulting.demo.common.review.repository.ReviewRepository;
import com.fasulting.demo.common.review.repository.ReviewSubRepository;
import com.fasulting.demo.common.review.respDto.ReviewRespDto;
import com.fasulting.demo.customer.favorite.repository.FavoriteRepository;
import com.fasulting.demo.customer.main.dto.respDto.*;
import com.fasulting.demo.entity.*;
import com.fasulting.demo.ps.ps.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
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

    public MainServiceImpl(MainCategoryRepository mainCategoryRepository, SubCategoryRepository subCategoryRepository,
                           PsMainRepository psMainRepository, PsMainSubRepository psMainSubRepository,
                           TotalRatingRepository totalRatingRepository, ReviewRepository reviewRepository ,
                           PsRepository psRepository, FavoriteRepository favoriteRepository,
                           PsDefaultRepository psDefaultRepository, DoctorRepository doctorRepository,
                           DoctorMainRepository doctorMainRepository, ReviewSubRepository reviewSubRepository) {
        this.mainCategoryRepository = mainCategoryRepository;
        this.subCategoryRepository = subCategoryRepository;
        this.psMainRepository = psMainRepository;
        this.psMainSubRepository = psMainSubRepository;
        this.totalRatingRepository = totalRatingRepository;
        this.reviewRepository = reviewRepository;
        this.psRepository = psRepository;
        this.favoriteRepository = favoriteRepository;
        this.psDefaultRepository = psDefaultRepository;
        this.doctorRepository = doctorRepository;
        this.doctorMainRepository = doctorMainRepository;
        this.reviewSubRepository = reviewSubRepository;
    }

    @Override
    public List<MainCategoryRespDto> getMainCategoryList(){

        List<MainCategoryEntity> list = mainCategoryRepository.findAll();

        if(list == null) {
            // custom exception

            log.info("main category list is NULL");
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

        log.info("main category list success" + returnList.toString());
        return returnList;
    }

    @Override
    public List<SubCategoryListRespDto> getSubcategoryList(Long mainSeq) {

        MainCategoryEntity main = mainCategoryRepository.findById(mainSeq).get();

        List<SubCategoryEntity> list = subCategoryRepository.findByMainCategory(main);

        if(list == null) {
            // custom exception

            log.info("sub category list is NULL");
            return  null;
        }

        List<SubCategoryListRespDto> resp = new ArrayList<>();

        for(SubCategoryEntity sub : list) {
            SubCategoryListRespDto subList = SubCategoryListRespDto.builder()
                    .subSeq(sub.getSeq())
                    .subName(sub.getName())
                    .build();

            resp.add(subList);
        }

        log.info("sub category list success" + resp.toString());
        return resp;
    }

    @Override
    public List<PsListRespDto> getPsList(Long mainSeq) {

        List<PsEntity> psList = psMainRepository.findPsByMainSeq(mainSeq);

        if(psList == null) {
            return null;
        }

        List<PsListRespDto> respList = new ArrayList<>();

        for(PsEntity ps : psList){
            Long psSeq = ps.getSeq();

            PsListRespDto resp = PsListRespDto.builder()
                    .psSeq(psSeq)
                    .psName(ps.getName())
                    .psProfileImg("server domain" + File.separator + ps.getProfileImg())
                    .psIntro(ps.getIntro())
                    .psAddress(ps.getAddress())
                    .subCategoryName(psMainSubRepository.getSubNameByPsSeq(psSeq))
                    .totalRatingResult(totalRatingRepository.getResultByPsSeq(psSeq))
                    .reviewTotalCount(reviewRepository.getCountByPsSeq(psSeq))
                    .build();

            respList.add(resp);

        }

        return respList;
    }

    @Override
    public PsDetailRespDto getPsDetail(Long userSeq, Long psSeq) {
        PsEntity ps = psRepository.findById(psSeq).get();

        if(ps == null){

            // 처리
        }

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
        if(psDefaultList == null){

            // 처리
        }


        // 의사
        List<DoctorEntity> docList = doctorRepository.findAllByPsSeq(psSeq);

        List<DoctorDto> docDtoList = new ArrayList<>();

        for(DoctorEntity doctor : docList){
            DoctorDto doctorDto = DoctorDto.builder()
                    .doctorSeq(doctor.getSeq())
                    .name(doctor.getName())
                    .profileImg(doctor.getImg())
                    .mainCategoryName(doctorMainRepository.getMainCategoryByDoctorSeq(doctor.getSeq()))
                    .build();

            docDtoList.add(doctorDto);
        }

        if(psDefaultList == null){

            // 처리
        }

        // 리뷰
        List<ReviewEntity> reviewList = reviewRepository.findAllByPsSeq(psSeq);


        if(reviewList == null){

            // 처리
        }

        List<ReviewRespDto> reviewRespDtoList = new ArrayList<>();

        for(ReviewEntity review : reviewList){

            ReviewRespDto reviewRespDto = ReviewRespDto.builder()
                    .reviewSeq(review.getSeq())
                    .userEmail(review.getUser().getEmail())
                    .point(review.getPoint())
                    .regDate(review.getRegDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                    .content(review.getContent())
                    .subCategoryName(reviewSubRepository.getSubCategoryByDoctorSeq(review.getSeq()))
                    .build();

            reviewRespDtoList.add(reviewRespDto);
        }

        // 전체
        PsDetailRespDto resp = PsDetailRespDto.builder()
                .psSeq(psSeq)
                .psName(ps.getName())
                .psIntro(ps.getIntro())
                .psAddress(ps.getAddress())
                .psProfileImg("server domain" + File.separator + ps.getProfileImg())
                .psNumber(ps.getNumber())
                .psEmail(ps.getEmail())
                .isFavorite(favoriteRepository.findByUserSeqPsSeq(userSeq, psSeq).isPresent())
                .subCategoryName(psMainSubRepository.getSubNameByPsSeq(psSeq))
                .totalRatingResult(totalRatingRepository.getResultByPsSeq(psSeq))
                .reviewTotalCount(reviewRepository.getCountByPsSeq(psSeq))
                .doctor(docDtoList)
                .review(reviewRespDtoList)
                .defaultTime(map)
                .build();


        return resp;
    }
}
