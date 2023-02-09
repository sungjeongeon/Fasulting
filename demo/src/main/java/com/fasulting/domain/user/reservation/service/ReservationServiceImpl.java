package com.fasulting.domain.user.reservation.service;

import com.fasulting.common.RoleType;
import com.fasulting.common.dto.respDto.MainCategoryRespDto;
import com.fasulting.common.dto.respDto.PsOperatingRespDto;
import com.fasulting.common.dto.respDto.SubCategoryRespDto;
import com.fasulting.common.util.Date2String;
import com.fasulting.common.util.FileManage;
import com.fasulting.domain.user.reservation.dto.reqDto.CancelReservationReqDto;
import com.fasulting.domain.user.reservation.dto.reqDto.RegReservationReqDto;
import com.fasulting.domain.user.reservation.dto.respDto.*;
import com.fasulting.entity.calendar.OperatingCalEntity;
import com.fasulting.entity.calendar.ReservationCalEntity;
import com.fasulting.entity.calendar.TimeEntity;
import com.fasulting.entity.category.MainCategoryEntity;
import com.fasulting.entity.category.SubCategoryEntity;
import com.fasulting.entity.compositeId.PsOperatingId;
import com.fasulting.entity.consulting.ConsultingEntity;
import com.fasulting.entity.consulting.ReportEntity;
import com.fasulting.entity.ps.PsDefaultEntity;
import com.fasulting.entity.ps.PsEntity;
import com.fasulting.entity.ps.PsOperatingEntity;
import com.fasulting.entity.reservation.ReservationEntity;
import com.fasulting.entity.reservation.ReservationSubEntity;
import com.fasulting.entity.user.UserEntity;
import com.fasulting.repository.calendar.OperatingCalRepository;
import com.fasulting.repository.calendar.ReservationCalRepository;
import com.fasulting.repository.calendar.TimeRepository;
import com.fasulting.repository.category.MainCategoryRepository;
import com.fasulting.repository.category.SubCategoryRepository;
import com.fasulting.repository.consulting.ConsultingRepository;
import com.fasulting.repository.consulting.ReportRepository;
import com.fasulting.repository.ps.*;
import com.fasulting.repository.reservation.ReservationRepository;
import com.fasulting.repository.reservation.ReservationSubRepository;
import com.fasulting.repository.review.ReviewRepository;
import com.fasulting.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.fasulting.common.util.FileManage.beforeImgDirPath;
import static com.fasulting.common.util.FileManage.domain;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final PsOperatingRepository psOperatingRepository;
    private final PsMainRepository psMainRepository;
    private final PsMainSubRepository psMainSubRepository;
    private final OperatingCalRepository operatingCalRepository;
    private final TimeRepository timeRepository;
    private final PsRepository psRepository;
    private final ReservationCalRepository reservationCalRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final ConsultingRepository consultingRepository;
    private final ReportRepository reportRepository;
    private final ReviewRepository reviewRepository;
    private final ReservationSubRepository reservationSubRepository;
    private final PsDefaultRepository psDefaultRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final MainCategoryRepository mainCategoryRepository;


    /**
     * 병원 예약 가능 테이블 조회
     *
     * @param psSeq
     * @param current
     * @return
     */
    @Override
    public ReservationTableRespDto getReservationTable(Long psSeq, LocalDateTime current) {

        log.info("getReservationTable Service Call");

        PsEntity ps = psRepository.findById(psSeq).orElseThrow( () -> new NullPointerException());

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
            log.info(entrySet.getKey() + " : " + entrySet.getValue());
            poList.add(entrySet.getValue());
        }

        // 전체 메인 카테고리 리스트
        List<ReservationMainRespDto> mainList = new ArrayList<>();

        List<MainCategoryEntity> mainEntityList = mainCategoryRepository.findAll();

        for (MainCategoryEntity main : mainEntityList) {

            List<SubCategoryEntity> subList = psMainSubRepository.findAllByMainCategoryAndPs(main.getSeq(), ps.getSeq());
            List<SubCategoryRespDto> sList = new ArrayList<>();

            for(SubCategoryEntity sub : subList){
                SubCategoryRespDto s = SubCategoryRespDto.builder()
                        .mainSeq(main.getSeq())
                        .subSeq(sub.getSeq())
                        .subName(sub.getName())
                        .build();

                sList.add(s);
            }

            ReservationMainRespDto mainDto = ReservationMainRespDto.builder()
                    .mainSeq(main.getSeq())
                    .mainName(main.getName())
                    .subCategoryList(sList)
                    .build();

            mainList.add(mainDto);
        }

        // resp 담기
        ReservationTableRespDto respList = ReservationTableRespDto.builder()
                .operatingTimeList(poList)
                .mainCategoryList(mainList)
                .build();

        return respList;
    }

    /**
     * 예약 등록
     *
     * @param regReservationReqDto
     * @return true or false
     */
    @Override
    public boolean addReservation(RegReservationReqDto regReservationReqDto) {

        log.info("addReservation Service Call");

        int year = regReservationReqDto.getYear();
        int month = regReservationReqDto.getMonth();
        int day = regReservationReqDto.getDay();
        int timeNum = regReservationReqDto.getTime();

        log.info(year + "," + month + "," + day);


        ///// 병원 운영 시간(operating)에서 해당 시간 유효한가 확인 ////

        // yyyy.mm.dd 로 operating_cal 구하기
        // time(num)으로 time 구하기
        // 위 두개로 PsOperating 구하기


        // operating_cal 구하기
        log.info("operating_cal 구하기");
        if (!operatingCalRepository.findByYearAndMonthAndDay(year, month, day).isPresent()) {
            return false;
        }
        OperatingCalEntity oc = operatingCalRepository.findByYearAndMonthAndDay(year, month, day).orElseThrow(() -> {
            throw new NullPointerException();
        });

        log.info(oc.toString());

        // time 구하기
        log.info("time 구하기");
        if (!timeRepository.findByNum(timeNum).isPresent()) {
            return false;
        }
        TimeEntity t = timeRepository.findByNum(timeNum).orElseThrow(() -> {
            throw new NullPointerException();
        });

        PsOperatingId poId = PsOperatingId.builder()
                .ps(regReservationReqDto.getPsSeq())
                .operatingCal(oc.getSeq())
                .time(t.getSeq())
                .build();

        log.info(regReservationReqDto.getPsSeq() + " " + oc.getSeq() + " " + t.getSeq());

        if (!psOperatingRepository.findById(poId).isPresent()) {
            return false;
        }
        PsOperatingEntity po = psOperatingRepository.findById(poId).orElseThrow(() -> {
            throw new NullPointerException();
        });

        log.info(" 예약 등록");
        if (!reservationCalRepository.findByYearAndMonthAndDay(year, month, day).isPresent()) {
            return false;
        }

        ReservationCalEntity rc = reservationCalRepository.findByYearAndMonthAndDay(year, month, day).orElseThrow(() -> {
            throw new NullPointerException();
        });
        PsEntity ps = psRepository.findById(regReservationReqDto.getPsSeq()).orElseThrow(() -> {
            throw new NullPointerException();
        });
        UserEntity user = userRepository.findById(regReservationReqDto.getUserSeq()).orElseThrow(() -> {
            throw new NullPointerException();
        });

        MultipartFile beforeImgFile = regReservationReqDto.getBeforeImg();

        String beforeImgPath = null;
        if (beforeImgFile != null && !beforeImgFile.isEmpty()) {
            // 파일 중복명 방지 uuid 생성
            UUID uuid = UUID.randomUUID();


            beforeImgPath = FileManage.uploadFile(beforeImgFile, uuid, beforeImgDirPath);

        }

        ReservationEntity reservation = ReservationEntity.builder()
                .reservationCal(rc)
                .time(t)
                .ps(ps)
                .user(user)
                .beforeImgPath(beforeImgPath)
                .beforeImgOrigin(beforeImgFile.getOriginalFilename())
                .build();

        reservationRepository.save(reservation);

        log.info(" 예약 - 서브 카테고리 매핑");
        // 예약 - 서브 카테고리 매핑
        List<Long> subSeqList = regReservationReqDto.getSubCategory();

        for (Long subSeq : subSeqList) {
            SubCategoryEntity sub = subCategoryRepository.findById(subSeq).orElseThrow(() -> {
                throw new NullPointerException();
            });

            ReservationSubEntity rs = ReservationSubEntity.builder()
                    .reservation(reservation)
                    .subCategory(sub)
                    .build();

            reservationSubRepository.save(rs);
        }

        //// 병원 운영 시간(operating)에서 해당 시간 삭제 ////
        psOperatingRepository.delete(po);


        return true;
    }

    /**
     * 과거 예약(상담 내역) 조회
     * @param userSeq
     * @return
     */
    @Override
    public List<PreReservationRespDto> getPreReservationList(Long userSeq) {

        log.info("getPreReservationList Service Call");

        List<PreReservationRespDto> respList = new ArrayList<>();

        List<ConsultingEntity> cList = consultingRepository.getAllByUserSeq(userRepository.findById(userSeq).orElseThrow(() -> {
            throw new NullPointerException();
        }).getSeq());

//        log.info(cList.toString());

        for (ConsultingEntity c : cList) {

            log.info(c.toString());

            String estimate = "";
            boolean isReported = false;


            if(reportRepository.findByConsulting(c).isPresent()) {
                estimate = reportRepository.findByConsulting(c).get().getEstimate();
                isReported = true;
            }

            PreReservationRespDto respDto = PreReservationRespDto.builder()
                    .consultingSeq(c.getSeq())
                    .psName(c.getPs().getName())
                    .estimate(estimate)
                    .subCategoryName(reservationSubRepository.getSubCategoryNameByReservationSeq(c.getReservation().getSeq()))
                    .date(Date2String.date2String(c.getReservation().getReservationCal().getYear(),
                            c.getReservation().getReservationCal().getMonth(),
                            c.getReservation().getReservationCal().getDay(),
                            c.getReservation().getReservationCal().getDayOfWeek(),
                            c.getReservation().getTime().getStartHour(),
                            c.getReservation().getTime().getStartMin()))
                    .isReviewed(reviewRepository.findByConsulting(c).isPresent())
                    .isReported(isReported)
                    .build();

            respList.add(respDto);
        }

        return respList;
    }

    /**
     * 미래 예약 조회
     *
     * @param userSeq
     * @return
     */
    @Override
    public List<PostReservationRespDto> getPostReservationList(Long userSeq) {

        log.info("getPostReservationList Service Call");

        List<PostReservationRespDto> respList = new ArrayList<>();

        String current = LocalDateTime.now().minusMinutes(30).format(DateTimeFormatter.ofPattern("yyyyMMddHHss"));

        List<ReservationEntity> rList = reservationRepository.getPostByUser(userSeq, current);

        for (ReservationEntity r : rList) {

            if ("N".equals(r.getDelYn()) && !consultingRepository.findByReservation(r).isPresent()) {

                PostReservationRespDto respDto = PostReservationRespDto.builder()
                        .reservationSeq(r.getSeq())
                        .userSeq(r.getUser().getSeq())
                        .psSeq(r.getPs().getSeq())
                        .psName(r.getPs().getName())
                        .year(r.getReservationCal().getYear())
                        .month(r.getReservationCal().getMonth())
                        .day(r.getReservationCal().getDay())
                        .dayOfWeek(r.getReservationCal().getDayOfWeek())
                        .hour(r.getTime().getStartHour())
                        .minute(r.getTime().getStartMin())
                        .subCategoryName(reservationSubRepository.getSubCategoryNameByReservationSeq(r.getSeq()))
                        .build();

                respList.add(respDto);
            }
        }

        return respList;
    }

    /**
     * 예약 취소
     *
     * @param cancelReservationReqDto
     * @return
     */
    @Transactional
    @Override
    public boolean cancelReservation(CancelReservationReqDto cancelReservationReqDto) {

        log.info("cancelReservation - call");

        Long rSeq = cancelReservationReqDto.getReservationSeq();
        Long uSeq = cancelReservationReqDto.getUserSeq();

        // 예약 delYn >> Y로 변경
        if (reservationRepository.findById(rSeq).isPresent() && reservationRepository.findById(rSeq).orElseThrow(() -> {
            throw new NullPointerException();
        }).getUser().getSeq() == uSeq) {

            ReservationEntity r = reservationRepository.findById(rSeq).orElseThrow(() -> {
                throw new NullPointerException();
            });
            r.updateByCancel(RoleType.USER + "_" + uSeq, LocalDateTime.now());

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

            TimeEntity time = timeRepository.findById(r.getTime().getSeq()).orElseThrow(() -> {
                throw new NullPointerException();
            });

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

    @Override
    public ReportRespDto getReport(Long userSeq, Long consultingSeq) {

        log.info("getReport - call");

        if (consultingRepository.findById(consultingSeq).isPresent()) {

            ConsultingEntity c = consultingRepository.findById(consultingSeq).orElseThrow(() -> {
                throw new NullPointerException();
            });

            if (c.getUser().getSeq() != consultingSeq) {
                return null;
            }

            // 병원
            PsEntity ps = consultingRepository.findById(consultingSeq).orElseThrow(() -> {
                throw new NullPointerException();
            }).getPs();

            // 병원 운영시간
            List<PsDefaultEntity> psDefaultList = psDefaultRepository.findAllByPsSeq(ps.getSeq());

            Map<Integer, List<Integer>> map = new HashMap<>();

            for (int i = 1; i <= 7; i++) {
                map.put(i, new ArrayList<>()); // 1: 일요일 ~ 7 : 토요일
            }

            for (PsDefaultEntity psDefault : psDefaultList) {
                int dayOfWeek = psDefault.getDefaultCal().getDayOfWeek();
                int time = psDefault.getTime().getNum();

                List<Integer> value = map.get(dayOfWeek);

                value.add(time);
                map.put(dayOfWeek, value);

            }

            // 소견서 내용
            ReportEntity report = reportRepository.findByConsulting(c).orElseThrow(() -> {
                throw new NullPointerException();
            });

            ReportRespDto respDto = ReportRespDto.builder()
                    .psSeq(ps.getSeq())
                    .psAddress(ps.getAddress())
                    .psName(ps.getName())
                    .psEmail(ps.getEmail())
                    .psHomepage(ps.getHomepage())
                    .psNumber(ps.getNumber())
                    .defaultTime(map)
                    .beforeImgPath(domain + report.getBeforeImgPath())
                    .afterImgPath(domain + report.getAfterImgPath())
                    .content(report.getContent())
                    .estimate(report.getEstimate())
                    .subCategoryName(reservationSubRepository.getSubCategoryNameByReservationSeq(c.getReservation().getSeq()))
                    .build();

            return respDto;
        }


        return null;
    }
}
