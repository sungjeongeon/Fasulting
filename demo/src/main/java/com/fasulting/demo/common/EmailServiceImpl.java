package com.fasulting.demo.common;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@PropertySource("classpath:application.properties")
@Slf4j
@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    // 인증코드 생성
    private final String code = createKey();

    @Value("${spring.mail.username}")
    private String id;

    /**
     * 보낼 메일 생성
     *
     * @param to: 보낼 메일 주소
     * @return: 보낼 이메일, 인증 번호 등이 담긴 content를 담은 객체 msg
     */
    public MimeMessage createRegistCodeMessage(String to) throws MessagingException, UnsupportedEncodingException {

        log.info("보낼 이메일: " + to);
        log.info("인증 번호: " + code);

        MimeMessage msg = javaMailSender.createMimeMessage();

        msg.addRecipients(MimeMessage.RecipientType.TO, to); // 보내는 대상
        msg.setSubject("fasulting 회원가입 인증 코드"); // 메일 제목

        // type html로 지정 => html 문법 사용 가능
        StringBuffer content = new StringBuffer();
        content.append("<!DOCTYPE html>");
        content.append("<html>");
        content.append("<head>");
        content.append("</head>");
        content.append("<body>");
        content.append(
                " <div" +
                        "	style=\"font-family: 'Apple SD Gothic Neo', 'sans-serif' !important; width: 400px; height: 600px; border-top: 4px solid #E5F3F5; margin: 100px auto; padding: 30px 0; box-sizing: border-box;\">" +
                        "	<h1 style=\"margin: 0; padding: 0 5px; font-size: 28px; font-weight: 400;\">" +
                        "		<span style=\"font-size: 15px; margin: 0 0 10px 3px;\">Fasulting</span><br />" +
                        "		<span style=\"color: #72A1A6\">메일인증</span> 안내입니다." +
                        "	</h1>\n" +
                        "	<p style=\"font-size: 16px; line-height: 26px; margin-top: 50px; padding: 0 5px;\">" +
                        to +
                        "		님 안녕하세요.<br />" +
                        "		Fasulting에 가입해 주셔서 진심으로 감사드립니다.<br />" +
                        "		아래 <b style=\"color: #72A1A6\">'인증코드'</b> 를 입력하여 회원가입을 완료해 주세요.<br />" +
                        "		감사합니다. <br /> <br />" +
                        " <b style=\"color: #72A1A6; font-size: 28px;\";>" + code + "</b>" +
                        "	</p>" +
                        "	<div style=\"border-top: 1px solid #E5F3F5; padding: 5px;\"></div>" +
                        " </div>"
        );
        content.append("</body>");
        content.append("</html>");

        msg.setText(content.toString(), "utf-8", "html");
        msg.setFrom(new InternetAddress(id, "fasulting")); // email, 이름

        return msg;

    }

    public MimeMessage createResetCodeMessage(String to) throws MessagingException, UnsupportedEncodingException {

        log.info("보낼 이메일: " + to);
        log.info("인증 번호: " + code);

        MimeMessage msg = javaMailSender.createMimeMessage();

        msg.addRecipients(MimeMessage.RecipientType.TO, to); // 보내는 대상
        msg.setSubject("fasulting 비밀번호 재설정 인증 코드"); // 메일 제목

        // type html로 지정 => html 문법 사용 가능
        StringBuffer content = new StringBuffer();
        content.append("<!DOCTYPE html>");
        content.append("<html>");
        content.append("<head>");
        content.append("</head>");
        content.append("<body>");
        content.append(
                " <div" +
                        "	style=\"font-family: 'Apple SD Gothic Neo', 'sans-serif' !important; width: 400px; height: 600px; border-top: 4px solid #E5F3F5; margin: 100px auto; padding: 30px 0; box-sizing: border-box;\">" +
                        "	<h1 style=\"margin: 0; padding: 0 5px; font-size: 28px; font-weight: 400;\">" +
                        "		<span style=\"font-size: 15px; margin: 0 0 10px 3px;\">Fasulting</span><br />" +
                        "		<span style=\"color: #72A1A6\">비밀번호 재설정</span> 안내입니다." +
                        "	</h1>\n" +
                        "	<p style=\"font-size: 16px; line-height: 26px; margin-top: 50px; padding: 0 5px;\">" +
                        to +
                        "		님 안녕하세요.<br />" +
                        "		비밀번호 재설정을 위해<br />" +
                        "		아래 <b style=\"color: #72A1A6\">'인증코드'</b> 를 입력하여 주세요.<br />" +
                        "		감사합니다. <br /> <br />" +
                        " <b style=\"color: #72A1A6; font-size: 28px;\";>" + code + "</b>" +
                        "	</p>" +
                        "	<div style=\"border-top: 1px solid #E5F3F5; padding: 5px;\"></div>" +
                        " </div>"
        );
        content.append("</body>");
        content.append("</html>");

        msg.setText(content.toString(), "utf-8", "html");
        msg.setFrom(new InternetAddress(id, "fasulting")); // email, 이름

        return msg;

    }

    // 인증 코드 생성
    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rand = new Random();

        for (int i = 0; i < 6; i++) {
            key.append((rand.nextInt(10)));
        }

        return key.toString();
    }

    /**
     * 메일 발송
     *
     * @param to: 보낼 메일 주소
     * @return: 인증 코드를 서버로 리턴
     */
    @Override
    public String sendRegistCodeMessage(String to) throws Exception {
        MimeMessage msg = createRegistCodeMessage(to); // 전송할 메일의 내용 담기

        try {
            javaMailSender.send(msg);
        } catch (MailException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }

        return code;
    }

    @Override
    public String sendResetCodeMessage(String to) throws Exception {
        MimeMessage msg = createResetCodeMessage(to); // 전송할 메일의 내용 담기

        try {
            javaMailSender.send(msg);
        } catch (MailException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }

        return code;
    }
}