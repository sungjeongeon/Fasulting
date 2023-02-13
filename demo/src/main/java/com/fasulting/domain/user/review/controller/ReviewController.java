package com.fasulting.domain.user.review.controller;

import com.fasulting.common.dto.respDto.ReviewRespDto;
import com.fasulting.domain.user.review.dto.reqDto.ReviewReqDto;
import com.fasulting.domain.user.review.service.ReviewService;
import com.fasulting.common.resp.ResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/review")
//@CrossOrigin("*")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /**
     * 리뷰 등록
     * @param reviewReq
     * @return
     */
    @PostMapping
    public ResponseEntity<?> regReview(@RequestBody ReviewReqDto reviewReq) {
        if(reviewService.regReview(reviewReq)){
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }
        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
    }

    /**
     * 사용자가 작성한 리뷰 조회
     * @param userSeq
     * @return
     */
    @GetMapping("/{userSeq}")
    public ResponseEntity<?> getReviewList(@PathVariable Long userSeq) {

        log.info("getReviewList - call");

        List<ReviewRespDto> resp = reviewService.getReviewList(userSeq);

        if(!resp.isEmpty()){
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success", resp));
        }
        return ResponseEntity.status(204).body(ResponseBody.create(204, "fail"));
    }

}
