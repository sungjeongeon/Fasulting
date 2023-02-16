package com.fasulting.config;

import com.fasulting.common.filter.jwt.JwtAuthenticationFilter;
import com.fasulting.domain.jwt.JwtTokenProvider;
import com.fasulting.repository.token.PsTokenRepository;
import com.fasulting.repository.token.TokenRepository;
import com.fasulting.repository.token.UserTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtService;
    private final UserTokenRepository userTokenRepository;
    private final TokenRepository tokenRepository;
    private final PsTokenRepository psTokenRepository;

//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic().disable()
                .authorizeRequests()

                .antMatchers("/user/logout/**").access("hasAnyAuthority('USER', 'ADMIN')")
                .antMatchers("/ps/logout/**").access("hasAnyAuthority('PS')")
//                .antMatchers("/user/info/**", "/user/withdraw", "/user/passcheck", "/user/edit",
//                        "/favorite/**", "/main/ps-detail/**", "/reservation/**", "/review/**").access("hasAuthority('USER')")
//                .antMatchers("/ps/logout/**", "/ps/address", "/ps/intro", "/ps/number", "/ps/homepage", "/ps/category", "/ps/operating/**",
//                        "/ps/profile", "/ps/doctor", "/ps/withdraw", "/ps/passcheck", "/ps/info/**", "/ps-reservation/**",
//                        "/ps-consulting/download/**", "/ps-consulting/result/**", "/ps-review/**").access("hasAuthority('PS')")
//                .antMatchers("/admin/**").access("hasAuthority('ADMIN')")


                .antMatchers("/**").permitAll()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtService, userTokenRepository, psTokenRepository, tokenRepository),
                          UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
