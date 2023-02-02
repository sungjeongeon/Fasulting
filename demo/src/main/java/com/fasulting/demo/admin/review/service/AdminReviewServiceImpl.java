package com.fasulting.demo.admin.review.service;

import com.fasulting.demo.common.review.repository.ReviewRepository;
import com.fasulting.demo.common.review.repository.ReviewSubRepository;
import com.fasulting.demo.common.review.respDto.ReviewRespDto;
import com.fasulting.demo.entity.ReviewEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AdminReviewServiceImpl implements AdminReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewSubRepository reviewSubRepository;

    public AdminReviewServiceImpl(ReviewRepository reviewRepository, ReviewSubRepository reviewSubRepository) {
        this.reviewRepository = reviewRepository;
        this.reviewSubRepository = reviewSubRepository;
    }


    @Transactional
    @Override
    public List<ReviewRespDto> getAccusedReviewList() {

        // 삭제 안 됐고 
        // 신고된 리뷰 리스트
        List<ReviewEntity> reviewList = reviewRepository.findAllByDecYnAndDelYn("Y", "N");

        if(reviewList.isEmpty()){

            // 처리
        }

        List<ReviewRespDto> accusedReviewList = new ArrayList<>();

        for(ReviewEntity review : reviewList){

            ReviewRespDto reviewRespDto = ReviewRespDto.builder()
                    .reviewSeq(review.getSeq())
                    .userEmail(review.getUser().getEmail())
                    .point(review.getPoint())
                    .regDate(review.getRegDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                    .content(review.getContent())
                    .subCategoryName(reviewSubRepository.getSubCategoryByDoctorSeq(review.getSeq()))
                    .build();

            accusedReviewList.add(reviewRespDto);
        }

        return accusedReviewList;
    }

    @Transactional
    @Override
    public boolean deleteReview(Long reviewSeq) {

        ReviewEntity review = reviewRepository.findById(reviewSeq).get();

        review.deleteReview();

        return true;
    }
}
