package com.fasulting.demo.ps.psReview.service;


import com.fasulting.demo.common.rating.TotalRatingRepository;
import com.fasulting.demo.common.review.repository.ReviewRepository;
import com.fasulting.demo.entity.ReviewEntity;
import com.fasulting.demo.entity.TotalRatingEntity;
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
