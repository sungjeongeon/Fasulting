package com.fasulting.demo.ps.ps.service;

public interface PsEmailService {

    String sendRegistCodeMessage(String to) throws Exception;
    String sendResetCodeMessage(String to) throws Exception;

}
