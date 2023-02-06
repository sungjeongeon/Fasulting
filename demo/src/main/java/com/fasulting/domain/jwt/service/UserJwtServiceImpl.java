package com.fasulting.domain.jwt.service;

import com.fasulting.domain.jwt.dto.reqDto.UserLoginReqDto;
import com.fasulting.entity.TokenEntity;
import com.fasulting.entity.user.UserEntity;
import com.fasulting.entity.user.UserTokenEntity;
import com.fasulting.repository.token.TokenRepository;
import com.fasulting.repository.token.UserTokenRepository;
import com.fasulting.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserJwtServiceImpl implements UserJwtService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final UserTokenRepository userTokenRepository;
    private final JwtServiceImpl jwtService;


    @Override
    public Map<String, Object> login(UserLoginReqDto userInfo) {
        Map<String, Object> resultMap = new HashMap<>();


        if(userRepository.findUserByEmailAndPassword(userInfo.getEmail(), userInfo.getPassword()).isPresent()) {

            UserEntity user = userRepository.findUserByEmailAndPassword(userInfo.getEmail(), userInfo.getPassword()).get();

            String accessToken = jwtService.createAccessToken(user.getEmail(), user.getRole().getAuthority());
            String refreshToken = jwtService.createRefreshToken(user.getEmail(), user.getRole().getAuthority());

            // 기존 refresh 토큰 삭제
            if(userTokenRepository.findByUser(user).isPresent()){
                UserTokenEntity userToken = userTokenRepository.findByUser(user).get();

                TokenEntity token = userToken.getToken();

                tokenRepository.delete(token);
                userTokenRepository.delete(userToken);
            }

            // refresh 토큰 저장
            TokenEntity token = TokenEntity.builder()
                    .refreshToken(refreshToken)
                    .build();

            UserTokenEntity userToken = UserTokenEntity.builder()
                    .user(user)
                    .token(token)
                    .build();

            tokenRepository.save(token);
            userTokenRepository.save(userToken);

            resultMap.put("access-token", accessToken);
            resultMap.put("refresh-token", refreshToken);
            resultMap.put("message", "success");

            return resultMap;

        }

        return null;
    }
}
