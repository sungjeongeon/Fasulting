package com.fasulting.demo.ps.psReview.service;


import com.fasulting.demo.entity.ReviewEntity;
import com.fasulting.demo.ps.psReview.repository.PsReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PsReviewServiceImpl implements PsReviewService {

    @Autowired
    private PsReviewRepository psReviewRepository;;

    @Transactional
    @Override
    public boolean accuseReview(Long seq) {
        ReviewEntity review = psReviewRepository.findById(seq).get();

        review.accuseReview();

        return true;
    }

}
