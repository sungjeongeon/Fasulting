package com.fasulting.demo.customer.main.controller;

import com.fasulting.demo.customer.main.service.MainService;
import com.fasulting.demo.resp.ResponseBody;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "상담자 메인 페이지 API", tags = {"MainController"})
@Slf4j
@RestController
@RequestMapping("/")
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
        if (mainService.getMainCategoryList() != null) {
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success", mainService.getMainCategoryList()));
        }

        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
    }

    /**
     * 메인 카테고리에 속하는 서브 카테고리 조회
     * @param mainSeq
     * @return
     */
    @GetMapping("sub/{mainSeq}")
    public ResponseEntity<?> getSubList(@PathVariable Long mainSeq) {
        if (mainService.getSubcategoryList(mainSeq) != null) {
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success", mainService.getSubcategoryList(mainSeq)));
        }

        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
    }

    @GetMapping("ps-list/{mainSeq}")
    public ResponseEntity<?> getPsList(@PathVariable Long mainSeq) {
        if (mainService.getPsList(mainSeq) != null) {
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success", mainService.getPsList(mainSeq)));
        }

        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
    }

    @GetMapping("ps-detail/{userSeq}/{psSeq}")
    public ResponseEntity<?> getPsDetail(@PathVariable Long userSeq, @PathVariable Long psSeq) {
        if (mainService.getPsDetail(psSeq, userSeq) != null) {
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success", mainService.getPsDetail(psSeq, userSeq)));
        }

        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
    }
}
