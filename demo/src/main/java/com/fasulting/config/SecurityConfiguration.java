package com.fasulting.config;

import com.fasulting.common.jwt.JwtAuthenticationFilter;
import com.fasulting.domain.jwt.service.JwtServiceImpl;
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

    private final JwtServiceImpl jwtService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/test").authenticated()
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/favorite/**").access("hasAuthority('user')") // 쿠키에 토큰 저장됨
                .antMatchers("/**").permitAll()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtService),
                          UsernamePasswordAuthenticationFilter.class);
    }

//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

}
