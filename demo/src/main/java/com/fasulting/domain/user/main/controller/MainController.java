package com.fasulting.domain.user.main.controller;

import com.fasulting.common.dto.respDto.MainCategoryRespDto;
import com.fasulting.common.dto.respDto.SubCategoryRespDto;
import com.fasulting.domain.user.main.dto.respDto.PsDetailRespDto;
import com.fasulting.domain.user.main.dto.respDto.PsListRespDto;
import com.fasulting.domain.user.main.service.MainService;
import com.fasulting.common.resp.ResponseBody;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "상담자 메인 페이지 API", tags = {"MainController"})
@Slf4j
@RestController
@RequestMapping("/main")
@CrossOrigin("*") // 수정
public class MainController {

    private final MainService mainService;

    @Autowired
    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    /**
     * 메인 카테고리 조회
     * @return
     */
    @GetMapping
    public ResponseEntity<?> getMainList() {
        List<MainCategoryRespDto> resp = mainService.getMainCategoryList();

        if (!resp.isEmpty()) {
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success", resp));
        }

        return ResponseEntity.status(204).body(ResponseBody.create(204, "fail"));
    }

    /**
     * 메인 카테고리에 속하는 서브 카테고리 조회
     * @param mainSeq
     * @return
     */
    @GetMapping("/sub/{mainSeq}")
    public ResponseEntity<?> getSubList(@PathVariable Long mainSeq) {
        List<SubCategoryRespDto> resp = mainService.getSubcategoryList(mainSeq);

        if (!resp.isEmpty()) {
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success", resp));
        }

        return ResponseEntity.status(204).body(ResponseBody.create(204, "fail"));
    }

    /**
     * 메인 카테고리에 속하는 병원 리스트 조회
     * @param mainSeq
     * @return
     */
    @GetMapping("/ps-list/{mainSeq}")
    public ResponseEntity<?> getPsList(@PathVariable Long mainSeq) {
        List<PsListRespDto> resp = mainService.getPsList(mainSeq);

        if (!resp.isEmpty()) {
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success", resp));
        }

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

        PsDetailRespDto resp = mainService.getPsDetail(psSeq, userSeq);

        if (resp != null) {
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success", resp));
        }

        return ResponseEntity.status(204).body(ResponseBody.create(204, "fail"));
    }
}
