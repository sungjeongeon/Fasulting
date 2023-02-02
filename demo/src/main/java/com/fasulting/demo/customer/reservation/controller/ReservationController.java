package com.fasulting.demo.customer.reservation.controller;

import com.fasulting.demo.customer.reservation.dto.reqDto.CancelReservationReqDto;
import com.fasulting.demo.customer.reservation.dto.reqDto.RegReservationReqDto;
import com.fasulting.demo.customer.reservation.dto.respDto.PostReservationRespDto;
import com.fasulting.demo.customer.reservation.dto.respDto.PreReservationRespDto;
import com.fasulting.demo.customer.reservation.dto.respDto.ReportRespDto;
import com.fasulting.demo.customer.reservation.dto.respDto.ReservationTableRespDto;
import com.fasulting.demo.customer.reservation.service.ReservationService;
import com.fasulting.demo.resp.ResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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

    /**
     * 병원 예약 가능 시간 조회
     *
     * @param psSeq
     * @return
     */
    @GetMapping("/{psSeq}")
    public ResponseEntity<?> getReservationTable(@PathVariable Long psSeq) {

        LocalDateTime current = LocalDateTime.now();

        ReservationTableRespDto resp = reservationService.getReservationTable(psSeq, current);

        if (resp != null) {
            return ResponseEntity.status(200).body(com.fasulting.demo.resp.ResponseBody.create(200, "success", resp));
        }

        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
    }

    /**
     * 예약 등록
     *
     * @param regReservationReqDto
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<?> regReservation(@RequestBody RegReservationReqDto regReservationReqDto) {

        if (reservationService.addReservation(regReservationReqDto)) {
            return ResponseEntity.status(200).body(com.fasulting.demo.resp.ResponseBody.create(200, "success"));
        }

        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
    }

    // 지난 예약 조회
    @GetMapping("/pre/{userSeq}")
    public ResponseEntity<?> getPreReservationList(@PathVariable Long userSeq) {

        List<PreReservationRespDto> resp = reservationService.getPreReservationList(userSeq);

        if (resp != null) {
            return ResponseEntity.status(200).body(com.fasulting.demo.resp.ResponseBody.create(200, "success", resp));
        }

        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
    }


    // 미래 예약 조회
    @GetMapping("/post/{userSeq}")
    public ResponseEntity<?> getPostReservationList(@PathVariable Long userSeq) {

        List<PostReservationRespDto> resp = reservationService.getPostReservationList(userSeq);

        if (resp != null) {
            return ResponseEntity.status(200).body(com.fasulting.demo.resp.ResponseBody.create(200, "success", resp));
        }

        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
     }

    // 예약 취소
    @PatchMapping
    public ResponseEntity<?> cancelReservation(@RequestBody CancelReservationReqDto cancelReservationReqDto) {

        if (reservationService.cancelReservation(cancelReservationReqDto)) {
            return ResponseEntity.status(200).body(com.fasulting.demo.resp.ResponseBody.create(200, "success"));
        }

        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
    }

    // 상담 결과 상세 조회
    @GetMapping("/report/{userSeq}/{consultingSeq}")
    public ResponseEntity<?> getReport(@PathVariable Long userSeq, @PathVariable Long consultingSeq) {

        ReportRespDto resp = reservationService.getReport(userSeq, consultingSeq);

        if (resp != null) {
            return ResponseEntity.status(200).body(com.fasulting.demo.resp.ResponseBody.create(200, "success", resp));
        }

        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));

    }

}
