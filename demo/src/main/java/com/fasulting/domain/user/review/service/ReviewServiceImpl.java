package com.fasulting.domain.user.review.service;

import com.fasulting.entity.consulting.ConsultingEntity;
import com.fasulting.entity.ps.PsEntity;
import com.fasulting.entity.ps.TotalRatingEntity;
import com.fasulting.entity.review.ReviewEntity;
import com.fasulting.entity.user.UserEntity;
import com.fasulting.repository.consulting.ConsultingRepository;
import com.fasulting.repository.ps.TotalRatingRepository;
import com.fasulting.repository.review.ReviewRepository;
import com.fasulting.repository.review.ReviewSubRepository;
import com.fasulting.common.dto.respDto.ReviewRespDto;
import com.fasulting.domain.user.review.dto.reqDto.ReviewReqDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final ConsultingRepository consultingRepository;
    private final ReviewSubRepository reviewSubRepository;
    private final TotalRatingRepository totalRatingRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, ConsultingRepository consultingRepository,
                             ReviewSubRepository reviewSubRepository, TotalRatingRepository totalRatingRepository) {
        this.reviewRepository = reviewRepository;
        this.consultingRepository = consultingRepository;
        this.reviewSubRepository = reviewSubRepository;
        this.totalRatingRepository = totalRatingRepository;
    }

    @Transactional
    @Override
    public boolean regReview(ReviewReqDto reviewReqDto) {

        Long consultingSeq = reviewReqDto.getConsultingSeq();

        ConsultingEntity consulting = consultingRepository.findById(consultingSeq).get();
        PsEntity ps = consulting.getPs();
        UserEntity user = consulting.getUser();

        if(reviewRepository.findByConsulting(consulting).isPresent()){
            return false;
        }

        ReviewEntity review = ReviewEntity.builder()
                .consulting(consulting)
                .ps(ps)
                .user(user)
                .content(reviewReqDto.getReviewContent())
                .point(reviewReqDto.getPoint())
                .build();

        reviewRepository.save(review);

        // 통계 평점이 없을 경우 생성
        if(!totalRatingRepository.findByPs(ps).isPresent()){
            TotalRatingEntity totalRating = TotalRatingEntity.builder()
                    .ps(ps)
                    .build();

            totalRatingRepository.save(totalRating);
        }

        // 통계 평점 최신화
        TotalRatingEntity totalRating = totalRatingRepository.findByPs(ps).get();
        totalRating.updateByReg(reviewReqDto.getPoint());
        totalRatingRepository.save(totalRating);

        return true;
    }

    @Override
    public List<ReviewRespDto> getReviewList(Long userSeq) {

        // 리뷰
        List<ReviewEntity> reviewList = reviewRepository.findAllByUserSeq(userSeq);


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

        return reviewRespDtoList;
    }
}
