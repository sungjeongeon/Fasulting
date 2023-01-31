package com.fasulting.demo.customer.favorite.service;

import com.fasulting.demo.common.rating.TotalRatingRepository;
import com.fasulting.demo.common.review.ReviewRepository;
import com.fasulting.demo.customer.favorite.dto.reqDto.FavoriteReq;
import com.fasulting.demo.customer.favorite.dto.respDto.FavoriteResp;
import com.fasulting.demo.customer.favorite.repository.FavoriteRepository;
import com.fasulting.demo.customer.user.repository.UserRepository;
import com.fasulting.demo.entity.FavoriteEntity;
import com.fasulting.demo.entity.PsEntity;
import com.fasulting.demo.entity.UserEntity;
import com.fasulting.demo.ps.ps.repository.PsMainSubRepository;
import com.fasulting.demo.ps.ps.repository.PsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;

    private final PsMainSubRepository psMainSubRepository;

    private final TotalRatingRepository totalRatingRepository;

    private final ReviewRepository reviewRepository;

    private final UserRepository userRepository;

    private final PsRepository psRepository;

    @Autowired
    public FavoriteServiceImpl(FavoriteRepository favoriteRepository, PsMainSubRepository psMainSubRepository, TotalRatingRepository totalRatingRepository, ReviewRepository reviewRepository, UserRepository userRepository, PsRepository psRepository) {
        this.favoriteRepository = favoriteRepository;
        this.psMainSubRepository = psMainSubRepository;
        this.totalRatingRepository = totalRatingRepository;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.psRepository = psRepository;
    }

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
