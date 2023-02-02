package com.fasulting.demo.ps.psReservation.service;

import com.fasulting.demo.common.operatingCal.repository.OperatingCalRepository;
import com.fasulting.demo.common.psOperating.repository.PsOperatingRepository;
import com.fasulting.demo.common.reservation.repository.ReservationRepository;
import com.fasulting.demo.common.reservation.repository.ReservationSubRepository;
import com.fasulting.demo.common.time.repository.TimeRepository;
import com.fasulting.demo.entity.OperatingCalEntity;
import com.fasulting.demo.entity.PsEntity;
import com.fasulting.demo.entity.PsOperatingEntity;
import com.fasulting.demo.entity.ReservationEntity;
import com.fasulting.demo.ps.ps.repository.PsRepository;
import com.fasulting.demo.ps.psReservation.dto.reqDto.ReservationReqDto;
import com.fasulting.demo.ps.psReservation.dto.respDto.PostReservationRespDto;
import com.fasulting.demo.ps.psReservation.dto.respDto.PsOperatingRespDto;
import com.fasulting.demo.ps.psReservation.dto.respDto.ReservationRespDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PsReservationServiceImpl implements PsReservationService{

    private final ReservationRepository reservationRepository;
    private final ReservationSubRepository reservationSubEntityRepository;
    private final PsOperatingRepository psOperatingRepository;
    private final PsRepository psRepository;
    private final OperatingCalRepository operatingCalRepository;
    private final TimeRepository timeRepository;

    public PsReservationServiceImpl(ReservationRepository reservationRepository,
                                    ReservationSubRepository reservationSubEntityRepository, PsOperatingRepository psOperatingRepository, PsRepository psRepository, OperatingCalRepository operatingCalRepository, TimeRepository timeRepository) {
        this.reservationRepository = reservationRepository;
        this.reservationSubEntityRepository = reservationSubEntityRepository;
        this.psOperatingRepository = psOperatingRepository;
        this.psRepository = psRepository;
        this.operatingCalRepository = operatingCalRepository;
        this.timeRepository = timeRepository;
    }

    @Transactional
    @Override
    public boolean modifyReservation(ReservationReqDto reservationReqDto) {

        ReservationEntity reservation = reservationRepository.findById(reservationReqDto.getReservationSeq()).get();

        reservation.updateDelYn();

        if("Y".equals(reservation.getDelYn())) {
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
        for(ReservationEntity reservation : reservationList) {

            ReservationRespDto respDto = ReservationRespDto.builder()
                    .reservationSeq(reservation.getSeq())
                    .reservationDate(reservation.getReservationCal().getDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                    .reservationTime(reservation.getTime().getStartHour() + ":" + reservation.getTime().getStartMin())
                    .subCategoryName(reservationSubEntityRepository.getSubCategoryByReservationSeq(reservation.getSeq()))
                    .userName(reservation.getUser().getName())
                    .build();

            respList.add(respDto);
        }


        // 운영 시간 조회
        for(int i = 0; i < 15; i++) {
            LocalDateTime date = current.plusDays(i);

            Integer year = date.getYear();
            Integer month = date.getMonth().getValue();
            Integer day = date.getDayOfMonth();

            // select seq from cal_operating where yaer = :year and month = :month and day := day
            OperatingCalEntity operatingCal = operatingCalRepository.findByYearAndMonthAndDay(year, month, day);

            // select time_seq from ps_operating where ps_seq = :psSeq and cal_seq = :calSeq
            List<PsOperatingEntity> psOperatingList = psOperatingRepository.findByPsAndOperatingCal(ps, operatingCal);

            List<Integer> timeList = new ArrayList<>();
            for(PsOperatingEntity psOperating : psOperatingList) {
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


}
