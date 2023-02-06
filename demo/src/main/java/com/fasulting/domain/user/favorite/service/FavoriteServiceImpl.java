package com.fasulting.domain.user.favorite.service;

import com.fasulting.domain.user.favorite.dto.reqDto.FavoriteReq;
import com.fasulting.domain.user.favorite.dto.respDto.FavoriteResp;
import com.fasulting.entity.ps.PsEntity;
import com.fasulting.entity.user.FavoriteEntity;
import com.fasulting.entity.user.UserEntity;
import com.fasulting.repository.ps.PsMainSubRepository;
import com.fasulting.repository.ps.PsRepository;
import com.fasulting.repository.ps.TotalRatingRepository;
import com.fasulting.repository.review.ReviewRepository;
import com.fasulting.repository.user.FavoriteRepository;
import com.fasulting.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;

    private final PsMainSubRepository psMainSubRepository;

    private final TotalRatingRepository totalRatingRepository;

    private final ReviewRepository reviewRepository;

    private final UserRepository userRepository;

    private final PsRepository psRepository;


    /**
     * 즐겨찾기 리스트
     * @param userSeq
     * @return
     */
    @Override
    public List<FavoriteResp> getFavoriteList(Long userSeq) {

        List<FavoriteResp> favoriteRespList= new ArrayList<>();
        List<FavoriteEntity> list = favoriteRepository.findAllByUserSeq(userSeq);

        for(FavoriteEntity favorite : list){
            PsEntity ps = favorite.getPs();
            Long psSeq = favorite.getPs().getSeq();

            FavoriteResp favoriteResp = FavoriteResp.builder()
                    .psSeq(psSeq)
                    .psName(ps.getName())
                    .subCategoryName(psMainSubRepository.getSubNameByPsSeq(psSeq))
                    .totalRatingResult(totalRatingRepository.getResultByPsSeq(psSeq))
                    .reviewTotalCount(reviewRepository.getCountByPsSeq(psSeq))
                    .build();

            favoriteRespList.add(favoriteResp);
        }

        for(FavoriteResp favorite : favoriteRespList){
            log.info(favorite.toString());
        }

        return favoriteRespList;
    }

    @Override
    public boolean addFavorite(FavoriteReq favoriteReq) {

        UserEntity user = userRepository.findById(favoriteReq.getUserSeq()).get();
        PsEntity ps = psRepository.findById(favoriteReq.getPsSeq()).get();

        if(user != null && ps != null){
            FavoriteEntity favorite = FavoriteEntity.builder()
                    .user(user)
                    .ps(ps)
                    .build();

            favoriteRepository.save(favorite);

            log.info("success insert favorite " + favorite.toString());
            return true;
        }

        log.info("fail insert favorite");
        return false;
    }

    @Override
    public boolean deleteFavorite(FavoriteReq favoriteReq) {
        Long userSeq = userRepository.findById(favoriteReq.getUserSeq()).get().getSeq();
        Long psSeq = psRepository.findById(favoriteReq.getPsSeq()).get().getSeq();

        if(userSeq != null && psSeq != null){
            FavoriteEntity favorite = favoriteRepository.findByUserSeqPsSeq(userSeq, psSeq).get();

            if(favorite != null) {
                favoriteRepository.delete(favorite);

                log.info("success delete favorite");
                return true;
            }
        }

        log.info("fail delete favorite");
        return false;
    }
}
