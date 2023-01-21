package com.fasulting.demo.ps.psConsulting.controller;

import com.fasulting.demo.ps.psConsulting.service.PsConsultingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/ps-consulting")
@CrossOrigin("*")
public class PsConsultingController {

    private PsConsultingService psConsultingService;
    
    public PsConsultingController(PsConsultingService psConsultingService) {
        this.psConsultingService = psConsultingService;
    }
    
    // 1. 상담 입장
    // 2. 상담 사전 진행
    // 3. 상담 진행
    // 4. 상담 결과 작성

}
