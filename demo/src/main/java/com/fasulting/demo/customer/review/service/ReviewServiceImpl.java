package com.fasulting.demo.customer.review.service;

import com.fasulting.demo.common.consulting.repository.ConsultingRepository;
import com.fasulting.demo.common.review.repository.ReviewRepository;
import com.fasulting.demo.common.review.repository.ReviewSubRepository;
import com.fasulting.demo.common.review.respDto.ReviewRespDto;
import com.fasulting.demo.customer.review.dto.req.ReviewReqDto;
import com.fasulting.demo.entity.ConsultingEntity;
import com.fasulting.demo.entity.PsEntity;
import com.fasulting.demo.entity.ReviewEntity;
import com.fasulting.demo.entity.UserEntity;
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

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, ConsultingRepository consultingRepository,
                             ReviewSubRepository reviewSubRepository) {
        this.reviewRepository = reviewRepository;
        this.consultingRepository = consultingRepository;
        this.reviewSubRepository = reviewSubRepository;
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
