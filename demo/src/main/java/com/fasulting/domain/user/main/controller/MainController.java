package com.fasulting.domain.user.main.controller;

import com.fasulting.common.dto.respDto.MainCategoryRespDto;
import com.fasulting.common.dto.respDto.SubCategoryRespDto;
import com.fasulting.common.resp.ResponseBody;
import com.fasulting.common.util.LogCurrent;
import com.fasulting.domain.user.main.dto.respDto.PsDetailRespDto;
import com.fasulting.domain.user.main.dto.respDto.PsListRespDto;
import com.fasulting.domain.user.main.service.MainService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.fasulting.common.util.LogCurrent.*;

@Api(value = "상담자 메인 페이지 API", tags = {"MainController"})
@Slf4j
@RestController
@RequestMapping("/main")
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    /**
     * 메인 카테고리 조회
     * @return
     */
    @GetMapping
    public ResponseEntity<?> getMainList() {

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));
        List<MainCategoryRespDto> resp = mainService.getMainCategoryList();

        if (!resp.isEmpty()) {
            log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success", resp));
        }
        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(204).body(ResponseBody.create(204, "fail"));
    }

    /**
     * 메인 카테고리에 속하는 서브 카테고리 조회
     * @param mainSeq
     * @return
     */
    @GetMapping("/sub/{mainSeq}")
    public ResponseEntity<?> getSubList(@PathVariable Long mainSeq) {

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));
        List<SubCategoryRespDto> resp = mainService.getSubcategoryList(mainSeq);

        if (!resp.isEmpty()) {
            log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success", resp));
        }
        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(204).body(ResponseBody.create(204, "fail"));
    }

    /**
     * 메인 카테고리에 속하는 병원 리스트 조회
     * @param mainSeq
     * @return
     */
    @GetMapping("/ps-list/{mainSeq}")
    public ResponseEntity<?> getPsList(@PathVariable Long mainSeq) {
        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));
        List<PsListRespDto> resp = mainService.getPsList(mainSeq);

        if (!resp.isEmpty()) {
            log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success", resp));
        }
        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(204).body(ResponseBody.create(204, "fail"));
    }

    /**
     * 선택한 병원에 대한 디테일 정보(페이지 전체)
     * @param userSeq
     * @param psSeq
     * @return
     */
    @GetMapping("/ps-detail/{userSeq}/{psSeq}")
    public ResponseEntity<?> getPsDetail(@PathVariable Long userSeq, @PathVariable Long psSeq) {

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));
        PsDetailRespDto resp = mainService.getPsDetail(userSeq, psSeq);

        if (resp != null) {
            log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success", resp));
        }
        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(204).body(ResponseBody.create(204, "fail"));
    }
}
