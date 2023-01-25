package com.fasulting.demo.customer.review.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/review")
@CrossOrigin("*")
public class ReviewController {



    // 1. 리뷰 작성
    // review => content
//    @PostMapping("/{consultingId}/{userId}")
//    public ResponseEntity<?> RegisterReview(@PathVariable("consultingId") int consultingId, @PathVariable("userId") int userId,
//                                            @RequestBody Review review) {
//        return null; // success OR fail
//    }
//
//    // 2. 리뷰 조회
//    @GetMapping("/{userId}")
//    public ResponseEntity<?> ReviewList(@PathVariable int userId) {
//        return null; // review List 반환
//    }

}
