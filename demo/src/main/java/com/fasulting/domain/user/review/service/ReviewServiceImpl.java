package com.fasulting.domain.user.review.service;

import com.fasulting.common.dto.respDto.ReviewRespDto;
import com.fasulting.domain.user.review.dto.reqDto.ReviewReqDto;
import com.fasulting.entity.category.SubCategoryEntity;
import com.fasulting.entity.consulting.ConsultingEntity;
import com.fasulting.entity.ps.PsEntity;
import com.fasulting.entity.ps.TotalRatingEntity;
import com.fasulting.entity.review.ReviewEntity;
import com.fasulting.entity.review.ReviewSubEntity;
import com.fasulting.entity.user.UserEntity;
import com.fasulting.repository.consulting.ConsultingRepository;
import com.fasulting.repository.ps.TotalRatingRepository;
import com.fasulting.repository.reservation.ReservationSubRepository;
import com.fasulting.repository.review.ReviewRepository;
import com.fasulting.repository.review.ReviewSubRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final ConsultingRepository consultingRepository;
    private final ReviewSubRepository reviewSubRepository;
    private final TotalRatingRepository totalRatingRepository;
    private final ReservationSubRepository reservationSubRepository;

    @Transactional
    @Override
    public boolean regReview(ReviewReqDto reviewReqDto) {

        Long consultingSeq = reviewReqDto.getConsultingSeq();

        ConsultingEntity consulting = consultingRepository.findById(consultingSeq).orElseThrow(() -> {
            throw new NullPointerException();
        });
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

        // 리뷰 서브 매핑관계 등록
        List<SubCategoryEntity> subList = reservationSubRepository.getAllByReservation(consulting.getReservation().getSeq());

        for(SubCategoryEntity sub : subList) {
            ReviewSubEntity rs = ReviewSubEntity.builder()
                    .review(review)
                    .subCategory(sub)
                    .build();
        }

        // 통계 평점이 없을 경우 생성
        if(!totalRatingRepository.findByPs(ps).isPresent()){
            TotalRatingEntity totalRating = TotalRatingEntity.builder()
                    .ps(ps)
                    .build();

            totalRatingRepository.save(totalRating);
        }

        // 통계 평점 최신화
        TotalRatingEntity totalRating = totalRatingRepository.findByPs(ps).orElseThrow(() -> {
            throw new NullPointerException();
        });
        totalRating.updateByReg(reviewReqDto.getPoint());
        totalRatingRepository.save(totalRating);

        return true;
    }

    @Override
    public List<ReviewRespDto> getReviewList(Long userSeq) {

        log.info(userSeq + "");

        // 리뷰
        List<ReviewEntity> reviewList = reviewRepository.findAllByUserSeq(userSeq);

        List<ReviewRespDto> reviewRespDtoList = new ArrayList<>();

        for(ReviewEntity review : reviewList){

            ReviewRespDto reviewRespDto = ReviewRespDto.builder()
                    .userSeq(review.getUser().getSeq())
                    .psSeq(review.getPs().getSeq())
                    .psName(review.getPs().getName())
                    .reviewSeq(review.getSeq())
                    .userEmail(review.getUser().getEmail())
                    .point(review.getPoint())
                    .regDate(review.getRegDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                    .content(review.getContent())
                    .subCategoryName(reviewSubRepository.getSubCategoryByReviewSeq(review.getSeq()))
                    .build();

            reviewRespDtoList.add(reviewRespDto);
        }

        log.info(reviewRespDtoList.toString());

        return reviewRespDtoList;
    }
}
