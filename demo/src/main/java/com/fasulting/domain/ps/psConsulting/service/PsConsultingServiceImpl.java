package com.fasulting.domain.ps.psConsulting.service;

import com.fasulting.entity.reservation.ReservationEntity;
import com.fasulting.repository.reservation.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PsConsultingServiceImpl implements PsConsultingService {

    private final ReservationRepository reservationRepository;

    public PsConsultingServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Map<String, String> getBeforeImg(Long reservationSeq) {

        if(reservationRepository.findById(reservationSeq).isPresent()){
            ReservationEntity reservation = reservationRepository.findById(reservationSeq).get();

            String beforeImgPath = reservation.getBeforeImgPath();
            String beforeImgOrigin = reservation.getBeforeImgOrigin();

            Map<String, String> resp = new HashMap<>();
            resp.put("beforeImgPath", beforeImgPath);
            resp.put("beforeImgOrigin", beforeImgOrigin);

            return  resp;
        }


        return null;
    }
}
