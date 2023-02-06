package com.fasulting.domain.ps.psConsulting.service;

import com.fasulting.entity.reservation.ReservationEntity;
import com.fasulting.repository.reservation.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class PsConsultingServiceImpl implements PsConsultingService {

    private final ReservationRepository reservationRepository;

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
