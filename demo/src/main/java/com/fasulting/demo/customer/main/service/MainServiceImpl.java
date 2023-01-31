package com.fasulting.demo.customer.main.service;

import com.fasulting.demo.common.rating.TotalRatingRepository;
import com.fasulting.demo.common.review.ReviewRepository;
import com.fasulting.demo.customer.main.dto.respDto.MainCategoryRespDto;
import com.fasulting.demo.customer.main.dto.respDto.PsListRespDto;
import com.fasulting.demo.customer.main.dto.respDto.SubCategoryListRespDto;
import com.fasulting.demo.entity.MainCategoryEntity;
import com.fasulting.demo.entity.PsEntity;
import com.fasulting.demo.entity.SubCategoryEntity;
import com.fasulting.demo.ps.ps.repository.MainCategoryRepository;
import com.fasulting.demo.ps.ps.repository.PsMainRepository;
import com.fasulting.demo.ps.ps.repository.PsMainSubRepository;
import com.fasulting.demo.ps.ps.repository.SubCategoryRepository;
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

    private final PsMainRepository psMainRepository;

    private  final PsMainSubRepository psMainSubRepository;

    private final TotalRatingRepository totalRatingRepository;

    private final ReviewRepository reviewRepository;

    public MainServiceImpl(MainCategoryRepository mainCategoryRepository, SubCategoryRepository subCategoryRepository,
                           PsMainRepository psMainRepository, PsMainSubRepository psMainSubRepository,
                           TotalRatingRepository totalRatingRepository, ReviewRepository reviewRepository) {
        this.mainCategoryRepository = mainCategoryRepository;
        this.subCategoryRepository = subCategoryRepository;
        this.psMainRepository = psMainRepository;
        this.psMainSubRepository = psMainSubRepository;
        this.totalRatingRepository = totalRatingRepository;
        this.reviewRepository = reviewRepository;
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
}
