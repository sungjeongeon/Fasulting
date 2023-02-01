package com.fasulting.demo.customer.review.controller;

import com.fasulting.demo.common.review.respDto.ReviewRespDto;
import com.fasulting.demo.customer.review.dto.req.ReviewReqDto;
import com.fasulting.demo.customer.review.service.ReviewService;
import com.fasulting.demo.resp.ResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/review")
@CrossOrigin("*")
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
            return ResponseEntity.status(200).body(com.fasulting.demo.resp.ResponseBody.create(200, "success"));
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

        List<ReviewRespDto> resp = reviewService.getReviewList(userSeq);

        if(resp != null){
            return ResponseEntity.status(200).body(com.fasulting.demo.resp.ResponseBody.create(200, "success", resp));
        }
        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
    }

}
