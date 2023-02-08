package com.fasulting.domain.jwt.service;

import com.fasulting.common.RoleType;
import com.fasulting.common.util.CheckInfo;
import com.fasulting.domain.jwt.dto.reqDto.LoginReqDto;
import com.fasulting.entity.ps.PsEntity;
import com.fasulting.entity.token.PsTokenEntity;
import com.fasulting.entity.token.TokenEntity;
import com.fasulting.entity.token.UserTokenEntity;
import com.fasulting.entity.user.UserEntity;
import com.fasulting.repository.ps.PsRepository;
import com.fasulting.repository.token.PsTokenRepository;
import com.fasulting.repository.token.TokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@Service
public class PsJwtServiceImpl implements PsJwtService {

    private final PsRepository psRepository;
    private final TokenRepository tokenRepository;
    private final PsTokenRepository psTokenRepository;
    private final JwtTokenProvider jwtService;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    @Override
    public Map<String, Object> login(LoginReqDto userInfo) {
        Map<String, Object> resultMap = new HashMap<>();

        PsEntity ps = psRepository.findPsByEmail(userInfo.getEmail()).orElseThrow(() -> new NullPointerException());

        if (passwordEncoder.matches(userInfo.getPassword(), ps.getPassword())) {

            String accessToken = jwtService.createAccessToken(ps.getEmail(), RoleType.PS, RoleType.PS);
            String refreshToken = jwtService.createRefreshToken(ps.getEmail(), RoleType.PS, RoleType.PS);

            log.info(ps.toString());

            TokenEntity token = TokenEntity.builder()
                    .refreshToken(refreshToken)
                    .build();

            log.info(token.toString());

            tokenRepository.save(token);

            log.info(tokenRepository.findByRefreshToken(refreshToken).get().toString());

            // 기존 refresh 토큰 삭제
            if (psTokenRepository.findByPs(ps).isPresent()) {
                PsTokenEntity psToken = psTokenRepository.findByPs(ps).get();

                TokenEntity preToken = psToken.getToken();

                psToken.updateToken(tokenRepository.findByRefreshToken(refreshToken).get());
                psTokenRepository.save(psToken);
                tokenRepository.delete(preToken);

            } else {
                PsTokenEntity psToken = PsTokenEntity.builder()
                        .ps(ps)
                        .token(tokenRepository.findByRefreshToken(refreshToken).get())
                        .build();

                log.info(ps.toString());
                log.info(psToken.toString());

                psTokenRepository.save(psToken);
                log.info(psToken.toString());
            }


            resultMap.put("access-token", accessToken);
            resultMap.put("refresh-token", refreshToken);
            resultMap.put("message", "success");

            return resultMap;
        }

        return null;
    }

    @Transactional
    @Override
    public boolean logout(Long psSeq) {
        PsEntity ps = psRepository.findById(psSeq).orElseThrow(
                () -> {
                    throw new NullPointerException();
                });

        // 토큰에서 뽑은 정보와 매개변수로 받은 정보 비교
        if (CheckInfo.checkLoginInfo(ps.getSeq(), ps.getEmail(), RoleType.PS)) {

            // 기존 refresh 토큰 삭제
            PsTokenEntity psToken = psTokenRepository.findByPs(ps).orElseThrow(
                    () -> {
                        throw new NullPointerException();
                    });

            TokenEntity preToken = psToken.getToken();

            psTokenRepository.delete(psToken);
            tokenRepository.delete(preToken);

            return true;
        }

        return false;
    }
}
