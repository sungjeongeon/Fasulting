package com.fasulting.domain.ps.psReservation.controller;


import com.fasulting.common.resp.ResponseBody;
import com.fasulting.common.util.LogCurrent;
import com.fasulting.domain.ps.psReservation.dto.reqDto.ReservationReqDto;
import com.fasulting.domain.ps.psReservation.dto.respDto.PreDetailRespDto;
import com.fasulting.domain.ps.psReservation.dto.respDto.PreReservationRespDto;
import com.fasulting.domain.ps.psReservation.dto.respDto.PsPostRespDto;
import com.fasulting.domain.ps.psReservation.service.PsReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.fasulting.common.util.LogCurrent.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/ps-reservation")
public class PsReservationController {

    private final PsReservationService psReservationService;

    /**
     * 미래 예약 및 시간(앞으로 2주) 조회
     * @param psSeq
     * @return
     */
    @GetMapping("/post/{psSeq}")
    public ResponseEntity<?> getPostReservationList(@PathVariable Long psSeq) {

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));
        PsPostRespDto respDto = psReservationService.getPostReservationList(psSeq, LocalDateTime.now());

        if(respDto != null){
            log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success", respDto));
        }
        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(204).body(ResponseBody.create(204, "fail"));
    }

    /**
     * 예약 취소
     * @param reservationReqDto
     * @return
     */
    @PatchMapping
    public ResponseEntity<?> cancelReservation(@RequestBody ReservationReqDto reservationReqDto) {
        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));

        if(psReservationService.cancelReservation(reservationReqDto)) {
            log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }
        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
    }


    /**
     * 지난 예약 조회
     * @param psSeq
     * @return
     */
    @GetMapping("/pre/{psSeq}")
    public ResponseEntity<?> getPreReservationInfo(@PathVariable Long psSeq) {

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));
        List<PreReservationRespDto> respDto = psReservationService.getPreReservationList(psSeq, LocalDateTime.now());

        if(!respDto.isEmpty()){
            log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success", respDto));
        }
        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(204).body(ResponseBody.create(204, "fail"));

    }


    /**
     * 상담 결과 조회
     * @param consultingSeq
     * @return
     */
    @GetMapping("/pre/detail/{consultingSeq}")
    public ResponseEntity<?> getPreDetail(@PathVariable Long consultingSeq) {

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));
        PreDetailRespDto respDto = psReservationService.getPreDetail(consultingSeq);

        if(respDto != null){
            log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success", respDto));
        }
        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(204).body(ResponseBody.create(204, "fail"));

    }
}
