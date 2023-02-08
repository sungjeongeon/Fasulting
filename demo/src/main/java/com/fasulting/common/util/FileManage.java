package com.fasulting.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class FileManage {

    // 배포할 때 경로 바꾸기
    // 배포할 때 경로 바꾸기
    public static final String beforeImgDirPath = "before/";
    public static final String afterImgDirPath = "after/";
    public static final String psProfileImgDirPath = "ps/profile/";
    public static final String psRegImgDirPath = "ps/reg";
    public static final String doctorImgPath = "doctor/profile/";


    public static final String domain = "http://i8e106.p.ssafy.io/home/ubuntu/fasulting/";


    public static String uploadFile(MultipartFile imgFile, UUID uuid, String imgUrl, String path) {

        String dirPath = domain + path;

        File folder = new File(dirPath);

        if (!folder.exists()) {
            folder.mkdirs(); // 폴더 생성
        }

        String imgSaveUrl = uuid + "_" + imgFile.getOriginalFilename();
        File file = new File(dirPath + File.separator + imgSaveUrl);

        try {
            imgFile.transferTo(file); // 이미지 최종 경로로 보내줘서 저장
            return dirPath + File.separator + imgSaveUrl;
        } catch (IOException e) {
            log.info(e.getMessage());
        }
        return null;
    }
}
