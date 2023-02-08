package com.fasulting.common.util;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
public class FileManage {

    private static AmazonS3Client amazonS3Client;

    @Autowired
    public void setAmazonS3Client(AmazonS3Client amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }

//    @Value("${cloud.aws.s3.bucket}")
    public static String bucket;

    @Value("${cloud.aws.s3.bucket}")
    public void setKey(String bucket) {
        this.bucket = bucket;
    }

    // 배포할 때 경로 바꾸기
    // 배포할 때 경로 바꾸기
//    public static final String beforeImgDirPath = "before";
//    public static final String afterImgDirPath = "after";
//    public static final String psProfileImgDirPath = "ps/profile";
//    public static final String psRegImgDirPath = "ps/reg";
//    public static final String doctorImgPath = "doctor/profile";
//
//
//    public static final String domain = "/home/ubuntu/fasulting/";


    public static String uploadFile(MultipartFile imgFile, UUID uuid, String path) {

        ObjectMetadata objMeta = new ObjectMetadata();
        String imgSaveUrl = uuid + "_" + imgFile.getOriginalFilename();

        objMeta.setContentType(imgFile.getContentType());

//        log.info(imgFile.getOriginalFilename());
//        log.info(objMeta.getContentLength());

        try {
            amazonS3Client.putObject(bucket, path + imgSaveUrl, imgFile.getInputStream(), objMeta);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return amazonS3Client.getUrl(bucket, path + imgSaveUrl).toString();

//        String dirPath = domain + path;
//
//        File folder = new File(dirPath);
//
//        if (!folder.exists()) {
//            folder.mkdirs(); // 폴더 생성
//        }
//
//        String imgSaveUrl = uuid + "_" + imgFile.getOriginalFilename();
//        File file = new File(dirPath + File.separator + imgSaveUrl);
//
//        try {
//            imgFile.transferTo(file); // 이미지 최종 경로로 보내줘서 저장
//            return dirPath + File.separator + imgSaveUrl;
//        } catch (IOException e) {
//            log.info(e.getMessage());
//        }
//        return null;
    }
}
