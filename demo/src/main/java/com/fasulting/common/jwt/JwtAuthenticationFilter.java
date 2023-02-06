package com.fasulting.common.jwt;

import com.fasulting.domain.jwt.service.JwtServiceImpl;
import com.fasulting.exception.UnAuthorizedException;
import com.fasulting.repository.token.TokenRepository;
import com.fasulting.repository.token.UserTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

//해당 클래스는 JwtTokenProvider가 검증을 끝낸 Jwt로부터 유저 정보를 조회해와서 UserPasswordAuthenticationFilter 로 전달합니다.
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtServiceImpl jwtService;
    private final UserTokenRepository userTokenRepository;
    private final TokenRepository tokenRepository;


    @Override
    public void doFilterInternal(HttpServletRequest  request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 헤더에서 JWT 를 받아옵니다.
        String token = jwtService.resolveToken(request);
        // 유효한 토큰인지 확인합니다.
        if (token != null && jwtService.validateToken(token)) {
            // 토큰이 유효하면 토큰으로부터 유저 정보를 받아옵니다.
            Authentication authentication = jwtService.getAuthentication(token);
            // SecurityContext 에 Authentication 객체를 저장합니다.
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // access 토큰이 존재하지만 유효하지 않을 때
        else if(token != null && !jwtService.validateToken(token)){
            // 쿠키에 저장된 refreshToken
            String refreshToken = getRefreshToken(request);

            String newAccessToken = jwtService.validateRefreshToken(refreshToken);

            if(isValidRefreshToken(refreshToken, newAccessToken)){
                // 토큰이 유효하면 토큰으로부터 유저 정보를 받아옵니다.
                Authentication authentication = jwtService.getAuthentication(newAccessToken);
                // SecurityContext 에 Authentication 객체를 저장합니다.
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            else {
                throw new UnAuthorizedException();
            }
        }


        chain.doFilter(request, response);
    }

    private String getRefreshToken(HttpServletRequest request) {
        if (request.getCookies() != null) {
            return Arrays.stream(request.getCookies())
                    .filter(cookie -> cookie.getName().equals("refreshToken"))
                    .findFirst()
                    .map(Cookie::getValue)
                    .orElseThrow(() -> new UnAuthorizedException());
        } else {
            throw new UnAuthorizedException();
        }
    }

    private boolean isValidRefreshToken(String refreshToken, String accessToken) {
        String userEmail = jwtService.getUserEmail(accessToken);
        log.info("userEmail outer : " + userEmail);

        if(tokenRepository.findByRefreshToken(refreshToken).isPresent()){
            String email = userTokenRepository.findByToken((tokenRepository.findByRefreshToken(refreshToken).get())).get().getUser().getEmail();
            log.info("userEmail : " + userEmail);
            log.info("email : " + email);
            if(userEmail.equals(email)){
                return true;
            }
        }
        return false;
    }

}