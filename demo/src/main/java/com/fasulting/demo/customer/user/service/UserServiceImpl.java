package com.fasulting.demo.customer.user.service;

import com.fasulting.demo.customer.user.db.entity.User;
import com.fasulting.demo.customer.user.db.repository.UserRepository;
import com.fasulting.demo.customer.user.request.UserRegisterReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    // passwordEncoder

    @Override
    public User userRegister(UserRegisterReq userRegisterInfo) {
        User user = new User();

        StringBuilder sb = new StringBuilder();
//        sb.append(userRegisterInfo.getUserBirth()).insert(4, "-").insert(7, "-");

        user.setUserEmail(userRegisterInfo.getUserEmail());
        user.setUserPassword(userRegisterInfo.getUserPassword()); // encode 필요
        user.setUserGender(userRegisterInfo.getUserGender());
        user.setUserNation(userRegisterInfo.getUserNation());
        user.setUserNationCode(userRegisterInfo.getUserNationCode());
        user.setUserPhone(userRegisterInfo.getUserPhone());
        user.setUserBirth(userRegisterInfo.getUserBirth());
        user.setUserName(userRegisterInfo.getUserName());


        return userRepository.save(user);
    }
}
