package com.fasulting.domain.user.consulting.controller;

import com.fasulting.domain.user.consulting.service.ConsultingService;
import io.openvidu.java.client.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import io.openvidu.java.client.Connection;
import io.openvidu.java.client.ConnectionProperties;
import io.openvidu.java.client.OpenVidu;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import io.openvidu.java.client.Session;
import io.openvidu.java.client.SessionProperties;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;


@Slf4j
@RestController
@RequestMapping("/consulting")
@CrossOrigin("*") // 수정
public class ConsultingController {

    private ConsultingService consultingService;

    @Value("${OPENVIDU_URL}")
    private String OPENVIDU_URL;

    @Value("${OPENVIDU_SECRET}")
    private String OPENVIDU_SECRET;

    private OpenVidu openvidu;

    @PostConstruct
    public void init() {
        this.openvidu = new OpenVidu(OPENVIDU_URL, OPENVIDU_SECRET);
    }

    // 1. 상담 입장
    @GetMapping("/consulting/{consultingId}/{userId}")
    public ResponseEntity<?> EnterMeetingRoom(@PathVariable("consultingId") int consultingId, @PathVariable("userId") int userId) {
        return null; // success OR fail
        // success: 상담화면으로 리다이렉트
    }

    /** 세션 생성 및 초기화
     * @param params Session properties
     * @return Session ID
     */
    @PostMapping("/api/sessions")
    public ResponseEntity<String> initializeSession(@RequestBody(required = false) Map<String, Object> params)
            throws OpenViduJavaClientException, OpenViduHttpException {

        SessionProperties properties = SessionProperties.fromJson(params).build();

        Session session = openvidu.createSession(properties);

        return new ResponseEntity<>(session.getSessionId(), HttpStatus.OK);
    }

    /** 커넥션 생성 및 토큰 부여
     * @param sessionId Session
     * @param params    Connection properties
     * @return Token
     */
    @PostMapping("/api/sessions/{sessionId}/connections")
    public ResponseEntity<String> createConnection(@PathVariable("sessionId") String sessionId,
                                                   @RequestBody(required = false) Map<String, Object> params)
            throws OpenViduJavaClientException, OpenViduHttpException {
        Session session = openvidu.getActiveSession(sessionId);
        if (session == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ConnectionProperties properties = ConnectionProperties.fromJson(params).build();
        Connection connection = session.createConnection(properties);
        return new ResponseEntity<>(connection.getToken(), HttpStatus.OK);
    }


}
