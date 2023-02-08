package com.fasulting.domain.ps.psConsulting.controller;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.fasulting.common.resp.ResponseBody;
import com.fasulting.domain.ps.psConsulting.dto.ResultReqDto;
import com.fasulting.domain.ps.psConsulting.service.PsConsultingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;

@RestController
@Slf4j
@RequestMapping("/ps-consulting")
@RequiredArgsConstructor
@CrossOrigin("*")
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

    @GetMapping("/download/{reservationSeq}")
    public ResponseEntity<?> getBeforeImg(@PathVariable Long reservationSeq) {

        log.info("getBeforeImg - Call");

        Map<String, String> map = psConsultingService.getBeforeImg(reservationSeq);

        String path = map.get("beforeImgPath");
        String originName = map.get("beforeImgOrigin");

//        log.info(path + "\n" + originName);

        S3Object s3Object = amazonS3Client.getObject(new GetObjectRequest(bucket, path));
        S3ObjectInputStream objectInputStream =  s3Object.getObjectContent();

        String[] arr = path.split("/");
        String fullName = "";

        

//        for(String s : arr) {
//            fullName += s;
//        }

        try {
            byte[] bytes = IOUtils.toByteArray(objectInputStream);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(contentType(path));
            httpHeaders.setContentLength(bytes.length);

            String[] arr =

            httpHeaders.setContentDispositionFormData("attachment", originName);

            return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



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
