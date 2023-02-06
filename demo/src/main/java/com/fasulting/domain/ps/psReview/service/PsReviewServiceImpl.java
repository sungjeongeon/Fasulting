package com.fasulting.domain.ps.psReview.service;


import com.fasulting.domain.ps.psReview.dto.reqDto.AccuseReviewReq;
import com.fasulting.entity.ps.TotalRatingEntity;
import com.fasulting.entity.review.ReviewEntity;
import com.fasulting.repository.ps.PsRepository;
import com.fasulting.repository.ps.TotalRatingRepository;
import com.fasulting.repository.review.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class PsReviewServiceImpl implements PsReviewService {

    private ReviewRepository reviewRepository;
    private TotalRatingRepository totalRatingRepository;
    private PsRepository psRepository;

    @Autowired
    public PsReviewServiceImpl(ReviewRepository reviewRepository, TotalRatingRepository totalRatingRepository,
                               PsRepository psRepository) {
        this.reviewRepository = reviewRepository;
        this.totalRatingRepository = totalRatingRepository;
        this.psRepository = psRepository;
    }

    /**
     * 리뷰 신고
     * @param accuseReviewReq
     * @return
     */
    @Transactional
    @Override
    public boolean accuseReview(AccuseReviewReq accuseReviewReq) {

        if(!reviewRepository.findById(accuseReviewReq.getReviewSeq()).isPresent()){
            return false;
        }

        if(!psRepository.findById(accuseReviewReq.getPsSeq()).isPresent()){
            return false;
        }

        ReviewEntity review = reviewRepository.findById(accuseReviewReq.getReviewSeq()).get();

        review.updateByDec("ps_" + accuseReviewReq.getPsSeq(), LocalDateTime.now());
        reviewRepository.save(review);

        TotalRatingEntity totalRating = totalRatingRepository.findByPs(review.getPs()).get();
        totalRating.updateByDel(review.getPoint());

        totalRatingRepository.save(totalRating);

        return true;
    }

}
