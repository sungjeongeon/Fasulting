package com.fasulting.domain.ps.psConsulting.service;

import com.fasulting.common.util.Date2String;
import com.fasulting.common.util.FileManage;
import com.fasulting.common.util.LogCurrent;
import com.fasulting.domain.ps.psConsulting.dto.ResultReqDto;
import com.fasulting.entity.consulting.ConsultingEntity;
import com.fasulting.entity.consulting.ReportEntity;
import com.fasulting.entity.ps.PsEntity;
import com.fasulting.entity.reservation.ReservationEntity;
import com.fasulting.repository.consulting.ConsultingRepository;
import com.fasulting.repository.consulting.ReportRepository;
import com.fasulting.repository.ps.PsRepository;
import com.fasulting.repository.reservation.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.fasulting.common.util.FileManage.afterImgDirPath;
import static com.fasulting.common.util.LogCurrent.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class PsConsultingServiceImpl implements PsConsultingService {
    private final ReportRepository reportRepository;
    private final ConsultingRepository consultingRepository;
    private final ReservationRepository reservationRepository;
    private final PsRepository psRepository;

    /**
     * 비포 사진 다운로드
     * @param reservationSeq
     * @return
     */
    @Override
    public Map<String, String> getBeforeImg(Long reservationSeq) {
        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));
        ReservationEntity reservation = reservationRepository.findById(reservationSeq).orElseThrow(() -> {
            throw new NullPointerException();
        });
        PsEntity ps = psRepository.findById(reservation.getPs().getSeq()).orElseThrow(() -> {
            throw new NullPointerException();
        });
//        if (!CheckInfo.checkLoginInfo(ps.getSeq(), ps.getEmail(), RoleType.PS)){
//            log.error(logCurrent(getClassName(), getMethodName(), END));
//            throw new UnAuthorizedException();
//        }

        String beforeImgPath = reservation.getBeforeImgPath();
        // 날짜_이름
        int year = reservation.getReservationCal().getYear();
        int month = reservation.getReservationCal().getMonth();
        int day = reservation.getReservationCal().getDay();

        String[] str = reservation.getBeforeImgOrigin().split("\\.");

        String beforeImgName = Date2String.date2String(year, month, day) + "_" + reservation.getUser().getName() + "." + str[str.length - 1];

        Map<String, String> resp = new HashMap<>();
        resp.put("beforeImgPath", beforeImgPath);
        resp.put("beforeImgOrigin", beforeImgName);

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
        return resp;

    }

    @Override
    public ResponseEntity<String> initializeSession(Map<String, Object> params) {
        return null;
    }

    @Override
    public ResponseEntity<String> createConnection(String sessionId, Map<String, Object> params) {
        return null;
    }

    /**
     * 상담 결과(소견서) 작성
     * @param resultReq
     * @return
     */
    @Override
    public boolean writeResult(ResultReqDto resultReq) {
        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));
        ReservationEntity reservation = reservationRepository.findById(resultReq.getReservationSeq()).orElseThrow(() -> {
            throw new NullPointerException();
        });
        PsEntity ps = psRepository.findById(reservation.getPs().getSeq()).orElseThrow(() -> {
            throw new NullPointerException();
        });
//        if (!CheckInfo.checkLoginInfo(ps.getSeq(), ps.getEmail(), RoleType.PS)){
//            log.error(logCurrent(getClassName(), getMethodName(), END));
//            throw new UnAuthorizedException();
//        }

        ///////////////// consultingEntity 생성
        ConsultingEntity consulting = ConsultingEntity.builder()
                .user(reservation.getUser())
                .ps(reservation.getPs())
                .reservation(reservation)
                .build();

        consultingRepository.save(consulting);

        ///////////////// reportEntity 생성
        MultipartFile afterImgFile = resultReq.getAfterImg();

        String afterImgUrl = null;
        if (afterImgFile != null && !afterImgFile.isEmpty()) {
            // 파일 중복명 방지 uuid 생성
            UUID uuid = UUID.randomUUID();

            afterImgUrl = FileManage.uploadFile(afterImgFile, uuid, afterImgDirPath);
        }

        // consulting.reservationId로 before이미지 얻어오기
        ReportEntity report = ReportEntity.builder()
                .beforeImgPath(consulting.getReservation().getBeforeImgPath())
                .beforeImgOrigin(consulting.getReservation().getBeforeImgOrigin())
                .afterImgOrigin(afterImgFile.getOriginalFilename())
                .afterImgPath(afterImgUrl)
                .content(resultReq.getReportContent())
                .estimate(resultReq.getReportEstimate())
                .consulting(consulting)
                .build();

        reportRepository.save(report);
        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
        return true;
    }
}
