package com.fasulting.domain.user.favorite.service;

import com.fasulting.domain.user.favorite.dto.reqDto.FavoriteReq;
import com.fasulting.domain.user.favorite.dto.respDto.FavoriteResp;

import java.util.List;

public interface FavoriteService {

    List<FavoriteResp> getFavoriteList(Long userSeq); // 즐겨찾기 리스트 조회

    boolean addFavorite(FavoriteReq favoriteReq); // 즐겨찾기 추가

    boolean deleteFavorite(FavoriteReq favoriteReq); // 즐겨찾기 추가
}
