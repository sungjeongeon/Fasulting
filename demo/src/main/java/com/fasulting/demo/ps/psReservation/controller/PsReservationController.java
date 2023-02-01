package com.fasulting.demo.ps.psReservation.controller;


import com.fasulting.demo.common.reservation.repository.ReservationRepository;
import com.fasulting.demo.common.review.repository.ReviewRepository;
import com.fasulting.demo.ps.psReservation.request.ReservationReq;
import com.fasulting.demo.ps.psReservation.service.PsReservationService;
import com.fasulting.demo.resp.ResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/ps-reservation")
@CrossOrigin("*")
public class PsReservationController {
    private final ReservationRepository reservationRepository;
    private final ReviewRepository reviewRepository;

    private PsReservationService psReservationService;

    @Autowired
    public PsReservationController(PsReservationService psReservationService,
                                   ReviewRepository reviewRepository,
                                   ReservationRepository reservationRepository) {
        this.psReservationService = psReservationService;
        this.reviewRepository = reviewRepository;
        this.reservationRepository = reservationRepository;
    }

    /**
     * 2주치 미래 예약 및 시간 조회
     * @param psSeq
     * @return
     */
//    @GetMapping("/post/{psSeq}")
//    public ResponseEntity<?> getPostReservationList(@PathVariable Long psSeq) {
//
//        LocalDateTime current = LocalDateTime.now();
//
//        psReservationService.getPostReservationList(psSeq, current);
//
//
//        return null;
//    }


    /**
     * 운영 시간 수정 (delete & insert)
     * @param reservationReq
     * @return
     */
    @PutMapping("/edit")
    public ResponseEntity<?> editReservationTime(@RequestBody ReservationReq reservationReq) {
        return null;
    }

    /**
     * 예약 취소
     * @param reservationReq
     * @return
     */
    @PatchMapping
    public ResponseEntity<?> cancelReservation(@RequestBody ReservationReq reservationReq) {

        if(psReservationService.modifyReservation(reservationReq)) {
            return ResponseEntity.status(200).body(com.fasulting.demo.resp.ResponseBody.create(200, "success"));
        }

        return ResponseEntity.status(200).body(ResponseBody.create(200, "fail"));
    }


    /**
     * 지난 예약 조회
     * @param seq
     * @return
     */
    @GetMapping("/pre/{seq}")
    public ResponseEntity<?> getPreReservationInfo(@PathVariable int seq) {



        return null;
    }


}
