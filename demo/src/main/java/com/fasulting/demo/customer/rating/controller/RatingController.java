package com.fasulting.demo.customer.rating.controller;

import com.fasulting.demo.customer.rating.service.RatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/rating")
@CrossOrigin("*")
public class RatingController {

//    private RatingService ratingService;
//
//    @Autowired
//    public RatingController(RatingService ratingService) {
//        this.ratingService = ratingService;
//    }
//
//    // 1. 평점 작성
//    // rating => rating_point
//    @PostMapping("/{consultingId}/{userId}")
//    public ResponseEntity<?> RegisterRating(@PathVariable("consultingId") int consultingId, @PathVariable("userId") int userId,
//                                            @RequestBody Rating rating) {
//        return null; // success OR fail
//    }


}
