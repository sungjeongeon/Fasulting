package com.fasulting.domain.user.user.service;

import com.fasulting.domain.user.user.dto.reqDto.UserSeqReqDto;
import com.fasulting.domain.user.user.dto.reqDto.UserWithoutSeqReqDto;
import com.fasulting.domain.user.user.dto.respDto.UserInfoRespDto;
import com.fasulting.entity.user.RoleEntity;
import com.fasulting.entity.user.UserEntity;
import com.fasulting.repository.role.RoleRepository;
import com.fasulting.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    // 로그인
    @Override
    public UserInfoRespDto login(UserWithoutSeqReqDto userInfo) {

        if (userRepository.findUserByEmailAndPassword(userInfo.getEmail(), userInfo.getPassword()).isPresent()) {

            UserEntity user = userRepository.findUserByEmailAndPassword(userInfo.getEmail(), userInfo.getPassword()).orElseThrow(() -> {
                throw new NullPointerException();
            });

            UserInfoRespDto userInfoRespDto = UserInfoRespDto.builder()
                    .userSeq(user.getSeq())
                    .userName(user.getName())
                    .build();

            return userInfoRespDto;

        }

        return null;
    }

    // 회원 가입
    @Override
    public boolean userRegister(UserWithoutSeqReqDto userInfo) {
        // User save
        UserEntity user = UserEntity.builder()
                .email(userInfo.getEmail())
                .password(passwordEncoder.encode(userInfo.getPassword()))
                .name(userInfo.getName())
                .number(userInfo.getNumber())
                .birth(userInfo.getBirth())
                .build();

        userRepository.save(user);

        // User-Role save
        RoleEntity roleEntity = RoleEntity.builder()
                .user(user)
                .authority("user")
                .build();

        roleRepository.save(roleEntity);

        return true;
    }


    // 비밀번호 수정 (재설정)
    @Transactional
    @Override
    public boolean resetPassword(UserWithoutSeqReqDto userInfo) {

        // userEmail이 있다면
        UserEntity user = userRepository.findUserByEmail(userInfo.getEmail()).orElseThrow(() -> {
            throw new NullPointerException();
        });

        String prePassword = user.getPassword();

        log.info(userInfo.getPassword());

        // password update
        user.resetPassword(userInfo.getPassword());

        String postPassword = user.getPassword();

        if (prePassword.equals(postPassword)) {
            return false;
        }
        return true;

    }

    // 회원 정보 조회
    @Override
    public UserInfoRespDto getUserInfo(Long seq) {

        UserEntity user = userRepository.findById(seq).orElseThrow(() -> {
            throw new NullPointerException();
        });

        UserInfoRespDto userInfo = UserInfoRespDto.builder()
                .userBirth(user.getBirth())
                .userEmail(user.getEmail())
                .userNumber(user.getNumber())
                .userName(user.getName())
                .build();


        return userInfo;

    }

    // 회원 이메일 조회 및 중복 확인
    @Override
    public boolean checkEmail(String email) {
        if (userRepository.findUserByEmail(email).isPresent()) {
            log.info("회원 이메일 존재");
            return true;
        } else {
            log.info("회원 이메일 존재하지 않음");
            return false;
        }
    }

    // 회원 탈퇴
    @Override
    @Transactional
    public boolean withdrawUser(UserSeqReqDto userInfo) {

        UserEntity user = userRepository.findById(userInfo.getSeq()).orElseThrow(() -> {
            throw new NullPointerException();
        });

        user.updateByWithdrawal("Y", "user_" + userInfo.getSeq(), LocalDateTime.now());

        userRepository.save(user);

        return true;

    }

    // 비밀번호 확인
    @Override
    public boolean checkPassword(UserSeqReqDto userInfo) {


        String password = userRepository.findById(userInfo.getSeq()).orElseThrow(() -> {
            throw new NullPointerException();
        }).getPassword();

        if (password.equals(userInfo.getPassword())) {
            return true;
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
