package com.fasulting.demo.customer.reservation.controller;

import com.fasulting.demo.customer.reservation.dto.respDto.ReservationTableRespDto;
import com.fasulting.demo.customer.reservation.service.ReservationService;
import com.fasulting.demo.resp.ResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/reservation")
@CrossOrigin("*")
public class ReservationController {

    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // 병원 예약 가능 시간 조회 - jwt
    @GetMapping("/{psSeq}")
    public ResponseEntity<?> getReservationTable(@PathVariable Long psSeq) {

        LocalDateTime current = LocalDateTime.now();

        ReservationTableRespDto resp = reservationService.getReservationTable(psSeq, current);

        if (resp != null) {
            return ResponseEntity.status(200).body(com.fasulting.demo.resp.ResponseBody.create(200, "success", resp));
        }

        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
    }

//    // 예약 등록 - jwt
//    @PostMapping("/register")
//    public ResponseEntity<?> ReservationRegister(@RequestBody Consulting consulting) {
//        return null; // success OR fail
//    }
//
//    // 미래 예약 조회
//    @GetMapping("/post/{userId}")
//    public ResponseEntity<?> PostReservationList(@PathVariable int userId) {
//        return null; // 미래 예약 리스트 반환
//    }
//
//    // 지난 예약 조회
//    @GetMapping("/pre/{userId}")
//    public ResponseEntity<?> PreReservationList(@PathVariable int userId) {
//        return null; // 지난 예야기 리스트 반환
//    }
//
//    // 예약 취소
//    @PatchMapping("/{consultingId}")
//    public ResponseEntity<?> CancelReservation(@PathVariable int consultingId) {
//        return null; // success OR fail
//    }
//
//    // 상담 결과 상세 조회
//    @GetMapping("/report/{consultingId}")
//    public ResponseEntity<?> Report(@PathVariable int consultingId) {
//        return null; // 상담 결과 반환
//    }

}
