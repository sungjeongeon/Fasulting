package com.fasulting.domain.admin.review.controller;

import com.fasulting.domain.admin.review.dto.reqDto.AdminReviewReqDto;
import com.fasulting.domain.admin.review.service.AdminReviewService;
import com.fasulting.repository.review.ReviewRepository;
import com.fasulting.common.dto.respDto.ReviewRespDto;
import com.fasulting.common.resp.ResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin/review")
@CrossOrigin("*")
public class AdminReviewController {
    private final ReviewRepository reviewRepository;

    private AdminReviewService adminReviewService;

    @Autowired
    public AdminReviewController(AdminReviewService adminReviewService,
                                 ReviewRepository reviewRepository) {
        this.adminReviewService = adminReviewService;
        this.reviewRepository = reviewRepository;
    }

    /**
     * 1. 신고된 리뷰 조회
     * @return 신고된 리뷰 리스트
     */
    @GetMapping
    public ResponseEntity<?> getAccusedReviewList() {

        List<ReviewRespDto> accusedReviewList = adminReviewService.getAccusedReviewList();

//        log.info(accusedReviewList.toString());

        if (!accusedReviewList.isEmpty()) {
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success", accusedReviewList));
        }

        return ResponseEntity.status(204).body(ResponseBody.create(204, "fail")); // 작성한 리뷰 없음

    }

    /**
     * 2. 리뷰 삭제
     * @return success or fail
     */
    @PatchMapping
    public ResponseEntity<?> deleteReview(@RequestBody AdminReviewReqDto adminReviewReq) {

        if (adminReviewService.deleteReview(adminReviewReq)) {
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }

        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail")); // 리뷰 삭제 실패

    }

}
