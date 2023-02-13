package com.fasulting.domain.ps.psConsulting.service;

import com.fasulting.common.util.Date2String;
import com.fasulting.common.util.FileManage;
import com.fasulting.domain.ps.psConsulting.dto.ResultReqDto;
import com.fasulting.entity.consulting.ConsultingEntity;
import com.fasulting.entity.consulting.ReportEntity;
import com.fasulting.entity.reservation.ReservationEntity;
import com.fasulting.repository.consulting.ConsultingRepository;
import com.fasulting.repository.consulting.ReportRepository;
import com.fasulting.repository.reservation.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.fasulting.common.util.FileManage.afterImgDirPath;
import static com.fasulting.common.util.FileManage.domain;

@Service
@Slf4j
@RequiredArgsConstructor
public class PsConsultingServiceImpl implements PsConsultingService {
    private final ReportRepository reportRepository;
    private final ConsultingRepository consultingRepository;
    private final ReservationRepository reservationRepository;


    @Override
    public Map<String, String> getBeforeImg(Long reservationSeq) {
        log.info("getBeforeImg Service - Call");
        ReservationEntity reservation = reservationRepository.findById(reservationSeq).orElseThrow(() -> {
            throw new NullPointerException();
        });

        String beforeImgPath = reservation.getBeforeImgPath();
        // 날짜_이름
        int year = reservation.getReservationCal().getYear();
        int month = reservation.getReservationCal().getMonth();
        int day = reservation.getReservationCal().getDay();
        int startHour = reservation.getTime().getStartHour();
        int startMin = reservation.getTime().getStartMin();

        String[] str = reservation.getBeforeImgOrigin().split("\\.");

        String beforeImgName = Date2String.date2String(year, month, day, startHour, startMin) + "_" + reservation.getUser().getName() + "." + str[str.length - 1];

        Map<String, String> resp = new HashMap<>();
        resp.put("beforeImgPath", beforeImgPath);
        resp.put("beforeImgOrigin", beforeImgName);

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

    @Override
    public boolean writeResult(ResultReqDto resultReq) {

        ReservationEntity reservation = reservationRepository.findById(resultReq.getReservationSeq()).orElseThrow(() -> {
            throw new NullPointerException();
        });

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

        return true;
    }
}
