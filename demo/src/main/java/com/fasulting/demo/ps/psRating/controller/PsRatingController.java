package com.fasulting.demo.ps.psRating.controller;

import com.fasulting.demo.ps.psRating.service.PsRatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Configuration("*")
@RequestMapping("/ps-rating")
public class PsRatingController {

    private PsRatingService psRatingService;
    
    @Autowired
    public PsRatingController(PsRatingService psRatingService) {
        this.psRatingService = psRatingService;
    }

    /**
     * 1. 평점 조회
     * @param consultingSeq
     * @param psSeq
     * @return
     */
    @GetMapping("/{consultingSeq}/{psSeq}")
    public ResponseEntity<?> GetRatingInfo(@PathVariable("consultingSeq") int consultingSeq, @PathVariable("psSeq") int psSeq) {
        return null;
    }

}
