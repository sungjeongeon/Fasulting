package com.fasulting.common.interceptor.jwt;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class  LoginInfo {

    private static Long seq;
    private static String email;
    private static String domain;

    public static void setSeq(Long seq) {
        LoginInfo.seq = seq;
    }

    public static void setEmail(String email) {
        LoginInfo.email = email;
    }

    public static void setDomain(String domain) {
        LoginInfo.domain = domain;
    }

    public static Long getSeq() {
        return seq;
    }

    public static String getEmail() {
        return email;
    }

    public static String getDomain() {
        return domain;
    }
}
