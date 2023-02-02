package com.fasulting.demo.ps.psReservation.service;

import com.fasulting.demo.common.operatingCal.repository.OperatingCalRepository;
import com.fasulting.demo.common.psOperating.repository.PsOperatingRepository;
import com.fasulting.demo.common.reservation.repository.ReservationRepository;
import com.fasulting.demo.common.reservation.repository.ReservationSubRepository;
import com.fasulting.demo.common.time.repository.TimeRepository;
import com.fasulting.demo.entity.ReservationEntity;
import com.fasulting.demo.ps.ps.repository.PsRepository;
import com.fasulting.demo.ps.psReservation.dto.reqDto.ReservationReqDto;
import com.fasulting.demo.ps.psReservation.dto.respDto.ReservationRespDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
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

    @Override
    public List<ReservationRespDto> getPostReservationList(Long psSeq, LocalDateTime current) {

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

        // 현 날짜 기준 2 주치 운영 시간
        // year
        // month
        // day
        // datOfWeek



        return respList;
    }


}
