package com.fasulting.domain.user.user.service;

import com.fasulting.domain.user.user.dto.reqDto.UserLoginReqDto;
import com.fasulting.entity.user.UserEntity;
import com.fasulting.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserLoginServiceImpl implements UserLoginService {

    private final UserRepository userRepository;
    @Override
    public UserEntity login(UserLoginReqDto userInfo) {
        if(userRepository.findUserByEmailAndPassword(userInfo.getEmail(), userInfo.getPassword()).isPresent()) {

            UserEntity user = userRepository.findUserByEmailAndPassword(userInfo.getEmail(), userInfo.getPassword()).get();


            return user;

        }

        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(username).orElseThrow();
    }
}
