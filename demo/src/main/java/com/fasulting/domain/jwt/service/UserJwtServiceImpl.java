package com.fasulting.domain.jwt.service;

import com.fasulting.common.RoleType;
import com.fasulting.common.interceptor.jwt.LoginInfo;
import com.fasulting.domain.jwt.dto.reqDto.UserLoginReqDto;
import com.fasulting.entity.token.TokenEntity;
import com.fasulting.entity.user.UserEntity;
import com.fasulting.entity.token.UserTokenEntity;
import com.fasulting.repository.token.TokenRepository;
import com.fasulting.repository.token.UserTokenRepository;
import com.fasulting.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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

    @Override
    @Transactional
    public Map<String, Object> login(UserLoginReqDto userInfo) {
        Map<String, Object> resultMap = new HashMap<>();


        if (userRepository.findUserByEmailAndPassword(userInfo.getEmail(), userInfo.getPassword()).isPresent()) {

            UserEntity user = userRepository.findUserByEmailAndPassword(userInfo.getEmail(), userInfo.getPassword()).get();

            String accessToken = jwtService.createAccessToken(user.getEmail(), user.getRole().getAuthority(), RoleType.USER);
            String refreshToken = jwtService.createRefreshToken(user.getEmail(), user.getRole().getAuthority(), RoleType.USER);

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


            resultMap.put("access-token", accessToken);
            resultMap.put("refresh-token", refreshToken);
            resultMap.put("message", "success");

            return resultMap;

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
        if(LoginInfo.getEmail().equals(user.getEmail()) &&
                LoginInfo.getSeq() == user.getSeq() &&
                LoginInfo.getDomain().equals(user.getRole().getAuthority())){

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
