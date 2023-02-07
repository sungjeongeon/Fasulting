package com.fasulting.config;

import com.fasulting.common.RoleType;
import com.fasulting.common.filter.jwt.JwtAuthenticationFilter;
import com.fasulting.domain.jwt.service.JwtTokenProvider;
import com.fasulting.repository.token.PsTokenRepository;
import com.fasulting.repository.token.TokenRepository;
import com.fasulting.repository.token.UserTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtService;
    private final UserTokenRepository userTokenRepository;
    private final TokenRepository tokenRepository;
    private final PsTokenRepository psTokenRepository;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/test").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/favorite/**").access("hasAuthority('USER')") // 쿠키에 토큰 저장됨
                .antMatchers("/**").permitAll()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtService, userTokenRepository, psTokenRepository, tokenRepository),
                          UsernamePasswordAuthenticationFilter.class);
    }

//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

}
