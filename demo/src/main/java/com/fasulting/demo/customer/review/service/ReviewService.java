package com.fasulting.demo.customer.review.service;

import com.fasulting.demo.common.review.respDto.ReviewRespDto;
import com.fasulting.demo.customer.review.dto.req.ReviewReqDto;

import java.util.List;

public interface ReviewService {

    boolean regReview(ReviewReqDto reviewReqDto);

    List<ReviewRespDto> getReviewList(Long userSeq);
}
