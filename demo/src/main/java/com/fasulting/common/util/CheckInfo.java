package com.fasulting.common.util;

import com.fasulting.common.interceptor.jwt.LoginInfo;

public class CheckInfo {

    public static boolean checkLoginInfo (Long seq, String email, String domain){
        return LoginInfo.getEmail().equals(email) &&
                LoginInfo.getSeq() == seq &&
                LoginInfo.getDomain().equals(domain);
    }
}
