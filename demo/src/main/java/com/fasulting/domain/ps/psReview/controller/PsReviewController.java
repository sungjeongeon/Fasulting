package com.fasulting.domain.ps.psReview.controller;

import com.fasulting.domain.ps.psReview.dto.reqDto.AccuseReviewReq;
import com.fasulting.domain.ps.psReview.service.PsReviewService;
import com.fasulting.common.resp.ResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin("*")
@RequestMapping("/ps-review")
public class PsReviewController {

    private PsReviewService psReviewService;

    @Autowired
    public PsReviewController(PsReviewService psReviewService) {
        this.psReviewService = psReviewService;
    }

    /**
     * 1. 리뷰 신고
     * @param accuseReviewReq (seq)
     * @return
     */
    @PatchMapping("/accuse")
    public ResponseEntity<?> AccuseReview(@RequestBody AccuseReviewReq accuseReviewReq) {

        Long reviewSeq = accuseReviewReq.getReviewSeq();

        if(psReviewService.accuseReview(reviewSeq)) {
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }

        return ResponseEntity.status(200).body(ResponseBody.create(200, "fail"));
    }

}
