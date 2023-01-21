package com.fasulting.demo.customer.user.service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface UserEmailService {

    // (회원 가입) 입력 받은 이메일로 인증 코드 보내기
    String sendRegistCodeMessage(String to) throws Exception;
    String sendResetCodeMessage(String to) throws Exception;

}
