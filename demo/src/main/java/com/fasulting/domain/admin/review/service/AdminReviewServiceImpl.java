package com.fasulting.domain.admin.review.service;

import com.fasulting.common.RoleType;
import com.fasulting.common.util.LogCurrent;
import com.fasulting.domain.admin.review.dto.reqDto.AdminReviewReqDto;
import com.fasulting.entity.ps.TotalRatingEntity;
import com.fasulting.repository.ps.TotalRatingRepository;
import com.fasulting.repository.review.ReviewRepository;
import com.fasulting.repository.review.ReviewSubRepository;
import com.fasulting.common.dto.respDto.ReviewRespDto;
import com.fasulting.entity.review.ReviewEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.fasulting.common.util.LogCurrent.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminReviewServiceImpl implements AdminReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewSubRepository reviewSubRepository;
    private final TotalRatingRepository totalRatingRepository;

    /**
     * 신고된 리뷰 리스트 반환
     * @return
     */
    @Transactional
    @Override
    public List<ReviewRespDto> getAccusedReviewList() {

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));
        // 삭제 안 된 신고된 리뷰 리스트
        List<ReviewEntity> reviewList = reviewRepository.findAllByDecYnAndDelYn("Y", "N");

        List<ReviewRespDto> accusedReviewList = new ArrayList<>();

        for(ReviewEntity review : reviewList){

            ReviewRespDto reviewRespDto = ReviewRespDto.builder()
                    .reviewSeq(review.getSeq())
                    .userSeq(review.getUser().getSeq())
                    .psSeq(review.getPs().getSeq())
                    .psName(review.getPs().getName())
                    .userEmail(review.getUser().getEmail())
                    .point(review.getPoint())
                    .regDate(review.getRegDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                    .content(review.getContent())
                    .subCategoryName(reviewSubRepository.getSubCategoryByReviewSeq(review.getSeq()))
                    .build();

            accusedReviewList.add(reviewRespDto);
        }

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
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

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));
        ReviewEntity review = reviewRepository.findById(adminReviewReqDto.getReviewSeq()).orElseThrow(() -> {
            throw new NullPointerException();
        });

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
        review.updateByDel(RoleType.ADMIN + "_" + adminReviewReqDto.getAdminSeq(), LocalDateTime.now());

        reviewRepository.save(review);

        TotalRatingEntity totalRating = totalRatingRepository.findByPs(review.getPs()).orElseThrow(() -> {
            throw new NullPointerException();
        });
        if(totalRating.getCount().equals(1)){
            totalRatingRepository.delete(totalRating);
        }
        else {
            totalRating.updateByDel(review.getPoint());
            totalRatingRepository.save(totalRating);
        }
        return true;
    }
}
