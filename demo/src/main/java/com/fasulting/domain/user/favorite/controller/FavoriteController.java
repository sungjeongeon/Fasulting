package com.fasulting.domain.user.favorite.controller;

import com.fasulting.common.resp.ResponseBody;
import com.fasulting.common.util.LogCurrent;
import com.fasulting.domain.user.favorite.dto.reqDto.FavoriteReq;
import com.fasulting.domain.user.favorite.dto.respDto.FavoriteResp;
import com.fasulting.domain.user.favorite.service.FavoriteService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fasulting.common.util.LogCurrent.*;

@Api(value = "즐겨찾기 관련 API", tags = {"FavoriteController"})
@Slf4j
@RestController
@RequestMapping("/favorite")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    /**
     * 즐겨찿기 조회
     * @param userSeq
     * @return
     */
    @GetMapping("/{userSeq}")
    public ResponseEntity<?> favoriteList(@PathVariable Long userSeq) {

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));
        List<FavoriteResp> favoriteRespList = favoriteService.getFavoriteList(userSeq);
        if(!favoriteRespList.isEmpty()){
            log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success", favoriteRespList));
        }
        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(204).body(ResponseBody.create(204, "fail"));
    }

    /**
     * 즐겨찾기 추가
     * @param favoriteReq
     * @return
     */
    @PostMapping
    public ResponseEntity<?> addFavorite(@RequestBody FavoriteReq favoriteReq) {
        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));
        if(favoriteService.addFavorite(favoriteReq)){
            log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }
        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
    }


    /**
     * 즐겨찾기 삭제
     * @param favoriteReq
     * @return
     */
    @DeleteMapping
    public ResponseEntity<?> deleteFavorite(@RequestBody FavoriteReq favoriteReq) {
        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));
        if(favoriteService.deleteFavorite(favoriteReq)){
            log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }
        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));
    }
}
