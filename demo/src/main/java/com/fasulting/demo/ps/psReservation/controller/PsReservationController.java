package com.fasulting.demo.ps.psReservation.controller;


import com.fasulting.demo.common.reservation.repository.ReservationRepository;
import com.fasulting.demo.common.review.repository.ReviewRepository;
import com.fasulting.demo.ps.psReservation.dto.reqDto.ReservationReqDto;
import com.fasulting.demo.ps.psReservation.dto.respDto.PreDetailRespDto;
import com.fasulting.demo.ps.psReservation.dto.respDto.PreReservationRespDto;
import com.fasulting.demo.ps.psReservation.dto.respDto.PsPostRespDto;
import com.fasulting.demo.ps.psReservation.service.PsReservationService;
import com.fasulting.demo.resp.ResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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
    @GetMapping("/post/{psSeq}")
    public ResponseEntity<?> getPostReservationList(@PathVariable Long psSeq) {

        PsPostRespDto respDto = psReservationService.getPostReservationList(psSeq, LocalDateTime.now());

        if(respDto != null){
            return ResponseEntity.status(200).body(com.fasulting.demo.resp.ResponseBody.create(200, "success", respDto));
        }

        return ResponseEntity.status(204).body(com.fasulting.demo.resp.ResponseBody.create(204, "fail"));
    }

    /**
     * 예약 취소
     * @param reservationReqDto
     * @return
     */
    @PatchMapping
    public ResponseEntity<?> cancelReservation(@RequestBody ReservationReqDto reservationReqDto) {

        if(psReservationService.cancelReservation(reservationReqDto)) {
            return ResponseEntity.status(200).body(com.fasulting.demo.resp.ResponseBody.create(200, "success"));
        }

        return ResponseEntity.status(200).body(ResponseBody.create(200, "fail"));
    }


    /**
     * 지난 예약 조회
     * @param psSeq
     * @return
     */
    @GetMapping("/pre/{psSeq}")
    public ResponseEntity<?> getPreReservationInfo(@PathVariable Long psSeq) {

        List<PreReservationRespDto> respDto = psReservationService.getPreReservationList(psSeq, LocalDateTime.now());

        if(respDto != null){
            return ResponseEntity.status(200).body(com.fasulting.demo.resp.ResponseBody.create(200, "success", respDto));
        }

        return ResponseEntity.status(204).body(com.fasulting.demo.resp.ResponseBody.create(204, "fail"));

    }


    /**
     * 상담 결과 상세 조회
     */
    @GetMapping("/pre/detail/{consultingSeq}")
    public ResponseEntity<?> getPreDetail(@PathVariable Long consultingSeq) {

        PreDetailRespDto respDto = psReservationService.getPreDetail(consultingSeq);

        if(respDto != null){
            return ResponseEntity.status(200).body(com.fasulting.demo.resp.ResponseBody.create(200, "success", respDto));
        }

        return ResponseEntity.status(204).body(com.fasulting.demo.resp.ResponseBody.create(204, "fail"));

    }


}
