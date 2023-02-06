package com.fasulting.domain.admin.review.service;

import com.fasulting.domain.admin.review.dto.reqDto.AdminReviewReqDto;
import com.fasulting.repository.review.ReviewRepository;
import com.fasulting.repository.review.ReviewSubRepository;
import com.fasulting.common.dto.respDto.ReviewRespDto;
import com.fasulting.entity.review.ReviewEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
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

    /**
     * 리뷰 삭제 요청 처리
     * @param adminReviewReqDto
     * @return
     */
    @Transactional
    @Override
    public boolean deleteReview(AdminReviewReqDto adminReviewReqDto) {

        ReviewEntity review = reviewRepository.findById(adminReviewReqDto.getReviewSeq()).get();

        review.updateByDel("admin_" + adminReviewReqDto.getAdminSeq(), LocalDateTime.now());

        reviewRepository.save(review);
        return true;
    }
}
