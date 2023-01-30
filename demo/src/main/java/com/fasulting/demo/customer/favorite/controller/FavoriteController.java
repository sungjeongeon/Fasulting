package com.fasulting.demo.customer.favorite.controller;

import com.fasulting.demo.customer.favorite.dto.reqDto.FavoriteReq;
import com.fasulting.demo.customer.favorite.service.FavoriteService;
import com.fasulting.demo.resp.ResponseBody;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "즐겨찾기 관련 API", tags = {"FavoriteController"})
@Slf4j
@RestController
@RequestMapping("/favorite")
@CrossOrigin("*") // 수정
public class FavoriteController {

    private final FavoriteService favoriteService;

    @Autowired
    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    /**
     * 즐겨찿기 조회
     * @param userSeq
     * @return
     */
    @GetMapping("/{userSeq}")
    public ResponseEntity<?> favoriteList(@PathVariable Long userSeq) {
        return ResponseEntity.status(200).body(ResponseBody.create(200, "success", favoriteService.getFavoriteList(userSeq)));
    }

    /**
     * 즐겨찾기 추가
     * @param favoriteReq
     * @return
     */
    @PostMapping
    public ResponseEntity<?> addFavorite(@RequestBody FavoriteReq favoriteReq) {

        if(favoriteService.addFavorite(favoriteReq)){
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }
        return ResponseEntity.status(200).body(ResponseBody.create(500, "fail"));
    }


    /**
     * 즐겨찾기 삭제
     * @param favoriteReq
     * @return
     */
    @DeleteMapping
    public ResponseEntity<?> deleteFavorite(@RequestBody FavoriteReq favoriteReq) {

        if(favoriteService.deleteFavorite(favoriteReq)){
            return ResponseEntity.status(200).body(ResponseBody.create(200, "success"));
        }
        return ResponseEntity.status(200).body(ResponseBody.create(500, "fail"));
    }
}
