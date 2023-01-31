package com.fasulting.demo.common.email;

public interface EmailService {

    String sendRegistCodeMessage(String to) throws Exception;
    String sendResetCodeMessage(String to) throws Exception;

}
