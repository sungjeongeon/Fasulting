package com.fasulting.domain.user.user.service;

import com.fasulting.common.RoleType;
import com.fasulting.common.util.CheckInfo;
import com.fasulting.domain.user.user.dto.reqDto.UserSeqReqDto;
import com.fasulting.domain.user.user.dto.reqDto.UserWithoutSeqReqDto;
import com.fasulting.domain.user.user.dto.respDto.UserInfoRespDto;
import com.fasulting.entity.user.RoleEntity;
import com.fasulting.entity.user.UserEntity;
import com.fasulting.exception.UnAuthorizedException;
import com.fasulting.repository.role.RoleRepository;
import com.fasulting.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.fasulting.common.util.LogCurrent.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    /**
     * 회원 가입
     * @param userInfo
     * @return
     */
    @Override
    @Transactional
    public boolean userRegister(UserWithoutSeqReqDto userInfo) {
        log.info(logCurrent(getClassName(), getMethodName(), START));
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
                .authority(RoleType.USER)
                .build();

        roleRepository.save(roleEntity);

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return true;
    }

    /**
     * 비밀번호 수정 (재설정)
     * @param userInfo
     * @return
     */
    @Transactional
    @Override
    public boolean resetPassword(UserWithoutSeqReqDto userInfo) {
        log.info(logCurrent(getClassName(), getMethodName(), START));
        // userEmail이 있다면
        UserEntity user = userRepository.findUserByEmail(userInfo.getEmail()).orElseThrow(() -> {
            throw new NullPointerException();
        });

        if (passwordEncoder.matches(userInfo.getPassword(), user.getPassword())) {
            log.info(logCurrent(getClassName(), getMethodName(), END));
            return false;
        }

        // password update
        user.resetPassword(passwordEncoder.encode(userInfo.getPassword()));

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return true;

    }

    /**
     * 회원 정보 조회
     * @param seq
     * @return
     */
    @Override
    public UserInfoRespDto getUserInfo(Long seq) {

        log.info(logCurrent(getClassName(), getMethodName(), START));
        UserEntity user = userRepository.findById(seq).orElseThrow(() -> {
            throw new NullPointerException();
        });

        if (!CheckInfo.checkLoginInfo(user.getSeq(), user.getEmail(), user.getRole().getAuthority())){
            log.error(logCurrent(getClassName(), getMethodName(), END));
            throw new UnAuthorizedException();
        }

        UserInfoRespDto userInfo = UserInfoRespDto.builder()
                .userBirth(user.getBirth())
                .userEmail(user.getEmail())
                .userNumber(user.getNumber())
                .userName(user.getName())
                .build();

        log.info(logCurrent(getClassName(), getMethodName(), END));
        return userInfo;

    }

    /**
     * 이메일 조회 및 중복 확인
     * @param email
     * @return
     */
    @Override
    public boolean checkEmail(String email) {
        log.info(logCurrent(getClassName(), getMethodName(), START));
        if (userRepository.findUserByEmail(email).isPresent()) {
            log.info(logCurrent(getClassName(), getMethodName(), END));
            return true;
        } else {
            log.info(logCurrent(getClassName(), getMethodName(), END));
            return false;
        }
    }

    /**
     * 회원 탈퇴
     * @param userInfo
     * @return
     */
    @Override
    @Transactional
    public boolean withdrawUser(UserSeqReqDto userInfo) {
        log.info(logCurrent(getClassName(), getMethodName(), START));
        UserEntity user = userRepository.findById(userInfo.getSeq()).orElseThrow(() -> {
            throw new NullPointerException();
        });

//        if (!CheckInfo.checkLoginInfo(user.getSeq(), user.getEmail(), user.getRole().getAuthority())){
//            log.error(logCurrent(getClassName(), getMethodName(), END));
//            throw new UnAuthorizedException();
//        }

        user.updateByWithdrawal("Y", RoleType.USER + "" + userInfo.getSeq(), LocalDateTime.now());

        userRepository.save(user);
        log.info(logCurrent(getClassName(), getMethodName(), END));
        return true;

    }

    /**
     * 비밀번호 확인
     * @param userInfo
     * @return
     */
    @Override
    public boolean checkPassword(UserSeqReqDto userInfo) {

        log.info(logCurrent(getClassName(), getMethodName(), START));

        UserEntity user = userRepository.findById(userInfo.getSeq()).orElseThrow(() -> {
            throw new NullPointerException();
        });

//        if (!CheckInfo.checkLoginInfo(user.getSeq(), user.getEmail(), user.getRole().getAuthority())){
//            log.error(logCurrent(getClassName(), getMethodName(), END));
//            throw new UnAuthorizedException();
//        }

        if (passwordEncoder.matches(userInfo.getPassword(), user.getPassword())) {
            log.info(logCurrent(getClassName(), getMethodName(), END));
            return true;
        }
        log.info(logCurrent(getClassName(), getMethodName(), END));
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
