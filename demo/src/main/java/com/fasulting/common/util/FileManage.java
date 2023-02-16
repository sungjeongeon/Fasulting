package com.fasulting.common.util;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Random;
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
    public static final String beforeImgDirPath = "before/";
    public static final String afterImgDirPath = "after/";
    public static final String psProfileImgDirPath = "ps/profile/";
    public static final String psRegImgDirPath = "ps/reg/";
    public static final String doctorImgPath = "doctor/profile/";
//
//
    public static final String domain = "https://hotsix.s3.ap-northeast-2.amazonaws.com/";

    public static String getRandomCode() {
        String words = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();

        StringBuffer code = new StringBuffer();
        for (int i = 0; i < 5; i++) {
            code.append(words.charAt(random.nextInt(words.length())));
        }

        return code.toString();
    }

    public static String uploadFile(MultipartFile imgFile, UUID uuid, String path) {

        ObjectMetadata objMeta = new ObjectMetadata();
        String imgSaveUrl = uuid + "_" + getRandomCode();

        objMeta.setContentType(imgFile.getContentType());

//        log.info(imgFile.getOriginalFilename());
//        log.info(objMeta.getContentLength());
//        log.info("ddddd " + imgSaveUrl);

        try {
            amazonS3Client.putObject(bucket, path + imgSaveUrl, imgFile.getInputStream(), objMeta);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return path + imgSaveUrl;

    }

    //파일 삭제
    public static boolean deleteFile(String path) {

        try {
            amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, path));
        } catch (AmazonServiceException e) {
            log.error(e.getMessage());
        } catch (SdkClientException e) {
            log.error(e.getMessage());
        }
        return true;
    }

    // 이미지 크기 줄이기
    public static MultipartFile resizeImage(MultipartFile originalImage) {
        try {
            // MultipartFile -> BufferedImage Convert
            BufferedImage image = ImageIO.read(originalImage.getInputStream());
            // newWidth : newHeight = originWidth : originHeight
            int newWidth = 512;
            int newHeight =512;


            Image resizeImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_FAST);
            BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

            Graphics2D graphics2D = newImage.createGraphics();
            graphics2D.drawImage(resizeImage, 0, 0, newWidth, newHeight, null);
            graphics2D.dispose();

            // 확장자 구하기
            String[] str = originalImage.getOriginalFilename().split("\\.");
            String formatName = str[str.length - 1];

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(newImage, formatName, baos );
            baos.flush();

            MultipartFile multipartFile = new MultipartImage(baos.toByteArray(), originalImage.getName(), originalImage.getOriginalFilename(),
                    originalImage.getContentType(), baos.toByteArray().length);

            return  multipartFile;

        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 리사이즈에 실패했습니다.");
        }
    }
}
