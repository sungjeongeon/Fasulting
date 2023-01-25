package com.fasulting.demo.customer.user.service;

import com.fasulting.demo.customer.user.dto.reqDto.*;
import com.fasulting.demo.customer.user.repository.UserRepository;
import com.fasulting.demo.customer.user.dto.respDto.UserInfoResp;
import com.fasulting.demo.entity.UserEntity;
import com.fasulting.demo.ps.ps.request.CheckPasswordReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    // passwordEncoder

    // 회원 가입
    @Override
    public boolean userRegister(UserWithoutSeqReq userInfo) {
        UserEntity user = UserEntity.builder()
                .email(userInfo.getEmail())
                .password(userInfo.getPassword())
                .name(userInfo.getName())
                .gender(userInfo.getGender())
                .nation(userInfo.getNation())
                .nationCode(userInfo.getNationCode())
                .number(userInfo.getNumber())
                .birth(userInfo.getBirth())
                .build();

        userRepository.save(user);

        return true;
    }


    // 비밀번호 수정 (재설정)
    @Override
    public boolean resetPassword(UserWithoutSeqReq userInfo) {

        if(userRepository.findUserByEmail(userInfo.getEmail()).isPresent()) {
            // userEmail이 있다면
            UserEntity user = userRepository.findUserByEmail(userInfo.getEmail()).get();

            log.info(userInfo.getPassword());

            // password update
            user.builder().password(userInfo.getPassword()).build();

            userRepository.save(user);

            return true;
        }

        return false;
    }

    // 회원 정보 조회
    @Override
    public UserInfoResp getUserInfo(Long seq) {
        if(userRepository.findById(seq).isPresent()) {
            UserEntity user = userRepository.findById(seq).get();

            UserInfoResp userInfo = new UserInfoResp();

            userInfo.setBirth(user.getBirth());
            userInfo.setEmail(user.getEmail());
            userInfo.setGender(user.getGender());
            userInfo.setNation(user.getNation());
            userInfo.setPhone(user.getNumber());
            userInfo.setName(user.getName());
            userInfo.setNationCode(user.getNationCode());

            return userInfo;
        }
        return null;
    }

    // 회원 이메일 조회 및 중복 확인
    @Override
    public boolean checkEmail(String email) {
        if(userRepository.findUserByEmail(email).isPresent()) {
            log.info("회원 이메일 존재");
            return true;
        }
        else {
            log.info("회원 이메일 존재하지 않음");
            return false;
        }
    }

    // 회원 정보 수정
    @Override
    public boolean editUserInfo(Long seq, UserSeqReq userInfo) {

        if(userRepository.findById(seq).isPresent()) {
            UserEntity user = userRepository.findById(seq).get();

            if(userInfo.getName() != null)
                user.builder().name(userInfo.getName()).build();
            if(userInfo.getNumber() != null)
                user.builder().number(userInfo.getNumber()).build();

            userRepository.save(user);
        }

        return false;
    }

    // 회원 탈퇴
    @Override
    public boolean withdrawUser(UserSeqReq userInfo) {
        if(userRepository.findById(userInfo.getSeq()).isPresent()) {
            UserEntity user = userRepository.findById(userInfo.getSeq()).get();

            user.builder()
                    .delYn("Y")
                    .delBy("user_" + userInfo.getSeq())
                    .delDate(LocalDateTime.now())
                    .build();

            return true;
        }

        return false;
    }

    // 비밀번호 확인
    @Override
    public boolean checkPassword(UserSeqReq userInfo) {

        if(userRepository.findById(userInfo.getSeq()).isPresent()) {
            String userPassword = userRepository.findById(userInfo.getSeq()).get().getPassword();

            if(userPassword.equals(userInfo.getPassword())) {
                return true;
            }
        }

        return false;
    }


}