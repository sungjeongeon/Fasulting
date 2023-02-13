package com.fasulting.domain.ps.psReview.controller;

import com.fasulting.common.resp.ResponseBody;
import com.fasulting.common.util.LogCurrent;
import com.fasulting.domain.ps.psReview.dto.reqDto.AccuseReviewReq;
import com.fasulting.domain.ps.psReview.service.PsReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.fasulting.common.util.LogCurrent.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/ps-review")
public class PsReviewController {

    private final PsReviewService psReviewService;

    /**
     * 리뷰 신고
     * @param accuseReviewReq (seq)
     * @return
     */
    @PatchMapping("/accuse")
    public ResponseEntity<?> AccuseReview(@RequestBody AccuseReviewReq accuseReviewReq) {
        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));
        if(psReviewService.accuseReview(accuseReviewReq)) {
            log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }
        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
    }

}
