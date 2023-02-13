package com.fasulting.domain.ps.psReview.service;


import com.fasulting.common.RoleType;
import com.fasulting.common.util.LogCurrent;
import com.fasulting.domain.ps.psReview.dto.reqDto.AccuseReviewReq;
import com.fasulting.entity.ps.TotalRatingEntity;
import com.fasulting.entity.review.ReviewEntity;
import com.fasulting.repository.ps.PsRepository;
import com.fasulting.repository.ps.TotalRatingRepository;
import com.fasulting.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static com.fasulting.common.util.LogCurrent.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class PsReviewServiceImpl implements PsReviewService {

    private final ReviewRepository reviewRepository;
    private final TotalRatingRepository totalRatingRepository;
    private final PsRepository psRepository;

    /**
     * 리뷰 신고
     * @param accuseReviewReq
     * @return
     */
    @Transactional
    @Override
    public boolean accuseReview(AccuseReviewReq accuseReviewReq) {
        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));

        ReviewEntity review = reviewRepository.findById(accuseReviewReq.getReviewSeq()).orElseThrow(() -> {
            throw new NullPointerException();
        });

        review.updateByDec(RoleType.PS + "_" + accuseReviewReq.getPsSeq(), LocalDateTime.now());
        reviewRepository.save(review);

        TotalRatingEntity totalRating = totalRatingRepository.findByPs(review.getPs()).orElseThrow(() -> {
            throw new NullPointerException();
        });
        totalRating.updateByDel(review.getPoint());

        totalRatingRepository.save(totalRating);
        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
        return true;
    }

}
