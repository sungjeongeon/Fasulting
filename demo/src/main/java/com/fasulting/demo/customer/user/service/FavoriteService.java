package com.fasulting.demo.customer.user.service;

import com.fasulting.demo.customer.user.dto.reqDto.FavoriteReq;
import com.fasulting.demo.customer.user.dto.reqDto.UserSeqReq;
import com.fasulting.demo.customer.user.dto.reqDto.UserWithoutSeqReq;
import com.fasulting.demo.customer.user.dto.respDto.FavoriteResp;
import com.fasulting.demo.customer.user.dto.respDto.UserInfoResp;

import java.util.List;

public interface FavoriteService {

    List<FavoriteResp> getFavoriteList(Long userSeq); // 즐겨찾기 리스트 조회

    boolean addFavorite(FavoriteReq favoriteReq); // 즐겨찾기 추가

    boolean deleteFavorite(FavoriteReq favoriteReq); // 즐겨찾기 추가
}
