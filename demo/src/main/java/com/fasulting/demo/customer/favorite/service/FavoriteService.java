package com.fasulting.demo.customer.favorite.service;

import com.fasulting.demo.customer.favorite.dto.reqDto.FavoriteReq;
import com.fasulting.demo.customer.favorite.dto.respDto.FavoriteResp;

import java.util.List;

public interface FavoriteService {

    List<FavoriteResp> getFavoriteList(Long userSeq); // 즐겨찾기 리스트 조회

    boolean addFavorite(FavoriteReq favoriteReq); // 즐겨찾기 추가

    boolean deleteFavorite(FavoriteReq favoriteReq); // 즐겨찾기 추가
}
