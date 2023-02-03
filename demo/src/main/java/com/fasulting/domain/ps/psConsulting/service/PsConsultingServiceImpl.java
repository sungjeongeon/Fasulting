package com.fasulting.domain.ps.psConsulting.service;

import com.fasulting.entity.reservation.ReservationEntity;
import com.fasulting.repository.reservation.ReservationRepository;
import org.springframework.stereotype.Service;

@Service
public class PsConsultingServiceImpl implements PsConsultingService {

    private final ReservationRepository reservationRepository;

    public PsConsultingServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public String getBeforeImg(Long reservationSeq) {

        if(reservationRepository.findById(reservationSeq).isPresent()){
            ReservationEntity reservation = reservationRepository.findById(reservationSeq).get();

            String beforeImgPath = reservation.getBeforeImgPath();

            return  beforeImgPath;
        }


        return null;
    }
}
