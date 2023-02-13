package com.fasulting.domain.admin.review.controller;

import com.fasulting.common.dto.respDto.ReviewRespDto;
import com.fasulting.common.resp.ResponseBody;
import com.fasulting.common.util.LogCurrent;
import com.fasulting.domain.admin.review.dto.reqDto.AdminReviewReqDto;
import com.fasulting.domain.admin.review.service.AdminReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fasulting.common.util.LogCurrent.*;

@RestController
@Slf4j
@RequestMapping("/admin/review")
@RequiredArgsConstructor
public class AdminReviewController {

    private final AdminReviewService adminReviewService;

    /**
     * 신고된 리뷰 조회
     * @return List<ReviewRespDto>
     */
    @GetMapping
    public ResponseEntity<?> getAccusedReviewList() {

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));
        List<ReviewRespDto> accusedReviewList = adminReviewService.getAccusedReviewList();

        if (!accusedReviewList.isEmpty()) {
            log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success", accusedReviewList));
        }
        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(204).body(ResponseBody.create(204, "fail")); // 작성한 리뷰 없음

    }

    /**
     * 리뷰 삭제
     * @return success or fail
     */
    @PatchMapping
    public ResponseEntity<?> deleteReview(@RequestBody AdminReviewReqDto adminReviewReq) {

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));
        if (adminReviewService.deleteReview(adminReviewReq)) {
            log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }
        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail")); // 리뷰 삭제 실패

    }

}
