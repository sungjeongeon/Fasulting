package com.fasulting.domain.user.reservation.controller;

import com.fasulting.common.resp.ResponseBody;
import com.fasulting.common.util.LogCurrent;
import com.fasulting.domain.user.reservation.dto.reqDto.CancelReservationReqDto;
import com.fasulting.domain.user.reservation.dto.reqDto.RegReservationReqDto;
import com.fasulting.domain.user.reservation.dto.respDto.PostReservationRespDto;
import com.fasulting.domain.user.reservation.dto.respDto.PreReservationRespDto;
import com.fasulting.domain.user.reservation.dto.respDto.ReportRespDto;
import com.fasulting.domain.user.reservation.dto.respDto.ReservationTableRespDto;
import com.fasulting.domain.user.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.fasulting.common.util.LogCurrent.*;

@Slf4j
@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    /**
     * 병원 예약 가능 시간 조회
     * @param psSeq
     * @return
     */
    @GetMapping("/{psSeq}")
    public ResponseEntity<?> getReservationTable(@PathVariable Long psSeq) {
        log.info(logCurrent(getClassName(), getMethodName(), START));
        LocalDateTime current = LocalDateTime.now();

        ReservationTableRespDto resp = reservationService.getReservationTable(psSeq, current);

        if (resp != null) {
            log.info(logCurrent(getClassName(), getMethodName(), END));
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success", resp));
        }
        log.info(logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
    }

    /**
     * 예약 등록
     * @param regReservationReqDto
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<?> regReservation(@ModelAttribute RegReservationReqDto regReservationReqDto) {

        log.info(logCurrent(getClassName(), getMethodName(), START));

        if (reservationService.addReservation(regReservationReqDto)) {
            log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }
        log.info(logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
    }

    /**
     * 지난 예약 조회
     * @param userSeq
     * @return
     */
    @GetMapping("/pre/{userSeq}")
    public ResponseEntity<?> getPreReservationList(@PathVariable Long userSeq) {

        log.info(logCurrent(getClassName(), getMethodName(), START));

        List<PreReservationRespDto> resp = reservationService.getPreReservationList(userSeq);

        if (!resp.isEmpty()) {
            log.info(logCurrent(getClassName(), getMethodName(), END));
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success", resp));
        }
        log.info(logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(204).body(ResponseBody.create(204, "fail"));
    }


    /**
     * 미래예약 조회
     * @param userSeq
     * @return
     */
    @GetMapping("/post/{userSeq}")
    public ResponseEntity<?> getPostReservationList(@PathVariable Long userSeq) {

        log.info(logCurrent(getClassName(), getMethodName(), START));

        List<PostReservationRespDto> resp = reservationService.getPostReservationList(userSeq);

        if (!resp.isEmpty()) {
            log.info(logCurrent(getClassName(), getMethodName(), END));
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success", resp));
        }
        log.info(logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
    }

    /**
     * 예약 취소
     * @param cancelReservationReqDto
     * @return
     */
    @PatchMapping
    public ResponseEntity<?> cancelReservation(@RequestBody CancelReservationReqDto cancelReservationReqDto) {

        log.info(logCurrent(getClassName(), getMethodName(), START));
        if (reservationService.cancelReservation(cancelReservationReqDto)) {
            log.info(logCurrent(getClassName(), getMethodName(), END));
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }
        log.info(logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
    }

    /**
     * 상담 결과 상세 조회
     * @param userSeq
     * @param consultingSeq
     * @return
     */
    @GetMapping("/report/{userSeq}/{consultingSeq}")
    public ResponseEntity<?> getReport(@PathVariable Long userSeq, @PathVariable Long consultingSeq) {

        log.info(logCurrent(getClassName(), getMethodName(), START));
        ReportRespDto resp = reservationService.getReport(userSeq, consultingSeq);

        if (resp != null) {
            log.info(logCurrent(getClassName(), getMethodName(), END));
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success", resp));
        }
        log.info(logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));

    }

}
