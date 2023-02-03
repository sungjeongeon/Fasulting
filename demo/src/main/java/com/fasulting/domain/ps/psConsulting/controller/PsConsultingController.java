package com.fasulting.domain.ps.psConsulting.controller;

import com.fasulting.common.resp.ResponseBody;
import com.fasulting.domain.ps.psConsulting.service.PsConsultingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/ps-consulting")
@CrossOrigin("*")
public class PsConsultingController {

    private PsConsultingService psConsultingService;
    
    public PsConsultingController(PsConsultingService psConsultingService) {
        this.psConsultingService = psConsultingService;
    }

//    @GetMapping("/download/{reservationSeq}")
//    public ResponseEntity<?> getBeforeImg(@PathVariable Long reservationSeq) {
//
//        psConsultingService.getBeforeImg(reservationSeq);
//
//        ResponseEntity.status(200).contentType().contentLength().header();
//        if (false) {
//            return ResponseEntity.status(200).body(com.fasulting.common.resp.ResponseBody.create(200, "success"));
//        }
//
//        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
//    }


    // 1. 상담 입장
    // 2. 상담 사전 진행
    // 3. 상담 진행
    // 4. 상담 결과 작성

}
