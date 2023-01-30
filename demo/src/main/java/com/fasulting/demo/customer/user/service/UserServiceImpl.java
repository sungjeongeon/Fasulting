package com.fasulting.demo.customer.user.service;

import com.fasulting.demo.customer.user.dto.reqDto.*;
import com.fasulting.demo.customer.user.dto.respDto.FavoriteResp;
import com.fasulting.demo.customer.user.repository.FavoriteRepository;
import com.fasulting.demo.customer.user.repository.UserRepository;
import com.fasulting.demo.customer.user.dto.respDto.UserInfoResp;
import com.fasulting.demo.entity.FavoriteEntity;
import com.fasulting.demo.entity.PsEntity;
import com.fasulting.demo.entity.PsMainSubEntity;
import com.fasulting.demo.entity.UserEntity;
import com.fasulting.demo.ps.ps.repository.PsMainSubRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private PsMainSubRepository psMainSubRepository;

    // passwordEncoder

    // 회원 가입
    @Override
    public boolean userRegister(UserWithoutSeqReq userInfo) {
        UserEntity user = UserEntity.builder()
                .email(userInfo.getEmail())
                .password(userInfo.getPassword())
                .name(userInfo.getName())
                .nation(userInfo.getNation())
                .nationCode(userInfo.getNationCode())
                .number(userInfo.getNumber())
                .birth(userInfo.getBirth())
                .build();

        userRepository.save(user);

        return true;
    }


    // 비밀번호 수정 (재설정)
    @Transactional
    @Override
    public boolean resetPassword(UserWithoutSeqReq userInfo) {

        // userEmail이 있다면
        UserEntity user = userRepository.findUserByEmail(userInfo.getEmail()).get();

        String prePassword = user.getPassword();

        log.info(userInfo.getPassword());

        // password update
        user.resetPassword(userInfo.getPassword());

        String postPassword = user.getPassword();

        if(prePassword.equals(postPassword)){
            return false;
        }
        return true;

    }

    // 회원 정보 조회
    @Override
    public UserInfoResp getUserInfo(Long seq) {
        if(userRepository.findById(seq).isPresent()) {
            UserEntity user = userRepository.findById(seq).get();

            UserInfoResp userInfo = new UserInfoResp();

            userInfo.setUserBirth(user.getBirth());
            userInfo.setUserEmail(user.getEmail());
            userInfo.setUserNation(user.getNation());
            userInfo.setUserPhone(user.getNumber());
            userInfo.setUserName(user.getName());
            userInfo.setUserNationCode(user.getNationCode());

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

    // 회원 탈퇴
    @Override
    @Transactional
    public boolean withdrawUser(UserSeqReq userInfo) {
        if(userRepository.findById(userInfo.getSeq()).isPresent()) {
            UserEntity user = userRepository.findById(userInfo.getSeq()).get();

            user.withdrawlUser("Y", "user_" + userInfo.getSeq(), LocalDateTime.now());

            return true;
        }

        return false;
    }

    // 비밀번호 확인
    @Override
    public boolean checkPassword(UserSeqReq userInfo) {

        if(userRepository.findById(userInfo.getSeq()).isPresent()) {
            String password = userRepository.findById(userInfo.getSeq()).get().getPassword();

            if(password.equals(userInfo.getPassword())) {
                return true;
            }
        }

        return false;
    }

    // 회원 정보 수정
//    @Override
//    @Transactional
//    public boolean editUserInfo(Long seq, UserSeqReq userInfo) {
//
//        if(userRepository.findById(seq).isPresent()) {
//            UserEntity user = userRepository.findById(seq).get();
//
//            log.info(userInfo.toString());
//            if(userInfo.getName() != null)
//                user.updateUserEntity(userInfo.getName(), user.getNumber());
//            if(userInfo.getNumber() != null)
//                user.updateUserEntity(user.getName(), userInfo.getNumber());
//
//            return true;
//        }
//
//        return false;
//    }
}
