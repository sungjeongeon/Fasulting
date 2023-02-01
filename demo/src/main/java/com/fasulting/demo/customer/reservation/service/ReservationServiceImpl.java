package com.fasulting.demo.customer.reservation.service;

import com.fasulting.demo.common.psOperating.dto.respDto.PsOperatingRespDto;
import com.fasulting.demo.common.psOperating.repository.PsOperatingRepository;
import com.fasulting.demo.customer.main.dto.respDto.MainCategoryRespDto;
import com.fasulting.demo.customer.main.dto.respDto.SubCategoryRespDto;
import com.fasulting.demo.customer.reservation.dto.respDto.ReservationTableRespDto;
import com.fasulting.demo.entity.MainCategoryEntity;
import com.fasulting.demo.entity.PsOperatingEntity;
import com.fasulting.demo.entity.SubCategoryEntity;
import com.fasulting.demo.ps.ps.repository.PsMainRepository;
import com.fasulting.demo.ps.ps.repository.PsMainSubRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Slf4j
@Service
public class ReservationServiceImpl implements ReservationService {

    private final PsOperatingRepository psOperatingRepository;
    private final PsMainRepository psMainRepository;
    private final PsMainSubRepository psMainSubRepository;

    @Autowired
    public ReservationServiceImpl(PsOperatingRepository psOperatingRepository, PsMainRepository psMainRepository,
                                  PsMainSubRepository psMainSubRepository) {
        this.psOperatingRepository = psOperatingRepository;
        this.psMainRepository = psMainRepository;
        this.psMainSubRepository = psMainSubRepository;

    }

    @Override
    public ReservationTableRespDto getReservationTable(Long psSeq, LocalDateTime current) {

        // 예약 가능 시간 테이블 조회, 오늘 포함 일주일 (총 7일)
        LocalDateTime post = current.plusDays(6);

        Map<String, PsOperatingRespDto> map = new TreeMap<>();

        List<PsOperatingEntity> psOperatingList = psOperatingRepository.getByPsSeqAndCurrent(psSeq, current, post);

        for (PsOperatingEntity po : psOperatingList) {

            Integer year = po.getOperatingCal().getYear();
            Integer month = po.getOperatingCal().getMonth();
            Integer day = po.getOperatingCal().getDay();


            String key = year + "-" + String.format("%02d", month) + "-" + String.format("%02d", day);

            if (map.get(key) == null) {
                List<Integer> timeList = new ArrayList<>();
                timeList.add(po.getTime().getNum());

                PsOperatingRespDto value = PsOperatingRespDto.builder()
                        .year(year)
                        .month(month)
                        .day(day)
                        .dayOfWeek(po.getOperatingCal().getDayOfWeek())
                        .time(timeList)
                        .build();

                map.put(key, value);
            } else {
                PsOperatingRespDto value = map.get(key);
                value.addTime(po.getTime().getNum());

                map.put(key, value);
            }
        }

        List<PsOperatingRespDto> poList = new ArrayList<>();
        for (Map.Entry<String, PsOperatingRespDto> entrySet : map.entrySet()) {
//            log.info(entrySet.getKey() + " : " + entrySet.getValue());
            poList.add(entrySet.getValue());
        }

        // 병원 메인 카테고리 리스트
        List<MainCategoryRespDto> mainList = new ArrayList<>();

        List<MainCategoryEntity> mainEntityList = psMainRepository.getMainByPsSeq(psSeq);

        for (MainCategoryEntity main : mainEntityList) {
            MainCategoryRespDto mainDto = MainCategoryRespDto.builder()
                    .mainSeq(main.getSeq())
                    .mainName(main.getName())
                    .build();

            mainList.add(mainDto);
        }

        // 병원 서브 카테고리 리스트
        List<SubCategoryRespDto> subList = new ArrayList<>();

        List<SubCategoryEntity> subEntityList = psMainSubRepository.getSubByPsSeq(psSeq);

        for (SubCategoryEntity sub : subEntityList) {
            SubCategoryRespDto subDto = SubCategoryRespDto.builder()
                    .subSeq(sub.getSeq())
                    .subName(sub.getName())
                    .build();

            subList.add(subDto);
        }

        // resp 담기
        ReservationTableRespDto respList = ReservationTableRespDto.builder()
                .operatingTime(poList)
                .mainCategory(mainList)
                .subCategory(subList)
                .build();

        return respList;
    }
}
