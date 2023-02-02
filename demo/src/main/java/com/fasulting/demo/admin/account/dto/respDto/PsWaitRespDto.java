package com.fasulting.demo.admin.account.dto.respDto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
public class PsWaitRespDto {

    private Long psSeq;
    private String email;
    private String name;
    private String address;
    private String zipcode;
    private String registration;
    private String number;
    private String director;
    private String homepage;
    private String intro;

    private List<String> mainCategoryList;
    private List<String> subCategoryList;

    private MultipartFile registrationImg;
    private MultipartFile profileImg;

}
