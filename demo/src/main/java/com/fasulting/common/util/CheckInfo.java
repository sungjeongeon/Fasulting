package com.fasulting.common.util;

import com.fasulting.common.RoleType;
import com.fasulting.common.interceptor.jwt.LoginInfo;
import com.fasulting.entity.ps.PsEntity;
import com.fasulting.entity.user.UserEntity;
import com.fasulting.exception.UnAuthorizedException;

public class CheckInfo {

    public static boolean checkLoginInfo (Long seq, String email, String domain){
        return LoginInfo.getEmail().equals(email) &&
                LoginInfo.getSeq() == seq &&
                LoginInfo.getDomain().equals(domain);
    }

    public void authorize (UserEntity user){
        if(!checkLoginInfo(user.getSeq(), user.getEmail(), user.getRole().getAuthority())){
            throw new UnAuthorizedException();
        }
    }

    public void authorize (PsEntity ps){
        if(!checkLoginInfo(ps.getSeq(), ps.getEmail(), RoleType.PS)){
            throw new UnAuthorizedException();
        }
    }
}
