package com.fasulting.demo.ps.psReservation.service;

import com.fasulting.demo.common.reservation.repository.ReservationRepository;
import com.fasulting.demo.common.reservation.repository.ReservationSubRepository;
import com.fasulting.demo.entity.ReservationEntity;
import com.fasulting.demo.ps.psReservation.dto.respDto.ReservationRespDto;
import com.fasulting.demo.ps.psReservation.request.ReservationReq;
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

    public PsReservationServiceImpl(ReservationRepository reservationRepository,
                                    ReservationSubRepository reservationSubEntityRepository) {
        this.reservationRepository = reservationRepository;
        this.reservationSubEntityRepository = reservationSubEntityRepository;
    }

    @Transactional
    @Override
    public boolean modifyReservation(ReservationReq reservationReq) {

        ReservationEntity reservation = reservationRepository.findById(reservationReq.getReservationSeq()).get();

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

        return respList;
    }


}
