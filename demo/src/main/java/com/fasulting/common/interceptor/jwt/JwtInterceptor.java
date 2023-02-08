package com.fasulting.common.interceptor.jwt;

import com.fasulting.common.RoleType;
import com.fasulting.domain.jwt.JwtTokenProvider;
import com.fasulting.entity.ps.PsEntity;
import com.fasulting.entity.user.UserEntity;
import com.fasulting.repository.ps.PsRepository;
import com.fasulting.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Slf4j
@Component
public class JwtInterceptor implements HandlerInterceptor {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PsRepository psRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String accessToken = request.getHeader("Authorization");

        if (accessToken != null) {
            String userEmail = jwtTokenProvider.getUserEmail(accessToken);
            String domain = jwtTokenProvider.getDomain(accessToken);

            if (RoleType.USER.equals(domain)) {
                UserEntity user = userRepository.findUserByEmail(userEmail).get();
                if (user.getRole().getAuthority().equals(RoleType.USER)) {
                    Long seq = user.getSeq();

                    LoginInfo.setSeq(seq);
                    LoginInfo.setEmail(userEmail);
                    LoginInfo.setDomain(domain);

                }

            } else if (RoleType.PS.equals(domain)) {
                PsEntity ps = psRepository.findPsByEmail(userEmail).get();
                Long seq = ps.getSeq();

                log.info(ps.toString());
                LoginInfo.setSeq(seq);
                LoginInfo.setEmail(userEmail);
                LoginInfo.setDomain(domain);

                log.info(LoginInfo.getEmail());


            } else if (RoleType.ADMIN.equals(domain)) {
                UserEntity user = userRepository.findUserByEmail(userEmail).get();
                if (user.getRole().getAuthority().equals(RoleType.ADMIN)) {
                    Long seq = user.getSeq();

                    LoginInfo.setSeq(seq);
                    LoginInfo.setEmail(userEmail);
                    LoginInfo.setDomain(domain);

                }
            }
        }

        return true;
    }
}
