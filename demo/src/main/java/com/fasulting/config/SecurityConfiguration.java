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
//                .antMatchers("/test").authenticated()
                .antMatchers("/ps/logout").access("hasAuthority('PS')")
                .antMatchers("/user/logout").access("hasAuthority('USER')")
                .antMatchers("/admin/logout").access("hasAuthority('ADMIN')")
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
