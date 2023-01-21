package com.fasulting.demo.ps.psReview.controller;

import com.fasulting.demo.ps.psReview.request.AccuseReviewReq;
import com.fasulting.demo.ps.psReview.service.PsReviewService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Configuration("*")
@RequestMapping("/ps-review")
public class PsReviewController {

    private PsReviewService psReviewService;

    @Autowired
    public PsReviewController(PsReviewService psReviewService) {
        this.psReviewService = psReviewService;
    }

    /**
     * 1. 리뷰 조회
     * @param seq
     * @return
     */
    @GetMapping("/{seq}")
    public ResponseEntity<?> GetReviewInfo(@PathVariable int seq) {
        return null;
    }

    /**
     * 2. 리뷰 신고
     * @param accuseReviewReq (seq)
     * @return
     */
    @PatchMapping("/accuse")
    public ResponseEntity<?> AccuseReview(@RequestBody AccuseReviewReq accuseReviewReq) {
        return null;
    }

}
