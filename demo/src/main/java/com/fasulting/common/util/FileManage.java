package com.fasulting.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class FileManage {

    public static String uploadFile(MultipartFile imgFile, UUID uuid, String imgUrl, String dirPath) {

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
