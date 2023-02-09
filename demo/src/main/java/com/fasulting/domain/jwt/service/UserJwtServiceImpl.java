package com.fasulting.domain.jwt.service;

import com.fasulting.common.RoleType;
import com.fasulting.common.util.CheckInfo;
import com.fasulting.domain.jwt.JwtTokenProvider;
import com.fasulting.domain.jwt.dto.reqDto.LoginReqDto;
import com.fasulting.domain.jwt.dto.respDtio.UserLoginRespDto;
import com.fasulting.entity.token.TokenEntity;
import com.fasulting.entity.user.UserEntity;
import com.fasulting.entity.token.UserTokenEntity;
import com.fasulting.repository.token.TokenRepository;
import com.fasulting.repository.token.UserTokenRepository;
import com.fasulting.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserJwtServiceImpl implements UserJwtService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final UserTokenRepository userTokenRepository;
    private final JwtTokenProvider jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserLoginRespDto login(LoginReqDto userInfo) {

        UserEntity user = userRepository.findUserByEmail(userInfo.getEmail()).orElseThrow(() -> new NullPointerException());

        if (passwordEncoder.matches(userInfo.getPassword(), user.getPassword())) {
            String accessToken = null;
            String refreshToken = null;
            boolean adminYn = false;
            if(user.getRole().getAuthority().equals(RoleType.USER)) {
                accessToken = jwtService.createAccessToken(user.getEmail(), user.getRole().getAuthority(), RoleType.USER);
                refreshToken = jwtService.createRefreshToken(user.getEmail(), user.getRole().getAuthority(), RoleType.USER);
            }
            if(user.getRole().getAuthority().equals(RoleType.ADMIN)){
                accessToken = jwtService.createAccessToken(user.getEmail(), user.getRole().getAuthority(), RoleType.ADMIN);
                refreshToken = jwtService.createRefreshToken(user.getEmail(), user.getRole().getAuthority(), RoleType.ADMIN);
                adminYn = true;
            }

            log.info(user.toString());

            TokenEntity token = TokenEntity.builder()
                    .refreshToken(refreshToken)
                    .build();

            log.info(token.toString());

            tokenRepository.save(token);

            log.info(tokenRepository.findByRefreshToken(refreshToken).get().toString());

            // 기존 refresh 토큰 삭제
            if (userTokenRepository.findByUser(user).isPresent()) {
                UserTokenEntity userToken = userTokenRepository.findByUser(user).get();

                TokenEntity preToken = userToken.getToken();

                userToken.updateToken(tokenRepository.findByRefreshToken(refreshToken).get());
                userTokenRepository.save(userToken);
                tokenRepository.delete(preToken);

            } else {
                UserTokenEntity userToken = UserTokenEntity.builder()
                        .user(user)
                        .token(tokenRepository.findByRefreshToken(refreshToken).get())
                        .build();

                log.info(user.toString());
                log.info(userToken.toString());

                userTokenRepository.save(userToken);
                log.info(userToken.toString());
            }

            UserLoginRespDto userLoginRespDto = UserLoginRespDto.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .userName(user.getName())
                    .userSeq(user.getSeq())
                    .adminYn(adminYn)
                    .build();

            return userLoginRespDto;

        }

        return null;
    }

    @Transactional
    @Override
    public boolean logout(Long userSeq) {

        UserEntity user = userRepository.findById(userSeq).orElseThrow(
                () -> {
                    throw new NullPointerException();
                });

        // 토큰에서 뽑은 정보와 매개변수로 받은 정보 비교
        if (CheckInfo.checkLoginInfo(user.getSeq(), user.getEmail(), user.getRole().getAuthority())) {

            // 기존 refresh 토큰 삭제
            UserTokenEntity userToken = userTokenRepository.findByUser(user).orElseThrow(
                    () -> {
                        throw new NullPointerException();
                    });

            TokenEntity preToken = userToken.getToken();

            userTokenRepository.delete(userToken);
            tokenRepository.delete(preToken);

            return true;
        }

        return false;

    }


}
