package com.fasulting.demo.customer.main.service;

import com.fasulting.demo.common.rating.TotalRatingRepository;
import com.fasulting.demo.common.review.ReviewRepository;
import com.fasulting.demo.customer.favorite.repository.FavoriteRepository;
import com.fasulting.demo.customer.main.dto.respDto.MainCategoryRespDto;
import com.fasulting.demo.customer.main.dto.respDto.PsDetailRespDto;
import com.fasulting.demo.customer.main.dto.respDto.PsListRespDto;
import com.fasulting.demo.customer.main.dto.respDto.SubCategoryListRespDto;
import com.fasulting.demo.entity.*;
import com.fasulting.demo.ps.ps.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

    public MainServiceImpl(MainCategoryRepository mainCategoryRepository, SubCategoryRepository subCategoryRepository,
                           PsMainRepository psMainRepository, PsMainSubRepository psMainSubRepository,
                           TotalRatingRepository totalRatingRepository, ReviewRepository reviewRepository ,
                           PsRepository psRepository, FavoriteRepository favoriteRepository,
                           PsDefaultRepository psDefaultRepository) {
        this.mainCategoryRepository = mainCategoryRepository;
        this.subCategoryRepository = subCategoryRepository;
        this.psMainRepository = psMainRepository;
        this.psMainSubRepository = psMainSubRepository;
        this.totalRatingRepository = totalRatingRepository;
        this.reviewRepository = reviewRepository;
        this.psRepository = psRepository;
        this.favoriteRepository = favoriteRepository;
        this.psDefaultRepository = psDefaultRepository;
    }

    @Autowired


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

        List<SubCategoryListRespDto> returnList = new ArrayList<>();

        for(SubCategoryEntity sub : list) {
            SubCategoryListRespDto resp = SubCategoryListRespDto.builder()
                    .subSeq(sub.getSeq())
                    .subName(sub.getName())
                    .build();

            returnList.add(resp);
        }

        log.info("sub category list success" + returnList.toString());
        return returnList;
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

            return null;
        }

        PsDetailRespDto respList = PsDetailRespDto.builder()
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
                .build();

        // 운영시간

        List<PsDefaultEntity> test = psDefaultRepository.findAllByPsSeq(psSeq);

        for(PsDefaultEntity t : test){
            log.info("test " + t.toString());
        }

//        log.info("test list " + test.toString());
        // 의사
        // 리뷰



        return respList;
    }
}
