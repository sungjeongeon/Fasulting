package com.fasulting.domain.ps.psConsulting.controller;

import com.fasulting.common.resp.ResponseBody;
import com.fasulting.domain.ps.psConsulting.dto.ResultReqDto;
import com.fasulting.domain.ps.psConsulting.service.PsConsultingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import io.openvidu.java.client.Connection;
import io.openvidu.java.client.ConnectionProperties;
import io.openvidu.java.client.OpenVidu;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import io.openvidu.java.client.Session;
import io.openvidu.java.client.SessionProperties;

import javax.annotation.PostConstruct;



@RestController
@Slf4j
@RequestMapping("/ps-consulting")
@CrossOrigin("*")
public class PsConsultingController {

    private PsConsultingService psConsultingService;
    
    public PsConsultingController(PsConsultingService psConsultingService) {
        this.psConsultingService = psConsultingService;
    }

    @Value("${OPENVIDU_URL}")
    private String OPENVIDU_URL;

    @Value("${OPENVIDU_SECRET}")
    private String OPENVIDU_SECRET;

    private OpenVidu openvidu;

    @PostConstruct
    public void init() {
        this.openvidu = new OpenVidu(OPENVIDU_URL, OPENVIDU_SECRET);
    }

    @GetMapping("/download/{reservationSeq}")
    public ResponseEntity<?> getBeforeImg(@PathVariable Long reservationSeq) {

        Map<String, String> map = psConsultingService.getBeforeImg(reservationSeq);

        String path = map.get("beforeImgPath");
        String originName = map.get("beforeImgOrigin");

        try {
            Path filePath = Paths.get(path);
            Resource resource = new InputStreamResource(Files.newInputStream(filePath)); // 파일 resource 얻기

            File file = new File(path);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentDisposition(ContentDisposition.builder("attachment").filename(originName).build());  // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더

            return new ResponseEntity<Object>(resource, headers, HttpStatus.OK); // 200
        } catch(Exception e) {
            return new ResponseEntity<Object>(null, HttpStatus.CONFLICT); // 409
        }
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

    // 상담 결과 작성
    @PostMapping("/result")
    public ResponseEntity<?> writeResult(@ModelAttribute ResultReqDto resultReq) {

        if(psConsultingService.writeResult(resultReq)) {
            return ResponseEntity.status(200).body(com.fasulting.common.resp.ResponseBody.create(200, "success"));
        }
        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));

    }


    // 1. 상담 입장
    // 2. 상담 사전 진행
    // 3. 상담 종료

}
