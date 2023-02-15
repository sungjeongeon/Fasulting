package com.fasulting.domain.user.review.service;

import com.fasulting.common.dto.respDto.ReviewRespDto;
import com.fasulting.domain.user.review.dto.reqDto.ReviewReqDto;

import java.util.List;

public interface ReviewService {

    boolean regReview(ReviewReqDto reviewReqDto);

    List<ReviewRespDto> getReviewList(Long userSeq);
}
