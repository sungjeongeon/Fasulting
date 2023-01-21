package com.fasulting.demo.admin.review.controller;

import com.fasulting.demo.admin.review.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/review")
@CrossOrigin("*")
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /**
     * 1. 신고된 리뷰 조회
     * @return 신고된 리뷰 리스트
     */
    @GetMapping
    public ResponseEntity<?> getAccusedReviewList() {
        return null;
    }

    /**
     * 2. 리뷰 삭제
     * @return success or fail
     */
    @DeleteMapping
    public ResponseEntity<?> deleteReview() {
        return null;
    }

}
