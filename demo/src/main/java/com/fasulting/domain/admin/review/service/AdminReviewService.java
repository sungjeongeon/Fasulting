package com.fasulting.domain.admin.review.service;

import com.fasulting.common.dto.respDto.ReviewRespDto;
import com.fasulting.domain.admin.review.dto.reqDto.AdminReviewReqDto;

import java.util.List;

public interface AdminReviewService {
    List<ReviewRespDto> getAccusedReviewList();
    boolean deleteReview(AdminReviewReqDto adminReviewReqDto);
}
