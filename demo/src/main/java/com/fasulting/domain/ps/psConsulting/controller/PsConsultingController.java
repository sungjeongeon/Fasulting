package com.fasulting.domain.ps.psConsulting.controller;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.fasulting.common.resp.ResponseBody;
import com.fasulting.common.util.LogCurrent;
import com.fasulting.domain.ps.psConsulting.dto.ResultReqDto;
import com.fasulting.domain.ps.psConsulting.service.PsConsultingService;
import io.openvidu.java.client.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;

import static com.fasulting.common.util.LogCurrent.*;


@RestController
@Slf4j
@RequestMapping("/ps-consulting")
@RequiredArgsConstructor
public class PsConsultingController {

    private final PsConsultingService psConsultingService;
    private final AmazonS3Client amazonS3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;


    private MediaType contentType(String name) {
        String[] arr = name.split("\\.");
        String type = arr[arr.length - 1];

        switch(type) {
            case "jpg":
                return MediaType.IMAGE_JPEG;
            case "png":
                return MediaType.IMAGE_PNG;
            default:
                return MediaType.APPLICATION_OCTET_STREAM;
        }
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

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));

        Map<String, String> map = psConsultingService.getBeforeImg(reservationSeq);

        String path = map.get("beforeImgPath");
        String originName = map.get("beforeImgOrigin");

        S3Object s3Object = amazonS3Client.getObject(new GetObjectRequest(bucket, path));
        S3ObjectInputStream objectInputStream =  s3Object.getObjectContent();


        try {
            byte[] bytes = IOUtils.toByteArray(objectInputStream);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(contentType(path));
            httpHeaders.setContentLength(bytes.length);

            // 일단 chrome 하나..
            originName = new String(originName.getBytes("UTF-8"), "ISO-8859-1");
//            httpHeaders.add("Content-Disposition", "attachment; filename=" + originName);
            httpHeaders.setContentDisposition(ContentDisposition.builder("attachment").filename(originName).build());

            log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
            return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /** 세션 생성 및 초기화
     * @param params Session properties
     * @return Session ID
     */
    @PostMapping("/api/sessions")
    public ResponseEntity<String> initializeSession(@RequestBody(required = false) Map<String, Object> params)
            throws OpenViduJavaClientException, OpenViduHttpException {

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));

        SessionProperties properties = SessionProperties.fromJson(params).build();

        Session session = openvidu.createSession(properties);

        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
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
        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));
        Session session = openvidu.getActiveSession(sessionId);
        if (session == null) {
            log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ConnectionProperties properties = ConnectionProperties.fromJson(params).build();
        Connection connection = session.createConnection(properties);
        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
        return new ResponseEntity<>(connection.getToken(), HttpStatus.OK);
    }

    /**
     * 상담 결과 작성
     * @param resultReq
     * @return
     */
    @PostMapping("/result")
    public ResponseEntity<?> writeResult(@ModelAttribute ResultReqDto resultReq) {
        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), START));
        if(psConsultingService.writeResult(resultReq)) {
            log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
            return ResponseEntity.status(200).body(com.fasulting.common.resp.ResponseBody.create(200, "success"));
        }
        log.info(LogCurrent.logCurrent(getClassName(), getMethodName(), END));
        return ResponseEntity.status(500).body(ResponseBody.create(500, "fail"));

    }
}
