package com.fasulting.domain.jwt;

import com.fasulting.common.RoleType;
import com.fasulting.exception.UnAuthorizedException;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Service
@Slf4j
public class JwtTokenProvider {

    private String secretKey = "myprojectsecret";

    // 토큰 유효시간 60분 - 1분 == 60 * 1000L
    private final long ACCESS_TOKEN_VALID_TIME = 60 * 60 * 1000L;
    // refresh 토큰 유효시간 하루
    private final long REFRESH_TOKEN_VALID_TIME = 24 * 60 * 60 * 1000L;

    private final CustomUserDetailServiceImpl customUserDetailService;
    private final CustomPsDetailServiceImpl customPsDetailService;

    // 객체 초기화, secretKey를 Base64로 인코딩한다.
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // JWT 토큰 생성
    public String createAccessToken(String userEmail, String role, String domain) {
        Claims claims = Jwts.claims().setSubject(userEmail); // JWT payload 에 저장되는 정보단위, 보통 여기서 user를 식별하는 값을 넣는다.
        claims.put("role", role); // 정보는 key / value 쌍으로 저장된다.
        claims.put("domain", domain);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_VALID_TIME)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 사용할 암호화 알고리즘과
                // signature 에 들어갈 secret값 세팅
                .compact();
    }

    public String createRefreshToken(String userEmail, String role, String domain) {

        Claims claims = Jwts.claims().setSubject(userEmail); // JWT payload 에 저장되는 정보단위
        claims.put("role", role); // 정보는 key / value 쌍으로 저장된다.
        claims.put("domain", domain);
        Date now = new Date();

        //Refresh Token
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_VALID_TIME)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 사용할 암호화 알고리즘과
                // signature 에 들어갈 secret값 세팅
                .compact();
    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        try {
            String email = this.getUserEmail(token);
            String domain = this.getDomain(token);
            UserDetails userDetails = null;

            // domain 에 따라 구현한 UserDetailService 사용
            if (domain.equals(RoleType.USER) || domain.equals(RoleType.ADMIN)) {
                userDetails = customUserDetailService.loadUserByUsername(email);
            } else if (domain.equals(RoleType.PS)) {
                userDetails = customPsDetailService.loadUserByUsername(email);
            }

            return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
        } catch (Exception e) {
            throw new UnAuthorizedException();
        }


    }

    // 토큰에서 회원 정보 추출
    public String getUserEmail(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
        } catch (Exception e) {
            throw new UnAuthorizedException();
        }
    }

    public String getDomain(String token) {
        try {
            return (String) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("domain");
        } catch (Exception e) {
            throw new UnAuthorizedException();
        }
    }

    // Request의 Header에서 token 값을 가져옵니다. "Authorization" : "TOKEN값'
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    // access 토큰 만료일자 확인
    public boolean isExpiredToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);

            long min = (claims.getBody().getExpiration().getTime() - new Date().getTime()) / 60000L; // 분

            return min > 30L;
        } catch (ExpiredJwtException e) {
            return false;
        }
    }

    // access 토큰 유효성
    public boolean isValidToken(String jwtToken) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return true;
        } catch (Exception e) {
            throw new UnAuthorizedException();
        }
    }

    // access 토큰이 사용 가능한 토큰인가?
    public boolean isBlockedToken(HttpServletRequest request, String jwtToken) {
        HttpSession session = request.getSession();
        if (session.getAttribute(jwtToken) == null) {
            return true;
        }

        return false;
    }

    // refresh 토큰 유효성 확인
    public String validateRefreshToken(String refreshToken) {

        try {
            // 검증
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(refreshToken);

            //refresh 토큰의 만료시간이 지나지 않았을 경우, 새로운 access 토큰을 생성합니다.
            if (!claims.getBody().getExpiration().before(new Date())) {
                return recreationAccessToken(claims.getBody().get("sub").toString(), claims.getBody().get("role"), claims.getBody().get("domain"));
            }
        } catch (Exception e) {
            throw new UnAuthorizedException();
        }

        return null;
    }

    public String recreationAccessToken(String userEmail, Object role, Object domain) {

        Claims claims = Jwts.claims().setSubject(userEmail); // JWT payload 에 저장되는 정보단위
        claims.put("role", role); // 정보는 key / value 쌍으로 저장된다.
        claims.put("domain", domain);
        Date now = new Date();

        //Access Token
        String accessToken = Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_VALID_TIME)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 사용할 암호화 알고리즘과
                // signature 에 들어갈 secret값 세팅
                .compact();

        return accessToken;
    }

}
