package com.fasulting.demo.customer.user.service;

import com.fasulting.demo.customer.user.db.entity.User;
import com.fasulting.demo.customer.user.request.UserRegisterReq;

public interface UserService {

    User userRegister(UserRegisterReq userRegisterInfo); // 회원가입

}
