package com.fasulting.demo.ps.ps.dto.reqDto;

import com.fasulting.demo.resp.Doctor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class PsSeqReq {

    private Long seq;

    private String email;
    private String password;
    private String name;
    private String address;
    private String zipcode;
    private String registration;
    private String registrationImg;
    private String number;
    private String director;
    private String homepage;
    private String profileImg;
    private String intro;

    // category 정보
    private List<String> mainCategoryList;
    private List<String> subCategoryList;

    // doctor 정보
    private List<Doctor> doctorList;


}
