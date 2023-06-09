package com.fasulting.domain.user.favorite.service;

import com.fasulting.common.util.CheckInfo;
import com.fasulting.common.util.LogCurrent;
import com.fasulting.domain.user.favorite.dto.reqDto.FavoriteReq;
import com.fasulting.domain.user.favorite.dto.respDto.FavoriteResp;
import com.fasulting.entity.ps.PsEntity;
import com.fasulting.entity.user.FavoriteEntity;
import com.fasulting.entity.user.UserEntity;
import com.fasulting.exception.UnAuthorizedException;
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

import static com.fasulting.common.util.LogCurrent.*;

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

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));


        UserEntity user = userRepository.findById(userSeq).orElseThrow(() -> {
            throw new NullPointerException();
        });
//        if (!CheckInfo.checkLoginInfo(user.getSeq(), user.getEmail(), user.getRole().getAuthority())){
//            log.error(logCurrent(getClassName(), getMethodName(), END));
//            throw new UnAuthorizedException();
//        }

        List<FavoriteResp> favoriteRespList = new ArrayList<>();
        List<FavoriteEntity> list = favoriteRepository.findAllByUserSeq(userSeq);

        for (FavoriteEntity favorite : list) {
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

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
        return favoriteRespList;
    }

    @Override
    public boolean addFavorite(FavoriteReq favoriteReq) {

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));
        UserEntity user = userRepository.findById(favoriteReq.getUserSeq()).orElseThrow(() -> {
            throw new NullPointerException();
        });
//        if (!CheckInfo.checkLoginInfo(user.getSeq(), user.getEmail(), user.getRole().getAuthority())){
//            log.error(logCurrent(getClassName(), getMethodName(), END));
//            throw new UnAuthorizedException();
//        }

        PsEntity ps = psRepository.findById(favoriteReq.getPsSeq()).orElseThrow(() -> {
            throw new NullPointerException();
        });

        if (user != null && ps != null) {
            FavoriteEntity favorite = FavoriteEntity.builder()
                    .user(user)
                    .ps(ps)
                    .build();

            favoriteRepository.save(favorite);

            log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
            return true;
        }

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
        return false;
    }

    @Override
    public boolean deleteFavorite(FavoriteReq favoriteReq) {

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));
        UserEntity user = userRepository.findById(favoriteReq.getUserSeq()).orElseThrow(() -> {
            throw new NullPointerException();
        });

//        if (!CheckInfo.checkLoginInfo(user.getSeq(), user.getEmail(), user.getRole().getAuthority())){
//            log.error(logCurrent(getClassName(), getMethodName(), END));
//            throw new UnAuthorizedException();
//        }

        Long userSeq = user.getSeq();

        PsEntity ps = psRepository.findById(favoriteReq.getPsSeq()).orElseThrow(() -> {
            throw new NullPointerException();
        });
        Long psSeq = ps.getSeq();

        if (userSeq != null && psSeq != null) {
            FavoriteEntity favorite = favoriteRepository.findByUserSeqPsSeq(userSeq, psSeq).orElseThrow(() -> {
                throw new NullPointerException();
            });

            if (favorite != null) {
                favoriteRepository.delete(favorite);

                log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
                return true;
            }
        }

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));;
        return false;
    }
}
