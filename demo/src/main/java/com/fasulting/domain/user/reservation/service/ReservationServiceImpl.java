package com.fasulting.domain.user.reservation.service;

import com.fasulting.common.dto.respDto.PsOperatingRespDto;
import com.fasulting.common.util.Date2String;
import com.fasulting.common.util.FileManage;
import com.fasulting.domain.user.main.dto.respDto.MainCategoryRespDto;
import com.fasulting.domain.user.main.dto.respDto.SubCategoryRespDto;
import com.fasulting.domain.user.reservation.dto.reqDto.CancelReservationReqDto;
import com.fasulting.domain.user.reservation.dto.reqDto.RegReservationReqDto;
import com.fasulting.domain.user.reservation.dto.respDto.PostReservationRespDto;
import com.fasulting.domain.user.reservation.dto.respDto.PreReservationRespDto;
import com.fasulting.domain.user.reservation.dto.respDto.ReportRespDto;
import com.fasulting.domain.user.reservation.dto.respDto.ReservationTableRespDto;
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
import com.fasulting.repository.category.SubCategoryRepository;
import com.fasulting.repository.consulting.ConsultingRepository;
import com.fasulting.repository.consulting.ReportRepository;
import com.fasulting.repository.ps.*;
import com.fasulting.repository.reservation.ReservationRepository;
import com.fasulting.repository.reservation.ReservationSubRepository;
import com.fasulting.repository.review.ReviewRepository;
import com.fasulting.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
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

    @Autowired
    public ReservationServiceImpl(PsOperatingRepository psOperatingRepository, PsMainRepository psMainRepository,
                                  PsMainSubRepository psMainSubRepository, OperatingCalRepository operatingCalRepository,
                                  TimeRepository timeRepository, PsRepository psRepository,
                                  ReservationCalRepository reservationCalRepository, UserRepository userRepository,
                                  ReservationRepository reservationRepository, ConsultingRepository consultingRepository,
                                  ReportRepository reportRepository, ReviewRepository reviewRepository,
                                  ReservationSubRepository reservationSubRepository, PsDefaultRepository psDefaultRepository,
                                  SubCategoryRepository subCategoryRepositor) {
        this.psOperatingRepository = psOperatingRepository;
        this.psMainRepository = psMainRepository;
        this.psMainSubRepository = psMainSubRepository;
        this.operatingCalRepository = operatingCalRepository;
        this.timeRepository = timeRepository;
        this.psRepository = psRepository;
        this.reservationCalRepository = reservationCalRepository;
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
        this.consultingRepository = consultingRepository;
        this.reportRepository = reportRepository;
        this.reviewRepository = reviewRepository;
        this.reservationSubRepository = reservationSubRepository;
        this.psDefaultRepository = psDefaultRepository;
        this.subCategoryRepository = subCategoryRepositor;

    }

    /**
     * 병원 예약 가능 테이블 조회
     *
     * @param psSeq
     * @param current
     * @return
     */
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
            log.info(entrySet.getKey() + " : " + entrySet.getValue());
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

    /**
     * 예약 등록
     *
     * @param regReservationReqDto
     * @return true or false
     */
    @Override
    public boolean addReservation(RegReservationReqDto regReservationReqDto) {

        log.info("addReservation - call");

        int year = regReservationReqDto.getYear();
        int month = regReservationReqDto.getMonth();
        int day = regReservationReqDto.getDay();
        int timeNum = regReservationReqDto.getTime();


        ///// 병원 운영 시간(operating)에서 해당 시간 유효한가 확인 ////

        // yyyy.mm.dd 로 operating_cal 구하기
        // time(num)으로 time 구하기
        // 위 두개로 PsOperating 구하기


        // operating_cal 구하기
        if (!operatingCalRepository.findByYearAndMonthAndDay(year, month, day).isPresent()) {
            return false;
        }
        OperatingCalEntity oc = operatingCalRepository.findByYearAndMonthAndDay(year, month, day).get();

//        log.info(oc.toString());

        // time 구하기
        if (!timeRepository.findByNum(timeNum).isPresent()) {
            return false;
        }
        TimeEntity t = timeRepository.findByNum(timeNum).get();

        PsOperatingId poId = PsOperatingId.builder()
                .ps(psRepository.findById(regReservationReqDto.getPsSeq()).get())
                .operatingCal(operatingCalRepository.findById(oc.getSeq()).get())
                .time(timeRepository.findById(t.getSeq()).get())
                .build();

        if (!psOperatingRepository.findById(poId).isPresent()) {
            return false;
        }
        PsOperatingEntity po = psOperatingRepository.findById(poId).get();

        //// 예약 등록 ////
        if (!reservationCalRepository.findByYearAndMonthAndDay(year, month, day).isPresent()) {
            return false;
        }

        ReservationCalEntity rc = reservationCalRepository.findByYearAndMonthAndDay(year, month, day).get();
        PsEntity ps = psRepository.findById(regReservationReqDto.getPsSeq()).get();
        UserEntity user = userRepository.findById(regReservationReqDto.getUserSeq()).get();

        MultipartFile beforeImgFile = regReservationReqDto.getBeforeImg();

        String beforeImgPath = null;
        if (beforeImgFile != null && !beforeImgFile.isEmpty()) {
            // 파일 중복명 방지 uuid 생성
            UUID uuid = UUID.randomUUID();

            beforeImgPath = FileManage.uploadFile(beforeImgFile, uuid,null, FileManage.beforeImgDirPath);
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

        // 예약 - 서브 카테고리 매핑
        List<Long> subSeqList = regReservationReqDto.getSubCategory();

        for(Long subSeq : subSeqList) {
            SubCategoryEntity sub = subCategoryRepository.findById(subSeq).get();

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

    @Override
    public List<PreReservationRespDto> getPreReservationList(Long userSeq) {

        log.info("getPreReservationList - call");

        List<PreReservationRespDto> respList = new ArrayList<>();

        List<ConsultingEntity> cList = consultingRepository.findAllByUser(userRepository.findById(userSeq).get());

//        log.info(cList.toString());

        for (ConsultingEntity c : cList) {

            log.info(c.toString());

            String estimate = "";
            boolean isReported = false;
            if (reportRepository.findByConsulting(c).isPresent()) {
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

    @Override
    public List<PostReservationRespDto> getPostReservationList(Long userSeq) {

        log.info("getPostReservationList - call");

        List<PostReservationRespDto> respList = new ArrayList<>();

        List<ReservationEntity> rList = reservationRepository.findAllByUserSeq(userRepository.findById(userSeq).get().getSeq());

        for (ReservationEntity r : rList) {

            if ("N".equals(r.getDelYn()) && !consultingRepository.findByReservation(r).isPresent()) {

                PostReservationRespDto respDto = PostReservationRespDto.builder()
                        .reservationSeq(r.getSeq())
                        .psName(r.getPs().getName())
                        .date(Date2String.date2String(r.getReservationCal().getYear(),
                                r.getReservationCal().getMonth(),
                                r.getReservationCal().getDay(),
                                r.getReservationCal().getDayOfWeek(),
                                r.getTime().getStartHour(),
                                r.getTime().getStartMin()))
                        .subCategoryName(reservationSubRepository.getSubCategoryNameByReservationSeq(r.getSeq()))
                        .build();

                respList.add(respDto);
            }
        }

        return respList;
    }

    @Override
    public boolean cancelReservation(CancelReservationReqDto cancelReservationReqDto) {

        log.info("cancelReservation - call");

        Long rSeq = cancelReservationReqDto.getReservationSeq();
        Long uSeq = cancelReservationReqDto.getUserSeq();

        // 예약 delYn >> Y로 변경
        if (reservationRepository.findById(rSeq).isPresent() && reservationRepository.findById(rSeq).get().getUser().getSeq() == uSeq) {

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

    @Override
    public ReportRespDto getReport(Long userSeq, Long consultingSeq) {

        log.info("getReport - call");

        if (consultingRepository.findById(consultingSeq).isPresent()) {

            ConsultingEntity c = consultingRepository.findById(consultingSeq).get();

            if(c.getUser().getSeq() != consultingSeq){
                return null;
            }

            // 병원
            PsEntity ps = consultingRepository.findById(consultingSeq).get().getPs();

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
            ReportEntity report = reportRepository.findByConsulting(c).get();

            ReportRespDto respDto = ReportRespDto.builder()
                    .psSeq(ps.getSeq())
                    .psAddress(ps.getAddress())
                    .psName(ps.getName())
                    .psEmail(ps.getEmail())
                    .psHomepage(ps.getHomepage())
                    .psNumber(ps.getNumber())
                    .defaultTime(map)
                    .beforeImgPath(report.getBeforeImgPath())
                    .afterImgPath(report.getAfterImgPath())
                    .content(report.getContent())
                    .estimate(report.getEstimate())
                    .subCategoryName(reservationSubRepository.getSubCategoryNameByReservationSeq(c.getReservation().getSeq()))
                    .build();

            return respDto;
        }


        return null;
    }
}
