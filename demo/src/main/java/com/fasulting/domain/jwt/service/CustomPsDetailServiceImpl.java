package com.fasulting.domain.jwt.service;

import com.fasulting.entity.ps.PsEntity;
import com.fasulting.entity.user.UserEntity;
import com.fasulting.repository.ps.PsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class CustomPsDetailServiceImpl implements UserDetailsService {

    private final PsRepository psRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        PsEntity ps = psRepository.findPsByEmail(username).get();

        return ps;
    }
}
