package com.fasulting.demo.ps.psReservation.service;

import com.fasulting.demo.common.consulting.repository.ConsultingRepository;
import com.fasulting.demo.common.operatingCal.repository.OperatingCalRepository;
import com.fasulting.demo.common.psOperating.repository.PsOperatingRepository;
import com.fasulting.demo.common.report.repository.ReportEntityRepository;
import com.fasulting.demo.common.reservation.repository.ReservationRepository;
import com.fasulting.demo.common.reservation.repository.ReservationSubRepository;
import com.fasulting.demo.common.time.repository.TimeRepository;
import com.fasulting.demo.entity.*;
import com.fasulting.demo.ps.ps.repository.PsRepository;
import com.fasulting.demo.ps.ps.repository.SubCategoryRepository;
import com.fasulting.demo.ps.psReservation.dto.reqDto.ReservationReqDto;
import com.fasulting.demo.ps.psReservation.dto.respDto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PsReservationServiceImpl implements PsReservationService {
    private final SubCategoryRepository subCategoryRepository;
    private final ReportEntityRepository reportEntityRepository;
    private final ConsultingRepository consultingRepository;

    private final ReservationRepository reservationRepository;
    private final ReservationSubRepository reservationSubEntityRepository;
    private final PsOperatingRepository psOperatingRepository;
    private final PsRepository psRepository;
    private final OperatingCalRepository operatingCalRepository;
    private final TimeRepository timeRepository;

    public PsReservationServiceImpl(ReservationRepository reservationRepository,
                                    ReservationSubRepository reservationSubEntityRepository, PsOperatingRepository psOperatingRepository, PsRepository psRepository, OperatingCalRepository operatingCalRepository, TimeRepository timeRepository,
                                    ConsultingRepository consultingRepository,
                                    ReportEntityRepository reportEntityRepository,
                                    SubCategoryRepository subCategoryRepository) {
        this.reservationRepository = reservationRepository;
        this.reservationSubEntityRepository = reservationSubEntityRepository;
        this.psOperatingRepository = psOperatingRepository;
        this.psRepository = psRepository;
        this.operatingCalRepository = operatingCalRepository;
        this.timeRepository = timeRepository;
        this.consultingRepository = consultingRepository;
        this.reportEntityRepository = reportEntityRepository;
        this.subCategoryRepository = subCategoryRepository;
    }

    @Transactional
    @Override
    public boolean cancelReservation(ReservationReqDto reservationReqDto) {

        log.info("cancelReservation - call");

        Long rSeq = reservationReqDto.getReservationSeq();
        Long pSeq = reservationReqDto.getPsSeq();

        // 예약 delYn >> Y로 변경
        if (reservationRepository.findById(rSeq).isPresent() && reservationRepository.findById(rSeq).get().getPs().getSeq() == pSeq) {

            ReservationEntity r = reservationRepository.findById(rSeq).get();
            r.updateDelYn();

            reservationRepository.save(r);

            // 예약 취소하고 해당 시간을 해당 병원 운영 시간에 추가

            // 예약에서 병원 구하기
            // 예약에서 date 구하기
            // 예약에서 시간 구하기
            // 병원 date(operating_cal) 시간(time) 으로 운영 시간(ps_operating) 추가

            PsEntity ps = r.getPs();

            OperatingCalEntity op = operatingCalRepository.findByYearAndMonthAndDay(
                            r.getReservationCal().getYear(),
                            r.getReservationCal().getMonth(),
                            r.getReservationCal().getDay())
                    .get();

            TimeEntity time = timeRepository.findById(r.getTime().getSeq()).get();

            PsOperatingEntity psOperating = PsOperatingEntity.builder()
                    .ps(ps)
                    .operatingCal(op)
                    .time(time)
                    .build();

            psOperatingRepository.save(psOperating);

            return true;
        }

        return false;

    }

    @Transactional
    @Override
    public PostReservationRespDto getPostReservationList(Long psSeq, LocalDateTime current) {

        List<PsOperatingRespDto> psOperatingRespList = new ArrayList<>();
        PsEntity ps = psRepository.findById(psSeq).get();
        List<ReservationEntity> reservationList = reservationRepository.getPostByPs(psSeq, current);

        List<ReservationRespDto> respList = new ArrayList<>();

        // 미래 예약 조회
        for (ReservationEntity reservation : reservationList) {

            ReservationRespDto respDto = ReservationRespDto.builder()
                    .reservationSeq(reservation.getSeq())
                    .reservationDate(reservation.getReservationCal().getDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                    .reservationTime(reservation.getTime().getStartHour() + ":" + reservation.getTime().getStartMin())
                    .subCategoryName(reservationSubEntityRepository.getSubCategoryNameByReservationSeq(reservation.getSeq()))
                    .userName(reservation.getUser().getName())
                    .build();

            respList.add(respDto);
        }

        /////////////////////////////////////////////////////////////////////////// 커먼
        // 운영 시간 조회
        for (int i = 0; i < 15; i++) {
            LocalDateTime date = current.plusDays(i);

            Integer year = date.getYear();
            Integer month = date.getMonth().getValue();
            Integer day = date.getDayOfMonth();

            // select seq from cal_operating where yaer = :year and month = :month and day := day
            OperatingCalEntity operatingCal = operatingCalRepository.findByYearAndMonthAndDay(year, month, day).get();

            // select time_seq from ps_operating where ps_seq = :psSeq and cal_seq = :calSeq
            List<PsOperatingEntity> psOperatingList = psOperatingRepository.findByPsAndOperatingCal(ps, operatingCal);

            List<Integer> timeList = new ArrayList<>();
            for (PsOperatingEntity psOperating : psOperatingList) {
                timeList.add(psOperating.getTime().getNum());
            }

            PsOperatingRespDto psOperatingRespDto = PsOperatingRespDto.builder()
                    .year(year)
                    .month(month)
                    .day(day)
                    .dayOfWeek(operatingCal.getDayOfWeek())
                    .time(timeList)
                    .build();

            psOperatingRespList.add(psOperatingRespDto);
        }

        PostReservationRespDto postReservationRespDto = PostReservationRespDto.builder()
                .reservation(respList)
                .operatingTime(psOperatingRespList)
                .build();

        return postReservationRespDto;
    }

    @Transactional
    @Override
    public List<PreReservationRespDto> getPreReservationList(Long psSeq, LocalDateTime current) {

        String[] days = {"", "일", "월", "화", "수", "목", "금", "토"};

        ////////////////////////////////////////////////// 지난 예약 가져오는 쿼리
        List<ReservationEntity> preReservationList = reservationRepository.getPreByPs(psSeq, current);


        List<PreReservationRespDto> preReservationRespDtoList = new ArrayList<>();

        for (ReservationEntity reservation : preReservationList) {

            String userName = reservation.getUser().getName();
            ConsultingEntity consulting = consultingRepository.findByResrvationSeq(reservation.getSeq());
            ReportEntity report = reportEntityRepository.findByConsulting(consulting);


            String estimate = report.getEstimate();
            List<String> subCategoryName = reservationSubEntityRepository.getSubCategoryNameByReservationSeq(reservation.getSeq());

            int year = reservation.getReservationCal().getYear();
            int month = reservation.getReservationCal().getMonth();
            int day = reservation.getReservationCal().getDay();
            int dayOfWeek = reservation.getReservationCal().getDayOfWeek();

            int startHour = reservation.getTime().getStartHour();
            int startMin = reservation.getTime().getStartMin();

            String date = year + "." + month + "." + day + "(" + days[dayOfWeek] + ")" + startHour + "시 " + startMin + "분";


            PreReservationRespDto preReservation = PreReservationRespDto.builder()
                    .conultingSeq(consulting.getSeq())
                    .date(date)
                    .estimate(estimate)
                    .username(userName)
                    .subCategoryName(subCategoryName)
                    .build();

            preReservationRespDtoList.add(preReservation);

        }
        log.info(preReservationRespDtoList.toString());

        return preReservationRespDtoList;
    }

    @Transactional
    @Override
    public PreDetailRespDto getPreDetail(Long consultingSeq) {
        ConsultingEntity consulting = consultingRepository.findById(consultingSeq).get();
        UserEntity user = consulting.getUser();
        ReportEntity report = reportEntityRepository.findByConsulting(consulting);
        ReservationCalEntity reservationCal = consulting.getReservation().getReservationCal();

        PreDetailRespDto preDetail = PreDetailRespDto.builder()
                .userName(user.getName())
                .userNumber(user.getNumber())
                .userBirth(user.getBirth())
                .date(reservationCal.getDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                .subCategoryName(reservationSubEntityRepository.getSubCategoryNameByReservationSeq(consulting.getReservation().getSeq()))
                .content(report.getContent())
                .estimate(report.getEstimate())
                .build();

        return preDetail;
    }


}

