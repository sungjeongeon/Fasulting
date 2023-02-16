package com.fasulting.domain.jwt.service;

import com.fasulting.common.RoleType;
import com.fasulting.common.util.CheckInfo;
import com.fasulting.common.util.LogCurrent;
import com.fasulting.domain.jwt.JwtTokenProvider;
import com.fasulting.domain.jwt.dto.reqDto.LoginReqDto;
import com.fasulting.domain.jwt.dto.respDtio.TokenRespDto;
import com.fasulting.domain.jwt.dto.respDtio.UserLoginRespDto;
import com.fasulting.entity.token.TokenEntity;
import com.fasulting.entity.token.UserTokenEntity;
import com.fasulting.entity.user.UserEntity;
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

import static com.fasulting.common.util.LogCurrent.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserJwtServiceImpl implements UserJwtService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final UserTokenRepository userTokenRepository;
    private final JwtTokenProvider jwtService;
    private final PasswordEncoder passwordEncoder;

    /**
     * user & admin login
     * @param userInfo
     * @return
     */
    @Override
    @Transactional
    public Map<String, Object> login(LoginReqDto userInfo) {

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));
        
        // 계정 정보가 없는 경우
        if(!userRepository.findUserByEmail(userInfo.getEmail()).isPresent()){
            log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
            return null;
        }
        UserEntity user = userRepository.findUserByEmail(userInfo.getEmail()).get();

        if (passwordEncoder.matches(userInfo.getPassword(), user.getPassword())) {
            String accessToken = null;
            String refreshToken = null;
            boolean adminYn = false;
            if (user.getRole().getAuthority().equals(RoleType.USER)) {
                accessToken = jwtService.createAccessToken(user.getEmail(), user.getRole().getAuthority(), RoleType.USER);
                refreshToken = jwtService.createRefreshToken(user.getEmail(), user.getRole().getAuthority(), RoleType.USER);
            }
            if (user.getRole().getAuthority().equals(RoleType.ADMIN)) {
                accessToken = jwtService.createAccessToken(user.getEmail(), user.getRole().getAuthority(), RoleType.ADMIN);
                refreshToken = jwtService.createRefreshToken(user.getEmail(), user.getRole().getAuthority(), RoleType.ADMIN);
                adminYn = true;
            }

            TokenEntity token = TokenEntity.builder()
                    .refreshToken(refreshToken)
                    .build();

            tokenRepository.save(token);

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

                userTokenRepository.save(userToken);
            }

            UserLoginRespDto userLoginRespDto = UserLoginRespDto.builder()
                    .userName(user.getName())
                    .userSeq(user.getSeq())
                    .adminYn(adminYn)
                    .build();

            TokenRespDto tokenRespDto = TokenRespDto.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();

            Map<String, Object> map = new HashMap<>();
            map.put("userLoginRespDto", userLoginRespDto);
            map.put("tokenRespDto", tokenRespDto);
            log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
            return map;

        }

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
        return null;
    }

    /**
     * user & admin logout
     * @param userSeq
     * @return
     */
    @Transactional
    @Override
    public boolean logout(Long userSeq) {

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));

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

            log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
            return true;
        }
        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
        return false;

    }


}
