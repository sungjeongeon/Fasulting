package com.fasulting.demo.customer.reservation.controller;

import com.fasulting.demo.customer.reservation.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// request, response 객체 논의 필요

@Slf4j
@RestController
@RequestMapping("/reservation")
@CrossOrigin("*")
public class ReservationController {

//    private ReservationService reservationService;
//
//    @Autowired
//    public ReservationController(ReservationService reservationService) {
//        this.reservationService = reservationService;
//    }
//
//    // 1. 병원 예약 테이블 조회 - jwt
//    @GetMapping("/{psId}")
//    public ResponseEntity<?> ReservationList(@PathVariable int psId) {
//        return null; // 병원 예약 테이블 반환
//    }
//
//    // 2. 예약 등록 - jwt
//    @PostMapping("/register")
//    public ResponseEntity<?> ReservationRegister(@RequestBody Consulting consulting) {
//        return null; // success OR fail
//    }
//
//    // 3. 미래 예약 조회 (논의)
//    @GetMapping("/post/{userId}")
//    public ResponseEntity<?> PostReservationList(@PathVariable int userId) {
//        return null; // 미래 예약 리스트 반환
//    }
//
//    // 4. 지난 예약 조회 (논의)
//    @GetMapping("/pre/{userId}")
//    public ResponseEntity<?> PreReservationList(@PathVariable int userId) {
//        return null; // 지난 예야기 리스트 반환
//    }
//
//    // 5. 예약 취소 (논의)
//    @PatchMapping("/{consultingId}")
//    public ResponseEntity<?> CancelReservation(@PathVariable int consultingId) {
//        return null; // success OR fail
//    }
//
//    // 6. 상담 결과 상세 조회 (논의)
//    @GetMapping("/report/{consultingId}")
//    public ResponseEntity<?> Report(@PathVariable int consultingId) {
//        return null; // 상담 결과 반환
//    }

}
