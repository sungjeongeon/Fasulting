package com.fasulting.demo.ps.psReservation.controller;

import com.fasulting.demo.ps.ps.controller.PsController;
import com.fasulting.demo.ps.psReservation.request.EditReservationReq;
import com.fasulting.demo.ps.psReservation.service.PsReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/ps-reservation")
@CrossOrigin("*")
public class PsReservationController {

    private PsReservationService psReservationService;

    @Autowired
    public PsReservationController(PsReservationService psReservationService) {
        this.psReservationService = psReservationService;
    }

    /**
     * 1. 병원 예약 테이블 조회
     * @param seq
     * @return
     */
    @GetMapping("/{seq}")
    public ResponseEntity<?> getReservationTable(@PathVariable int seq) {
        return null;
    }


    /**
     * 2. 예약 테이블 수정
     * @param editReservationReq
     * @return
     */
    @PutMapping("/edit")
    public ResponseEntity<?> editReservationTable(@RequestBody EditReservationReq editReservationReq) {
        return null;
    }

    /**
     * 3. 예약 취소
     * @param seq
     * @return
     */
    @PatchMapping("/{seq}")
    public ResponseEntity<?> cancelReservation(@PathVariable int seq) {
        return null;
    }

    /**
     * 4. 미래 예약 조회
     * @param seq
     * @return
     */
    @GetMapping("/post/{seq}")
    public ResponseEntity<?> getPostReservationInfo(@PathVariable int seq) {
        return null;
    }

    /**
     * 5. 지난 예약 조회
     * @param seq
     * @return
     */
    @GetMapping("/pre/{seq}")
    public ResponseEntity<?> getPreReservationInfo(@PathVariable int seq) {
        return null;
    }

    /**
     * 6. 상담 결과 상세 조회
     * @param consultingSeq
     * @return
     */
    @GetMapping("/pre/detail/{consultingSeq}")
    public ResponseEntity<?> getPreReservationDetail(@PathVariable int consultingSeq) {
        return null;
    }

}
