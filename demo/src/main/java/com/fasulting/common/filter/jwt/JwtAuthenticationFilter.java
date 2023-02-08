package com.fasulting.common.filter.jwt;

import com.fasulting.common.RoleType;
import com.fasulting.domain.jwt.service.JwtTokenProvider;
import com.fasulting.entity.user.UserEntity;
import com.fasulting.exception.UnAuthorizedException;
import com.fasulting.repository.token.PsTokenRepository;
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

    private final JwtTokenProvider jwtService;
    private final UserTokenRepository userTokenRepository;
    private final PsTokenRepository psTokenRepository;
    private final TokenRepository tokenRepository;


    @Override
    public void doFilterInternal(HttpServletRequest  request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 헤더에서 JWT 를 받아옵니다.
        String token = jwtService.resolveToken(request);

        // 이미 사용된 토큰이 들어오는 경우
        if(token != null && !jwtService.isBlockedToken(request, token)){
            throw new UnAuthorizedException();
        }
        // access 토큰이 존재하지만 유효하지 않을 때
        else if(token != null && !jwtService.isValidToken(token)){
            throw new UnAuthorizedException();
        }
        // access 토큰이 만료되기 전일 때
        else if (token != null && jwtService.isExpiredToken(token)) {
            // 토큰이 유효하면 토큰으로부터 유저 정보를 받아옵니다.
            Authentication authentication = jwtService.getAuthentication(token);
            // SecurityContext 에 Authentication 객체를 저장합니다.
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        // access 토큰이 만료되었을 때
        else if(token != null && !jwtService.isExpiredToken(token)){
            // 쿠키에 저장된 refreshToken
            String refreshToken = getRefreshToken(request);
            // 쿠키에 저장된 refreshToken 유효성 확인으로 새로운 accessToken 생성
            String newAccessToken = jwtService.validateRefreshToken(refreshToken);
            // 생성된 accessToken 에 대한 유효성 검증
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
        String domain = jwtService.getDomain(accessToken);
        log.info("userEmail : " + userEmail);
        log.info("user domain : " + domain);

        if(tokenRepository.findByRefreshToken(refreshToken).isPresent()){
            if(RoleType.USER.equals(domain)){
                UserEntity user = userTokenRepository.findByToken((tokenRepository.findByRefreshToken(refreshToken).get())).get().getUser();
                String email = user.getEmail();
                String authority = user.getRole().getAuthority();

                log.info("role : " + authority);

                if(userEmail.equals(email) && RoleType.USER.equals(authority)){
                    return true;
                }
            }

            if(RoleType.ADMIN.equals(domain)){
                UserEntity user = userTokenRepository.findByToken((tokenRepository.findByRefreshToken(refreshToken).get())).get().getUser();
                String email = user.getEmail();
                String authority = user.getRole().getAuthority();
                if(userEmail.equals(email) && RoleType.ADMIN.equals(authority)){
                    return true;
                }
            }

            if(RoleType.PS.equals(domain)){
                String email = psTokenRepository.findByToken((tokenRepository.findByRefreshToken(refreshToken).get())).get().getPs().getEmail();
                if(userEmail.equals(email)){
                    return true;
                }
            }
        }
        return false;
    }

}