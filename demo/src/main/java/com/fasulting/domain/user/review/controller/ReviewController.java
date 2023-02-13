package com.fasulting.domain.user.review.controller;

import com.fasulting.common.dto.respDto.ReviewRespDto;
import com.fasulting.common.resp.ResponseBody;
import com.fasulting.domain.user.review.dto.reqDto.ReviewReqDto;
import com.fasulting.domain.user.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fasulting.common.util.LogCurrent.*;

@Slf4j
@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * 리뷰 등록
     * @param reviewReq
     * @return
     */
    @PostMapping
    public ResponseEntity<?> regReview(@RequestBody ReviewReqDto reviewReq) {

        log.info(logCurrent(getClassName(), getMethodName(), START));
        if(reviewService.regReview(reviewReq)){
            log.info(logCurrent(getClassName(), getMethodName(), END));
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }
        log.info(logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
    }

    /**
     * 사용자가 작성한 리뷰 조회
     * @param userSeq
     * @return
     */
    @GetMapping("/{userSeq}")
    public ResponseEntity<?> getReviewList(@PathVariable Long userSeq) {

        log.info(logCurrent(getClassName(), getMethodName(), START));

        List<ReviewRespDto> resp = reviewService.getReviewList(userSeq);

        if(!resp.isEmpty()){
            log.info(logCurrent(getClassName(), getMethodName(), END));
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success", resp));
        }
        log.info(logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(204).body(ResponseBody.create(204, "fail"));
    }

}
