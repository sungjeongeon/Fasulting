package com.fasulting.domain.ps.psReview.service;


import com.fasulting.repository.ps.TotalRatingRepository;
import com.fasulting.repository.review.ReviewRepository;
import com.fasulting.entity.review.ReviewEntity;
import com.fasulting.entity.ps.TotalRatingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PsReviewServiceImpl implements PsReviewService {

    private ReviewRepository reviewRepository;
    private TotalRatingRepository totalRatingRepository;

    @Autowired
    public PsReviewServiceImpl(ReviewRepository reviewRepository, TotalRatingRepository totalRatingRepository) {
        this.reviewRepository = reviewRepository;
        this.totalRatingRepository = totalRatingRepository;
    }


    @Transactional
    @Override
    public boolean accuseReview(Long reviewSeq) {
        ReviewEntity review = reviewRepository.findById(reviewSeq).get();

        review.accuseReview();
        reviewRepository.save(review);

        TotalRatingEntity totalRating = totalRatingRepository.findByPs(review.getPs()).get();
        totalRating.updateByDel(review.getPoint());

        totalRatingRepository.save(totalRating);

        return true;
    }

}
