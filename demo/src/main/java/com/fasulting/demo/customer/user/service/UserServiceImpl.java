package com.fasulting.demo.customer.user.service;

import com.fasulting.demo.customer.user.db.entity.User;
import com.fasulting.demo.customer.user.db.repository.UserRepository;
import com.fasulting.demo.customer.user.request.*;
import com.fasulting.demo.customer.user.response.UserInfoResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    // passwordEncoder

    // 회원 가입
    @Override
    public boolean userRegister(UserRegisterReq userRegisterInfo) {
        User user = new User();

//        StringBuilder sb = new StringBuilder();
//        sb.append(userRegisterInfo.getUserBirth()).insert(4, "-").insert(7, "-");

        user.setUserEmail(userRegisterInfo.getUserEmail());
        user.setUserPassword(userRegisterInfo.getUserPassword()); // encode 필요
        user.setUserGender(userRegisterInfo.getUserGender());
        user.setUserNation(userRegisterInfo.getUserNation());
        user.setUserNationCode(userRegisterInfo.getUserNationCode());
        user.setUserPhone(userRegisterInfo.getUserPhone());
        user.setUserBirth(userRegisterInfo.getUserBirth());
        user.setUserName(userRegisterInfo.getUserName());


        userRepository.save(user);

        return true;
    }


    // 비밀번호 수정 (재설정)
    @Override
    public boolean ResetPassword(UserBasicInfoReq userResetInfo) {

        if(userRepository.findUserByUserEmail(userResetInfo.getUserEmail()).isPresent()) {
            // userEmail이 있다면
            User user = userRepository.findUserByUserEmail(userResetInfo.getUserEmail()).get();

            log.info(userResetInfo.getUserPassword());
            // password update
            user.setUserPassword(userResetInfo.getUserPassword());

            userRepository.save(user);

            return true;
        }

        return false;
    }

    // 회원 정보 조회
    @Override
    public UserInfoResp GetUserInfo(int userId) {
        if(userRepository.findById(userId).isPresent()) {
            User user = userRepository.findById(userId).get();
            UserInfoResp userInfo = new UserInfoResp();

            userInfo.setUserBirth(user.getUserEmail());
            userInfo.setUserEmail(user.getUserEmail());
            userInfo.setUserGender(user.getUserGender());
            userInfo.setUserNation(user.getUserNation());
            userInfo.setUserPhone(user.getUserPhone());
            userInfo.setUserName(user.getUserName());
            userInfo.setUserNationCode(user.getUserNationCode());

            return userInfo;
        }
        return null;
    }

    // 회원 이메일 중복 확인
    @Override
    public boolean DupleEmail(String userEmail) {
        if(userRepository.findUserByUserEmail(userEmail).isPresent()) {
            log.info("회원 이메일 중복");
            return false;
        }
        else {
            log.info("회원 이메일 중복 아님");
            return true;
        }
    }

    // 회원 정보 수정
    @Override
    public boolean EditUserInfo(int id, EditUserInfoReq userInfo) {

        if(userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();

            if(userInfo.getUserName() != null)
                user.setUserName(userInfo.getUserName());
            if(userInfo.getUserPhone() != null)
                user.setUserPhone(userInfo.getUserPhone());

            userRepository.save(user);
        }

        return false;
    }

    // 회원 탈퇴
    @Override
    public boolean WidrawUser(WithdrawReq userInfo) {
        if(userRepository.findById(userInfo.getUserSeq()).isPresent()) {
            User user = userRepository.findById(userInfo.getUserSeq()).get();
            user.setUserValidation("N");
            return true;
        }

        return false;
    }

    // 비밀번호 중복 확인
    @Override
    public boolean CheckPassword(CheckPasswordReq userInfo) {

        if(userRepository.findById(userInfo.getUserSeq()).isPresent()) {
            String userPassword = userRepository.findById(userInfo.getUserSeq()).get().getUserPassword();
            if(userPassword.equals(userInfo.getUserPassword())) {
                return true;
            }
        }

        return false;
    }


}
