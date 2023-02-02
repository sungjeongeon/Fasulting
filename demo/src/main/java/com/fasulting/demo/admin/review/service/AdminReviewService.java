package com.fasulting.demo.admin.review.service;

import com.fasulting.demo.common.review.respDto.ReviewRespDto;

import java.util.List;

public interface AdminReviewService {
    List<ReviewRespDto> getAccusedReviewList();
    boolean deleteReview(Long reviewSeq);
}
